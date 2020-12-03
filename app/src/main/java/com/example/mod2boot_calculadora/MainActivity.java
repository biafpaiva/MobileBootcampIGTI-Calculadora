package com.example.mod2boot_calculadora;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String SALARIO = "com.example.mod2boot_calculadora.SALARIO";
    public static final String DEPENDENTES = "com.example.mod2boot_calculadora.DEPENDENTES";
    public static final String DESCONTOS = "com.example.mod2boot_calculadora.DESCONTOS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText campSalBrt = (EditText)findViewById(R.id.campSalBrut);
        final EditText campNumDep = (EditText)findViewById(R.id.campNumDep);
        final EditText campOutDesc = (EditText)findViewById(R.id.campOutDesc);

        Button btnCalc = (Button)findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (campSalBrt.getText().length() > 0) {
                    Intent intentMain = new Intent(getApplicationContext(), ResultActivity.class);

                    intentMain.putExtra(SALARIO, Double.parseDouble(campSalBrt.getText().toString()));
                    intentMain.putExtra(DEPENDENTES, Integer.parseInt(campNumDep.getText().toString().equals("") ? "0" : campNumDep.getText().toString()));
                    intentMain.putExtra(DESCONTOS, Double.parseDouble(campOutDesc.getText().toString().equals("") ? "0" : campOutDesc.getText().toString()));
                    startActivity(intentMain);
                } else {
                    new AlertDialog.Builder(MainActivity.this).setTitle("Erro")
                            .setMessage("Preencha o campo de 'Salário Bruto'.")
                            .setNeutralButton("OK", null).show();
                }
            }
        });
    }
}