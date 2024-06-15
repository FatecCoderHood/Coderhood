package com.example.dadosmeteorologicos.model;

import lombok.Data;

@Data
public class EstacaoDetalhes {
    
    private String dataPrimeiroRegistro;
    private String dataUltimoRegistro;
    private String nomeCidade;

    public EstacaoDetalhes(String dataPrimeiroRegistro, String dataUltimoRegistro, String nomeCidade) {
        this.dataPrimeiroRegistro = dataPrimeiroRegistro;
        this.dataUltimoRegistro = dataUltimoRegistro;
        this.nomeCidade = nomeCidade;
    }

    
}
