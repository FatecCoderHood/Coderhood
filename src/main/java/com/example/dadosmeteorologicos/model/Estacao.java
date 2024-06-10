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


    public Estacao(String numero, String siglaCidade) {
        this.numero = numero;
        this.siglaCidade = siglaCidade;
    }

}
