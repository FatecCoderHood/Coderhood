package com.example.dadosmeteorologicos.exceptions;

public class NomeCidadeJaExisteNoBanco extends RuntimeException {
    public NomeCidadeJaExisteNoBanco(String message) {
        super(message);
    }
}
