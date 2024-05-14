package com.example.dadosmeteorologicos.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.dadosmeteorologicos.db.SituacaoSQL;
import com.example.dadosmeteorologicos.model.RegistroSituacao;

public class SituacaoService {

    // Método para buscar registros de situação
    public List<RegistroSituacao> buscaSituacaoService() {
        SituacaoSQL situacaoSQL = new SituacaoSQL();
        List<RegistroSituacao> listaRegistros = situacaoSQL.buscaSituacaoBanco();
        situacaoSQL.fecharConexao();
        return listaRegistros;     
    }

    public List<RegistroSituacao> transformaRegistros(List<RegistroSituacao> listaRegistros) {
    // Cria um mapa para agrupar os registros
    Map<String, RegistroSituacao> mapaRegistros = new HashMap<>();

    for (RegistroSituacao registro : listaRegistros) {
        // Cria a chave para o mapa
        String chave = registro.getSiglaCidade() + registro.getData().toString() + registro.getHora().toString();

        // Obtém o registro da tabela para a chave, ou cria um novo se não existir
        RegistroSituacao registroTabela = mapaRegistros.getOrDefault(chave, new RegistroSituacao());

        // Adiciona o valor ao registro da tabela
        switch (registro.getTipo()) {
            case "temperaturaMedia":
                registroTabela.setTemperaturaMedia(registro.getValor());
                break;
            case "umidadeMedia":
                registroTabela.setUmidadeMedia(registro.getValor());
                break;
            case "VelVento":
                registroTabela.setVelVento(registro.getValor());
                break;
            case "dirVento":
                registroTabela.setDirVento(registro.getValor());
                break;
            case "chuva":
                registroTabela.setChuva(registro.getValor());
                break;
        }

        // Adiciona o registro da tabela ao mapa
        mapaRegistros.put(chave, registroTabela);
    }

    // Retorna a lista de registros da tabela
    return new ArrayList<>(mapaRegistros.values());
}
    
}
