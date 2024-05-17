package com.example.dadosmeteorologicos.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.dadosmeteorologicos.db.SituacaoSQL;
import com.example.dadosmeteorologicos.model.RegistroSituacao;
import com.example.dadosmeteorologicos.model.Cidade;

public class SituacaoService {

    public Map<Cidade, RegistroSituacao> getRegistroSituacao(List<Cidade> cidades){
        SituacaoSQL situacaoSQL = new SituacaoSQL();
        Map<Cidade, RegistroSituacao> registroSituacaoPorCidade = new HashMap<>();
        for (Cidade cidade : cidades) {
            RegistroSituacao registro = situacaoSQL.getRegistroSituacao(cidade);
            registroSituacaoPorCidade.put(cidade, registro);
        }
        return registroSituacaoPorCidade;
    }
}
