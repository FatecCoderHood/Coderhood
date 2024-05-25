package com.example.dadosmeteorologicos.model;


import java.util.Arrays;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValoresBoxPlot {

    private String tipo;
    private String min;
    private String q1;
    private String mediana;
    private String q3;
    private String max;

public ValoresBoxPlot(String tipo, Double[] data) {
    this.tipo = tipo;
    if (data.length < 3) {
        this.min = this.q1 = this.mediana = this.q3 = this.max = "--";
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("O dados de " + tipo + " não tem pelo menos 3 registros");
            alert.showAndWait();
        });
    } else {
        Arrays.sort(data);
        this.min = String.format("%.2f", data[0]);
        this.q1 = String.format("%.2f", getPercentil(data, 25.0));
        this.mediana = String.format("%.2f", getPercentil(data, 50.0));
        this.q3 = String.format("%.2f", getPercentil(data, 75.0));
        this.max = String.format("%.2f", data[data.length - 1]);
    }
}

    private Double getPercentil(Double[] data, Double percentil) {
        int index = (int) Math.ceil(percentil / 100.0 * (data.length - 1));
        if (data.length % 2 == 0) {
            // Se o número de elementos é par, a mediana é a média dos dois elementos do meio
            return (data[index] + data[index - 1]) / 2.0;
        } else {
            // Se o número de elementos é ímpar, a mediana é o elemento do meio
            return data[index];
        }
    }


    public String[] converteValorParaCsv(ValoresBoxPlot valoresBoxPlot){
        String[] resultado = {tipo, min.replace(".", ","), q1.replace(".", ","), mediana.replace(".", ","), 
        q3.replace(".", ","), max.replace(".", ",")};

        return resultado;
    }
}