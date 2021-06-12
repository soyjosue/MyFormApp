package com.example.myform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    
    private Button btnShowMessage;
    private ImageButton ibShare;

    private String name;
    private int age, typeMessage = 0;
    private boolean err;

    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Obtener extras del Intent
        Bundle bundle = getIntent().getExtras();
        
        // Comprobar si el bundle viene con los extras
        if(bundle != null) {
            this.err = false;
            this.name = bundle.getString("NAME");
            this.age = bundle.getInt("AGE");
            this.typeMessage = bundle.getInt("TYPE");
        } else {
            this.err = true;
            Toast.makeText(this, "Hubo un problema con el envio de los datos.", Toast.LENGTH_LONG).show();
        }
        
        // Buscando elements en el activity
        btnShowMessage = (Button) findViewById(R.id.btnShowMessage);
        ibShare = (ImageButton) findViewById(R.id.ibShare);

        if(err) return;

        if (typeMessage == 0 ) {
            message = "Hola " + name + ", ¿Cómo llevas esos " + age + " años? #MyForm";
        } else {
            message = "Espero verte pronto " + name + ", antes que cumplas " + (age + 1) + ".. #MyForm";
        }
        
        // Asignando listening al btnShowMessage
        btnShowMessage.setOnClickListener(View -> {
            if(!err) {
                btnShowMessage.setVisibility(android.view.View.GONE);

                Toast.makeText(ThirdActivity.this, this.message, Toast.LENGTH_LONG).show();
            }
        });

        ibShare.setOnClickListener(View -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, this.message);
            startActivity(Intent.createChooser(intent, "Compartir Usando"));
        });
    }
}