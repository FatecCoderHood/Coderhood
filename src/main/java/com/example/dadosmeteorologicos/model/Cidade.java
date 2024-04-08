package com.example.dadosmeteorologicos.model;

import lombok.Data;

@Data   
public class Cidade {
    private String nome;
    private String sigla;
    
    public Cidade(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

}
