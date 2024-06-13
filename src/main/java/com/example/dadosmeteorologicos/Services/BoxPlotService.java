package com.example.dadosmeteorologicos.Services;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.example.dadosmeteorologicos.db.BoxPlotSQL;

import javafx.scene.shape.Box;

public class BoxPlotService {

    private BoxPlotSQL banco;

    public BoxPlotService() {
        this.banco = new BoxPlotSQL();
    }

    public BoxPlotService(Connection conn) {
        this.banco = new BoxPlotSQL(conn);
    }

    // Método para obter uma lista de estações do banco de dados
    public List<String[]> getEstacoesDoBancoDeDados(){
        // Cria uma nova instância da classe BoxPlotSQL
        banco.conectarBanco();
        // Chama o método getEstacoesMenuItem para obter a lista de estações
        List<String[]> estacoes = banco.getEstacoesMenuItem();
        // Fecha a conexão com o banco de dados
        banco.fecharConexao();
        // Retorna a lista de estações
        return estacoes;
    }

    // Método para obter os dados de um boxplot
    public Map<String, List<String>> getBoxPlotDados(int numeroEstacao, LocalDate data){
        // Cria uma nova instância da classe BoxPlotSQL
        banco.conectarBanco();
        // Chama o método getBoxPlotDados para obter os dados do boxplot
        Map<String, List<String>> boxPlotDados = banco.getBoxPlotDados(numeroEstacao, data);
        // Fecha a conexão com o banco de dados
        banco.fecharConexao();
        // Retorna os dados do boxplot
        return boxPlotDados;}
}
