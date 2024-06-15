package com.example.dadosmeteorologicos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Estacao {
    private int id;
    private String numero;
    private String siglaCidade;
    private String nome;
    private String descricao;
    private Double latitude;
    private Double longitude;
    private EstacaoDetalhes estacaoDetalhes;


    public Estacao(String numero, String siglaCidade) {
        this.numero = numero;
        this.siglaCidade = siglaCidade;
    }

    public Estacao(String numeroEstacao, String siglaCidade, EstacaoDetalhes estacaoDetalhes) {
        this.numero = numeroEstacao;
        this.siglaCidade = siglaCidade;
        this.estacaoDetalhes = estacaoDetalhes;

    }

}
