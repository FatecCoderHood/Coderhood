package com.example.dadosmeteorologicos.model;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class RegistroSituacao {
    private int id;
    private LocalDate data;
    private LocalTime hora;
    private String siglaCidade;
    private String cidadeESigla;
    private Double temperaturaMedia;
    private Double umidadeMedia;
    private Double velVento;
    private Double dirVento;
    private Double chuva;

    // Construtor para montar Registro para o banco
    public RegistroSituacao(LocalDate data, LocalTime hora, String siglaCidade, Double temperaturaMedia, Double umidadeMedia, Double velVento, Double dirVento, Double chuva) {
        this.data = data;
        this.hora = hora;
        this.siglaCidade = siglaCidade;
        this.temperaturaMedia = temperaturaMedia;
        this.umidadeMedia = umidadeMedia;
        this.velVento = velVento;
        this.dirVento = dirVento;
        this.chuva = chuva;
    }

    // Construtor para recuperar do banco
    public RegistroSituacao(int id, LocalDate data, LocalTime hora, String siglaCidade, Double temperaturaMedia, Double umidadeMedia, Double velVento, Double dirVento, Double chuva) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.siglaCidade = siglaCidade;
        this.temperaturaMedia = temperaturaMedia;
        this.umidadeMedia = umidadeMedia;
        this.velVento = velVento;
        this.dirVento = dirVento;
        this.chuva = chuva;
    }

}
