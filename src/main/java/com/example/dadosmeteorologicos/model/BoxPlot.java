package com.example.dadosmeteorologicos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoxPlot {

    private String cidade;
    private String estacao;
    private String data;
    private String temperatura;
    private String umidade;
    private String velVento;
    private String dirVento;
    private String chuva;

}
