package com.example.dadosmeteorologicos.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.dadosmeteorologicos.model.RegistroDto;

import lombok.ToString;


public class RegistroDtoService {
    @ToString.Exclude
    private static DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @ToString.Exclude
    private static DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HHmm");
    private static final double TEMPERATURA_MAXIMA_C = 60.0;
    private static final double TEMPERATURA_MINIMA_C = -20.0;

    // Umidade em percentual
    private static final double UMIDADE_MAXIMA = 100.0;
    private static final double UMIDADE_MINIMA = 0.0;

    // Velocidade do vento em m/s
    private static final double VELOCIDADE_VENTO_MAXIMA = 25.5;
    private static final double VELOCIDADE_VENTO_MINIMA = 0.0;

    // Direção do vento em graus
    private static final double DIRECAO_VENTO_MAX = 360.0;
    private static final double DIRECAO_VENTO_MIN = 0.0;

    // Chuva em mm
    private static final double CHUVA_MAXIMA = 400.0;
    private static final double CHUVA_MINIMA = 0.0;

    private static Double tentarParseDouble(String s) {
        try {
            return s == null ? null : Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static List<RegistroDto> criaRegistroDto(List<String[]> csvFiltrado) {
        List<RegistroDto> registros = new ArrayList<>();
        for (String[] linha : csvFiltrado) {
            String codigoCidade = linha[0];
            String codigoEstacao = linha[1];
            LocalDate data = LocalDate.parse(linha[2], formatoData);
            LocalTime hora = LocalTime.parse(linha[3], formatoHora);
            Double temperaturaMedia = tentarParseDouble(linha[4]);
            Double umidadeMedia = tentarParseDouble(linha[5]);
            Double velVento = tentarParseDouble(linha[6]);
            Double dirVento = tentarParseDouble(linha[7]);
            Double chuva = tentarParseDouble(linha[8]);
            RegistroDto registro = new RegistroDto(codigoCidade, codigoEstacao, data, hora, temperaturaMedia, umidadeMedia, velVento, dirVento, chuva, 
            temperaturaMedia != null && (temperaturaMedia < TEMPERATURA_MINIMA_C || temperaturaMedia > TEMPERATURA_MAXIMA_C),
            umidadeMedia != null && (umidadeMedia < UMIDADE_MINIMA || umidadeMedia > UMIDADE_MAXIMA),
            velVento != null && (velVento < VELOCIDADE_VENTO_MINIMA || velVento > VELOCIDADE_VENTO_MAXIMA),
            dirVento != null && (dirVento < DIRECAO_VENTO_MIN || dirVento > DIRECAO_VENTO_MAX),
            chuva != null && (chuva < CHUVA_MINIMA || chuva > CHUVA_MAXIMA));
            registros.add(registro);
        }
        return registros;
    }

}
