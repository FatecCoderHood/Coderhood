package com.example.dadosmeteorologicos.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistroValorMedio {
    private LocalDate data;
    private LocalTime hora;
    private String estacao;
    private String siglaCidade;
    private List<ValorMedioInfo> valorMedioInfos;

    public static List<RegistroValorMedio> agruparRegistrosPorData(List<Registro> listaRegistrosBD) {
        Map<LocalDate, List<Registro>> registrosPorData = listaRegistrosBD.stream()
            .collect(Collectors.groupingBy(Registro::getData));

        List<RegistroValorMedio> registrosValorMedio = new ArrayList<>();

        for (Map.Entry<LocalDate, List<Registro>> entry : registrosPorData.entrySet()) {
            RegistroValorMedio registroValorMedio = new RegistroValorMedio();
            registroValorMedio.setData(entry.getKey());
            registroValorMedio.setHora(entry.getValue().get(0).getHora());
            registroValorMedio.setValorMedioInfos(transformarEmValorMedioInfo(entry.getValue()));
            registrosValorMedio.add(registroValorMedio);
        }

        return registrosValorMedio;
    }

    private static List<ValorMedioInfo> transformarEmValorMedioInfo(List<Registro> registros) {
        List<ValorMedioInfo> valorMedioInfos = new ArrayList<>();

        for (Registro registro : registros) {
            ValorMedioInfo valorMedioInfo = new ValorMedioInfo();
            valorMedioInfo.setId(registro.getId());
            valorMedioInfo.setTipo(registro.getTipo());
            valorMedioInfo.setValor(registro.getValor());
            valorMedioInfos.add(valorMedioInfo);
        }

        return valorMedioInfos;
    }
}
