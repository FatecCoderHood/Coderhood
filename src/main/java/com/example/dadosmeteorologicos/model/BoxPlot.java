package com.example.dadosmeteorologicos.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BoxPlot {
    private LocalDate dataSelecionada;
    private String numeroEstacao;
    private String cidadeEstacao;
    private String siglaCidade;
    private String cidadeEsigla;
    private ValoresBoxPlot valoresBoxPlot;

    public BoxPlot(LocalDate dataSelecionada, String numeroEstacao, String cidadeEstacao, String siglaCidade) {
        this.dataSelecionada = dataSelecionada;
        this.numeroEstacao = numeroEstacao;
        this.cidadeEstacao = cidadeEstacao;
        this.siglaCidade = siglaCidade;
        this.cidadeEsigla = cidadeEstacao + " - " + siglaCidade;
    }

        public BoxPlot(ValoresBoxPlot valoresBoxPlot) {
        this.valoresBoxPlot = valoresBoxPlot;
    }

}
