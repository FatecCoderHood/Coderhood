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
    private String latitude;
    private String longitude;


    Estacao(String numero, String siglaCidade) {
        this.numero = numero;
        this.siglaCidade = siglaCidade;
    }

}
