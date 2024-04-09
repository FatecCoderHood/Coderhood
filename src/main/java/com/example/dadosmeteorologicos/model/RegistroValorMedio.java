package com.example.dadosmeteorologicos.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistroValorMedio {
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

    public List<RegistroValorMedio> agruparRegistros(List<Registro> registroBanco){
        Map<LocalDate, Map<LocalTime, List<Registro>>> registrosPorDataHora = new HashMap<>();
    
        for (Registro registro : registroBanco){
            LocalDate data = registro.getData();
            LocalTime hora = registro.getHora();
    
            if (!registrosPorDataHora.containsKey(data)) {
                registrosPorDataHora.put(data, new HashMap<>());
            }
    
            Map<LocalTime, List<Registro>> registrosPorHora = registrosPorDataHora.get(data);
    
            if (!registrosPorHora.containsKey(hora)) {
                registrosPorHora.put(hora, new ArrayList<>());
            }
    
            registrosPorHora.get(hora).add(registro);
        }
    
        List<RegistroValorMedio> registrosAgrupados = new ArrayList<>();
        for (Map<LocalTime, List<Registro>> registrosPorHora : registrosPorDataHora.values()) {
            for (List<Registro> registros : registrosPorHora.values()) {
                RegistroValorMedio registroMedio = new RegistroValorMedio();
                for (Registro registro : registros) {
                    registroMedio.setId(registro.getId());
                    registroMedio.setData(registro.getData());
                    registroMedio.setHora(registro.getHora());
                    registroMedio.setEstacao(registro.getEstacao());
                    registroMedio.setSiglaCidade(registro.getSiglaCidade());
                    switch (registro.getTipo()) {
                        case "temperaturaMedia":
                            registroMedio.setTemperaturaMedia(registro.getValor());
                            break;
                        case "umidadeMedia":
                            registroMedio.setUmidadeMedia(registro.getValor());
                            break;
                        case "velVento":
                            registroMedio.setVelVento(registro.getValor());
                            break;
                        case "dirVento":
                            registroMedio.setDirVento(registro.getValor());
                            break;
                        case "chuva":
                            registroMedio.setChuva(registro.getValor());
                            break;
                    }
                }
                registrosAgrupados.add(registroMedio);
            }
        }
    
        return registrosAgrupados;
    }
}
