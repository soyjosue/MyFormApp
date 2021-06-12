package com.example.myform;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    // Elements Activity
    private Button btnContinue;
    private RadioGroup radioGroup;
    private SeekBar seekBar;
    private Toast toastPrev;

    // Nombre del usuario
    private String nameUser;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnContinue = (Button) findViewById(R.id.buttonContinue);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        // Obtener extras intent
        Bundle bundle = getIntent().getExtras();

        // Comprobar si no se ha enviado extra
        if (bundle != null) {
            // Obteniendo nombre del usuario
            nameUser = bundle.getString("NAME");
        } else {
            nameUser = "Pedro";
            Toast.makeText(this, "Hubo un error con el nombre", Toast.LENGTH_LONG).show();
        }

        // Agregando Listening al SeekBar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView tvEdad = (TextView) findViewById(R.id.tvEdad);
                tvEdad.setText("Edad: " + progress);

                if(progress > 16 && progress < 60) {
                    btnContinue.setVisibility(View.VISIBLE);
                } else {
                    btnContinue.setVisibility(View.GONE);
                    if(toastPrev != null) {
                        toastPrev.cancel();
                    }
                    toastPrev = Toast.makeText(SecondActivity.this, "La edad debe estar entre 16 y 60.", Toast.LENGTH_SHORT);
                    toastPrev.show();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Agregando listening del button
        btnContinue.setOnClickListener(view -> {

            int rbResult;
            int age = seekBar.getProgress();

            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.rbGreeting: rbResult = 0; break;
                case R.id.rbFarewell: rbResult = 1; break;
                default: rbResult = -1;
            }

            if (age > 16 && age < 60) {
                if(rbResult != -1) {

                    Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                    intent.putExtra("NAME", nameUser);
                    intent.putExtra("TYPE", rbResult);
                    intent.putExtra("AGE", age);
                    startActivity(intent);

                } else {
                    Toast.makeText(SecondActivity.this, "Debe colocar que tipo de mensaje enviar.", Toast.LENGTH_LONG).show();
                }
            }

        });

    }
}