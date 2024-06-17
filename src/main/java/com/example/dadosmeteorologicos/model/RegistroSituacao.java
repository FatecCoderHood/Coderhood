package com.example.dadosmeteorologicos.model;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistroSituacao {
    private int id;
    private String siglaCidade;
    private String cidadeESigla;

    private LocalDate dataTemperaturaMedia;
    private LocalTime horaTemperaturaMedia;
    private String temperaturaMedia;

    private LocalDate dataUmidadeMedia;
    private LocalTime horaUmidadeMedia;
    private String umidadeMedia;

    private LocalDate dataVelVento;
    private LocalTime horaVelVento;
    private String velVento;

    private LocalDate dataDirVento;
    private LocalTime horaDirVento;
    private String dirVento;

    private LocalDate dataChuva;
    private LocalTime horaChuva;
    private String chuva;


    public void filtraRegistro(){

        if (this.getTemperaturaMedia() == null) this.setTemperaturaMedia("--");
        if (this.getUmidadeMedia() == null) this.setUmidadeMedia("--");
        if (this.getVelVento() == null) this.setVelVento("--");
        if (this.getDirVento() == null) this.setDirVento("--");
        if (this.getChuva() == null) this.setChuva("--");
        
    }
}
