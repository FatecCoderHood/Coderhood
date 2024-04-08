package com.example.dadosmeteorologicos.model;

import lombok.Data;

@Data
public class Estacao { 
    private int id;
    private String numero;
    private String siglaCidade;


    Estacao(String numero, String siglaCidade) {
        this.numero = numero;
        this.siglaCidade = siglaCidade;
    }

}
