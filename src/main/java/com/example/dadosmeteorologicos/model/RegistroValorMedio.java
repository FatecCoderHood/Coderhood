package com.example.dadosmeteorologicos.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistroValorMedio {
    private LocalDate data;
    private LocalTime hora;
    private String siglaCidade;
    private List<ValorMedioInfo> valorMedioInfos;

    public static List<RegistroValorMedio> calcularMediaPorDataHora(List<RegistroValorMedio> registros) {
        Map<LocalDateTime, List<RegistroValorMedio>> registrosPorDataHora = registros.stream()
            .collect(Collectors.groupingBy(registro -> LocalDateTime.of(registro.getData(), registro.getHora())));
    
        List<RegistroValorMedio> registrosMedia = new ArrayList<>();
    
        for (Map.Entry<LocalDateTime, List<RegistroValorMedio>> entry : registrosPorDataHora.entrySet()) {
            RegistroValorMedio registroMedia = new RegistroValorMedio();
            registroMedia.setSiglaCidade(entry.getValue().get(0).getSiglaCidade());
            registroMedia.setData(entry.getKey().toLocalDate());
            registroMedia.setHora(entry.getKey().toLocalTime());
    
            // Calcular a média dos valores
            List<ValorMedioInfo> valorMedioInfosMedia = new ArrayList<>();
            Set<String> tipos = entry.getValue().stream()
                .flatMap(registro -> registro.getValorMedioInfos().stream())
                .map(ValorMedioInfo::getTipo)
                .collect(Collectors.toSet());
    
            for (String tipo : tipos) {
                double media = entry.getValue().stream()
                    .flatMap(registro -> registro.getValorMedioInfos().stream())
                    .filter(valorMedioInfo -> tipo.equals(valorMedioInfo.getTipo()) && valorMedioInfo.getValor() != null)
                    .mapToDouble(ValorMedioInfo::getValor)
                    .average()
                    .orElse(Double.NaN);
            
                // Se não houver valores, continue para o próximo tipo
                if (Double.isNaN(media)) {
                    continue;
                }
            
                // Arredondar a média para duas casas decimais
                media = Math.round(media * 100.0) / 100.0;
    
                // Criar um novo ValorMedioInfo com a média dos valores
                ValorMedioInfo valorMedioInfoMedia = new ValorMedioInfo();
                valorMedioInfoMedia.setTipo(tipo);
                valorMedioInfoMedia.setValor(media);
    
                valorMedioInfosMedia.add(valorMedioInfoMedia);
            }
    
            // Adicionar os ValorMedioInfos à lista de ValorMedioInfos do RegistroValorMedio
            registroMedia.setValorMedioInfos(valorMedioInfosMedia);
    
            registrosMedia.add(registroMedia);
        }
    
        return registrosMedia;
    }

}
