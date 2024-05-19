package com.example.dadosmeteorologicos.model;


import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValoresBoxPlot {

    private String tipo;
    private double min;
    private double q1;
    private double mediana;
    private double q3;
    private double max;

    public ValoresBoxPlot(String tipo, double[] data) {
        this.tipo = tipo;
        if (data.length < 3) {
            data = new double[5];
            Arrays.fill(data, 0);
        }
        Arrays.sort(data);
        this.min = data[0];
        this.q1 = getPercentil(data, 25);
        this.mediana = getPercentil(data, 50);
        this.q3 = getPercentil(data, 75);
        this.max = data[data.length - 1];
    }

    private double getPercentil(double[] data, double percentil) {
        int index = (int) Math.ceil(percentil / 100.0 * (data.length - 1));
        if (data.length % 2 == 0) {
            // Se o número de elementos é par, a mediana é a média dos dois elementos do meio
            return (data[index] + data[index - 1]) / 2.0;
        } else {
            // Se o número de elementos é ímpar, a mediana é o elemento do meio
            return data[index];
        }
    }

}