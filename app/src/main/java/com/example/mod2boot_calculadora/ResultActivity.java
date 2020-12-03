package com.example.mod2boot_calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    double salarioBruto = 0;
    int numDependentes = 0;
    double outrosDescontos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        recebeValores();
        botaoVoltar();
        calculaValores();
    }

    private void recebeValores () {
        Intent intentValoresMain = getIntent();

        salarioBruto = intentValoresMain.getDoubleExtra(MainActivity.SALARIO, 0);
        numDependentes = intentValoresMain.getIntExtra(MainActivity.DEPENDENTES, 0);
        outrosDescontos = intentValoresMain.getDoubleExtra(MainActivity.DESCONTOS, 0);
    }

    private void botaoVoltar() {
        Button btnBack = (Button)findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBotaoBck = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentBotaoBck);
            }
        });
    }

    private void calculaValores () {
        TextView valSalarioBruto = findViewById(R.id.valSalBruto);
        TextView valINSS = findViewById(R.id.valINSS);
        TextView valIRRF = findViewById(R.id.valIRRF);
        TextView valOutrosDescontos = findViewById(R.id.valOutDesc);
        TextView valSalarioLiquido = findViewById(R.id.valSalLiq);
        TextView valPorcentagemDesconto = findViewById(R.id.valDesc);

        valSalarioBruto.setText(String.format("%.2f",salarioBruto));
        valINSS.setText(String.format("%.2f", CalculoSalario.CalculoINSS(salarioBruto)));
        valIRRF.setText(String.format("%.2f", CalculoSalario.CalculoIRRF(salarioBruto, numDependentes)));
        valOutrosDescontos.setText(String.format("%.2f",outrosDescontos));
        valSalarioLiquido.setText(String.format("%.2f", CalculoSalario.CalculoSalLiquido(salarioBruto, numDependentes, outrosDescontos)));
        valPorcentagemDesconto.setText(String.format("%.2f", CalculoSalario.PorcentagemDesc(salarioBruto, numDependentes, outrosDescontos)) + "%");
    }
}