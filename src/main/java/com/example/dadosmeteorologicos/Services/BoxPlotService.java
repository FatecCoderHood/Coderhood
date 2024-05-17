package com.example.dadosmeteorologicos.Services;

import java.util.List;

import com.example.dadosmeteorologicos.db.BoxPlotSQL;

public class BoxPlotService {

    // Método para obter uma lista de estações do banco de dados
    public List<String[]> getEstacoesDoBancoDeDados(){
        // Cria uma nova instância da classe BoxPlotSQL
        BoxPlotSQL banco = new BoxPlotSQL();
        // Chama o método getEstacoesMenuItem para obter a lista de estações
        List<String[]> estacoes = banco.getEstacoesMenuItem();
        // Fecha a conexão com o banco de dados
        banco.fecharConexao();
        // Retorna a lista de estações
        return estacoes;
    }
}
