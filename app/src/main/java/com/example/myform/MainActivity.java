package com.example.myform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Busco en la actividad los elementos
        etName = (EditText) findViewById(R.id.etNombre);
        Button btnContinue = (Button) findViewById(R.id.buttonContinue);

        // Agregar listening al button
        btnContinue.setOnClickListener(view -> {

            // Guardando el valor actual del EditText
            String value = etName.getText().toString();

            if(!value.isEmpty()) {
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_LONG).show();
                 Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                 intent.putExtra("NAME", value);
                 startActivity(intent);

            } else {
                Toast.makeText(MainActivity.this, "Para continuar debe colocar su nombre.", Toast.LENGTH_LONG).show();
            }

        });

    }
}