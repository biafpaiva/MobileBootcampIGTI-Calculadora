package com.example.mod2boot_calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.text.DecimalFormat;

public class CalculoSalario {

    final static double ALIQUOT_INSS_MIN = 0.075;
    final static double ALIQUOT_INSS_2 = 0.09;
    final static double ALIQUOT_INSS_3 = 0.12;
    final static double ALIQUOT_INSS_4 = 0.14;

    public static double CalculoINSS (double salarioBruto) {
        double valorDescINSS = 0;

        if (salarioBruto > 0 && salarioBruto <= 1045)
            valorDescINSS = salarioBruto * ALIQUOT_INSS_MIN;
        else if (salarioBruto > 1045 && salarioBruto <= 2089.60)
            valorDescINSS = ((salarioBruto * ALIQUOT_INSS_2) - 15.67);
        else if (salarioBruto > 2089.60 && salarioBruto <= 3134.40)
            valorDescINSS = ((salarioBruto * ALIQUOT_INSS_3) - 78.36);
        else if (salarioBruto > 3134.40 && salarioBruto <= 6101.06)
            valorDescINSS = ((salarioBruto * ALIQUOT_INSS_4) - 141.05);
        else if (salarioBruto > 6101.06)
            valorDescINSS = 713.10;

        return valorDescINSS;
    }

    final static double ALIQUOT_IRRF_MIN = 0;
    final static double ALIQUOT_IRRF_2 = 0.075;
    final static double ALIQUOT_IRRF_3 = 0.15;
    final static double ALIQUOT_IRRF_4 = 0.225;
    final static double ALIQUOT_IRRF_5 = 0.275;

    final static double TAXA_DEPENDENTES = 189.59;

    public static double CalculoIRRF (double salarioBruto, int numDependentes) {
        double valorDescIRRF = 0;

        double baseSal = ((salarioBruto - CalculoINSS(salarioBruto)) - (numDependentes * TAXA_DEPENDENTES));

        if (baseSal > 0 && baseSal <= 1903.98)
            valorDescIRRF = 0;
        else if (baseSal > 1903.98 && baseSal <= 2826.65)
            valorDescIRRF = ((baseSal * ALIQUOT_IRRF_2) - 142.80);
        else if (baseSal > 2826.65 && baseSal <= 3751.05)
            valorDescIRRF = ((baseSal * ALIQUOT_IRRF_3) - 354.80);
        else if (baseSal > 3751.05 && baseSal <= 4664.68)
            valorDescIRRF = ((baseSal * ALIQUOT_IRRF_4) - 636.13);
        else if (baseSal > 4664.68)
            valorDescIRRF = ((baseSal * ALIQUOT_IRRF_5) - 869.36);

        return valorDescIRRF;
    }

    public static double CalculoSalLiquido (double salarioBruto, int numDependentes, double outrosDescontos) {
        return salarioBruto - CalculoINSS(salarioBruto) - CalculoIRRF(salarioBruto, numDependentes) - outrosDescontos;
    }

    public static double PorcentagemDesc(double salarioBruto, int numDependentes, double outrosDescontos) {
        double valorDesc = CalculoINSS(salarioBruto) + CalculoIRRF(salarioBruto,numDependentes) + outrosDescontos;
        double porcentagemDouble = (valorDesc*100) / salarioBruto;
        return porcentagemDouble;
    }
}

