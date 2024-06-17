package com.example.dadosmeteorologicos.model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistroValorMedio {
    private LocalDate data;
    private LocalTime hora;
    private String siglaCidade;
    private List<ValorMedioInfo> valorMedioInfos;


    public void carregarInfoValores(ResultSet rs) {
        this.valorMedioInfos = new ValorMedioInfo().adicionarInfo(rs);
    }



}
