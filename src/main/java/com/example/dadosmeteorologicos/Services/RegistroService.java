package com.example.dadosmeteorologicos.Services;

public class RegistroService {
    private static final Double TEMPERATURA_MAXIMA_C = 60.0;
    private static final Double TEMPERATURA_MINIMA_C = -20.0;

    // Umidade em percentual
    private static final Double UMIDADE_MAXIMA = 100.0;
    private static final Double UMIDADE_MINIMA = 0.0;

    // Velocidade do vento em m/s
    private static final Double VELOCIDADE_VENTO_MAXIMA = 25.5;
    private static final Double VELOCIDADE_VENTO_MINIMA = 0.0;

    // Direção do vento em graus
    private static final Double DIRECAO_VENTO_MAX = 360.0;
    private static final Double DIRECAO_VENTO_MIN = 0.0;

    // Chuva em mm
    private static final Double CHUVA_MAXIMA = 400.0;
    private static final Double CHUVA_MINIMA = 0.0;

    public boolean validarTemperatura(Double temperatura) {
        if (temperatura == null) return false;
        return !(temperatura >= TEMPERATURA_MINIMA_C && temperatura <= TEMPERATURA_MAXIMA_C);
    }

    public boolean validarUmidade(Double umidade) {
        if (umidade == null) return false;
        return !(umidade >= UMIDADE_MINIMA && umidade <= UMIDADE_MAXIMA);
    }

    public boolean validarVelocidadeVento(Double velocidadeVento) {
        if (velocidadeVento == null) return false;
        return !(velocidadeVento >= VELOCIDADE_VENTO_MINIMA && velocidadeVento <= VELOCIDADE_VENTO_MAXIMA);
    }

    public boolean validarDirecaoVento(Double direcaoVento) {
        if (direcaoVento == null) return false;
        return !(direcaoVento >= DIRECAO_VENTO_MIN && direcaoVento <= DIRECAO_VENTO_MAX);
    }

    public boolean validarChuva(Double chuva) {
        if (chuva == null) return false;
        return !(chuva >= CHUVA_MINIMA && chuva <= CHUVA_MAXIMA);
    }
}
