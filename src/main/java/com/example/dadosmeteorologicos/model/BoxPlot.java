package com.example.dadosmeteorologicos.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BoxPlot {
    private LocalDate dataSelecionada;
    private String numeroEstacao;
    private String nomeCidade;
    private String siglaCidade;
    private String cidadeEsigla;
    private ValoresBoxPlot valoresBoxPlot;

    public BoxPlot(LocalDate dataSelecionada, String numeroEstacao, String nomeCidade, String siglaCidade) {
        this.dataSelecionada = dataSelecionada;
        this.numeroEstacao = numeroEstacao;
        this.nomeCidade = nomeCidade;
        this.siglaCidade = siglaCidade;
        this.cidadeEsigla = nomeCidade + " - " + siglaCidade;
    }

}
