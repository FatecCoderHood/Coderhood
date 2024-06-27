package com.example.dadosmeteorologicos.model;

import lombok.Data;

@Data
public class CidadeDetalhes {

    String dataPrimeiroRegistro;
    String dataUltimoRegistro;

    public CidadeDetalhes(String dataPrimeiroRegistro, String dataUltimoRegistro) {
        this.dataPrimeiroRegistro = dataPrimeiroRegistro;
        this.dataUltimoRegistro = dataUltimoRegistro;
    }

}
