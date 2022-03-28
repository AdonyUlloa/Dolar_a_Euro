package com.example.dolaraeuro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RadioButton primeraDivisa = findViewById(R.id.rbtnCambiar);
        RadioButton segundaDivisa = findViewById(R.id.rbtnMantener);
        Button resultado = findViewById(R.id.btnConvertir);
        TextView Conversion = findViewById(R.id.txtvCalc);
        EditText Dollar= findViewById(R.id.txtDollar);
        EditText Euro= findViewById(R.id.txtEuro);
        Button cambio = findViewById(R.id.btnCambio);
        TextView textoeuro = findViewById(R.id.txtvEuro);
        TextView textDivisa = findViewById(R.id.txtvDivisa);



        primeraDivisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                primeraDivisa.isChecked();

                textDivisa.setVisibility(View.INVISIBLE);


                textoeuro.setVisibility(View.VISIBLE);
                Euro.setVisibility(View.VISIBLE);
                cambio.setVisibility(view.VISIBLE);
            }
        });

        segundaDivisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                segundaDivisa.isChecked();

                textoeuro.setVisibility(View.INVISIBLE);

                Euro.setVisibility(View.INVISIBLE);
                cambio.setVisibility(view.INVISIBLE);

                textDivisa.setVisibility(View.VISIBLE);
            }
        });


        //No logre hacer funcionar lo que es la catura del dato en Int
        // y guardalo para usar depues, me gustaria que me diga cual es mi error.
        //El codigo funciona pero no se guarda el dato.

        cambio.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences =
                        getSharedPreferences("MySharedPref",MODE_PRIVATE);

                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("name", String.valueOf(Euro));
                myEdit.commit();

                SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                String s1 = sh.getString("name", "No hay dato");
                textDivisa.setText(s1);
            }
        });

        resultado.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {


                if(primeraDivisa.isChecked()){
                    double EUR = Double.parseDouble(String.valueOf(Euro.getText()));
                    double USD = Double.parseDouble(String.valueOf(Dollar.getText()));


                    Conversion.setVisibility(View.VISIBLE);

                    double ConversionFinal = USD*EUR;
                    Conversion.setText(Double.toString(ConversionFinal));

                }
                else if (segundaDivisa.isChecked()){
                    double USD = Double.parseDouble(String.valueOf(Dollar.getText()));
                    final double DivasEUR=0.90;


                    double ConversionFinal = USD*DivasEUR;
                    Conversion.setText(Double.toString(ConversionFinal));

                }

            }
        });


    }
}