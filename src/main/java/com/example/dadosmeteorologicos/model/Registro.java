package com.example.dadosmeteorologicos.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Registro {
    private int id;
    private LocalDate data;
    private LocalTime hora;
    private String estacao;
    private String siglaCidade;
    private String tipo;
    private Double valor;
    private boolean suspeito;

    //Construtor para montar Registro para o banco
    public Registro(LocalDate data, LocalTime hora, String estacao, String siglaCidade, String tipo, Double valor, boolean suspeito) {
        this.data = data;
        this.hora = hora;
        this.estacao = estacao;
        this.siglaCidade = siglaCidade;
        this.tipo = tipo;
        this.valor = valor;
        this.suspeito = suspeito;
    }

    //Construtor para recuperar do banco
    public Registro(int id, LocalDate data, LocalTime hora, String estacao, String siglaCidade, String tipo, Double valor, boolean suspeito) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.estacao = estacao;
        this.siglaCidade = siglaCidade;
        this.tipo = tipo;
        this.valor = valor;
        this.suspeito = suspeito;
    }  

}
