package com.example.dadosmeteorologicos.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ValorMedioInfo {
    private String tipo;
    private Double valor;

    public List<ValorMedioInfo> adicionarInfo(ResultSet rs){
        List<ValorMedioInfo> valorMedioInfos = new ArrayList<>();
        try {
            ValorMedioInfo temperaturaMedia = new ValorMedioInfo();
            temperaturaMedia.setTipo("temperaturaMedia");
            temperaturaMedia.setValor(rs.getDouble("temperaturaMedia"));
            valorMedioInfos.add(temperaturaMedia);

            ValorMedioInfo dirVento = new ValorMedioInfo();
            dirVento.setTipo("dirVento");
            dirVento.setValor(rs.getDouble("dirvento"));
            valorMedioInfos.add(dirVento);

            ValorMedioInfo umidadeMedia = new ValorMedioInfo();
            umidadeMedia.setTipo("umidadeMedia");
            umidadeMedia.setValor(rs.getDouble("umidadeMedia"));
            valorMedioInfos.add(umidadeMedia);

            ValorMedioInfo velVento = new ValorMedioInfo();
            velVento.setTipo("velVento");
            velVento.setValor(rs.getDouble("velVento"));
            valorMedioInfos.add(velVento);

            ValorMedioInfo chuva = new ValorMedioInfo();
            chuva.setTipo("chuva");
            chuva.setValor(rs.getDouble("chuva"));
            valorMedioInfos.add(chuva);
        } catch (Exception e) {
            System.err.format(" valor medio info: %s\n%s", e.getMessage());
        }
        return valorMedioInfos;
    }
}
