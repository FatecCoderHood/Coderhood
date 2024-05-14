package com.example.dadosmeteorologicos.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegistroSituacao {
    private int id;
    private LocalDate data;
    private LocalTime hora;
    private String estacao;
    private String siglaCidade;
    private Double temperaturaMedia;
    private Double umidadeMedia;
    private Double velVento;
    private Double dirVento;
    private Double chuva;
    private boolean suspeito;

    //Construtor para montar Registro para o banco
    public RegistroSituacao(LocalDate data, LocalTime hora, String estacao, String siglaCidade, Double temperaturaMedia, Double umidadeMedia, Double velVento, Double dirVento, Double chuva, boolean suspeito) {
        this.data = data;
        this.hora = hora;
        this.estacao = estacao;
        this.siglaCidade = siglaCidade;
        this.temperaturaMedia = temperaturaMedia;
        this.umidadeMedia = umidadeMedia;
        this.velVento = velVento;
        this.dirVento = dirVento;
        this.chuva = chuva;
        this.suspeito = suspeito;
    }

    //Construtor para recuperar do banco
    public RegistroSituacao(int id, LocalDate data, LocalTime hora, String estacao, String siglaCidade, Double temperaturaMedia, Double umidadeMedia, Double velVento, Double dirVento, Double chuva, boolean suspeito) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.estacao = estacao;
        this.siglaCidade = siglaCidade;
        this.temperaturaMedia = temperaturaMedia;
        this.umidadeMedia = umidadeMedia;
        this.velVento = velVento;
        this.dirVento = dirVento;
        this.chuva = chuva;
        this.suspeito = suspeito;
    }

    public RegistroSituacao() {
        
    }

    //getter e setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }

    public String getSiglaCidade() {
        return siglaCidade;
    }

    public void setSiglaCidade(String siglaCidade) {
        this.siglaCidade = siglaCidade;
    }

    public Double getTemperaturaMedia() {
        return temperaturaMedia;
    }

    public void setTemperaturaMedia(Double temperaturaMedia) {
        this.temperaturaMedia = temperaturaMedia;
    }

    public Double getUmidadeMedia() {
        return umidadeMedia;
    }

    public void setUmidadeMedia(Double umidadeMedia) {
        this.umidadeMedia = umidadeMedia;
    }

    public Double getVelVento() {
        return velVento;
    }

    public void setVelVento(Double velVento) {
        this.velVento = velVento;
    }

    public Double getDirVento() {
        return dirVento;
    }

    public void setDirVento(Double dirVento) {
        this.dirVento = dirVento;
    }

    public Double getChuva() {
        return chuva;
    }

    public void setChuva(Double chuva) {
        this.chuva = chuva;
    }

    public boolean isSuspeito() {
        return suspeito;
    }

    public void setSuspeito(boolean suspeito) {
        this.suspeito = suspeito;
    }

    public String getTipo() {
        if (this.temperaturaMedia != null) {
            return "temperaturaMedia";
        } else if (this.umidadeMedia != null) {
            return "umidadeMedia";
        } else if (this.velVento != null) {
            return "velVento";
        } else if (this.dirVento != null) {
            return "dirVento";
        } else if (this.chuva != null) {
            return "chuva";
        } else {
            return null;
        }
    }

    public Double getValor() {
        if (this.temperaturaMedia != null) {
            return this.temperaturaMedia;
        } else if (this.umidadeMedia != null) {
            return this.umidadeMedia;
        } else if (this.velVento != null) {
            return this.velVento;
        } else if (this.dirVento != null) {
            return this.dirVento;
        } else if (this.chuva != null) {
            return this.chuva;
        } else {
            return null;
        }
    }

    

    
}