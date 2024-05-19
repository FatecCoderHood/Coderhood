package com.example.dadosmeteorologicos.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import java.sql.Statement;

public class BoxPlotSQL extends IniciaBanco {

    private Connection conn;

    public BoxPlotSQL() {
        this.conn = super.conectarBanco();
    }

    public List<String[]> getEstacoesMenuItem() {
        List<String[]> estacoes = new ArrayList<String[]>();
        try {
            if (conn != null) {

                String sql = "SELECT estacao.numero AS numeroEstacao,"+
                    "estacao.siglacidade AS siglaCidade," +
                    "cidade.nome AS nomeCidade,"+
                    "MIN(registro.data) AS dataMinima, MAX(registro.data) AS dataMaxima " +
                    "FROM estacao JOIN cidade ON estacao.siglacidade = cidade.sigla " +
                    "JOIN registro ON estacao.siglacidade = registro.siglacidade " +
                    "GROUP BY estacao.numero, estacao.siglacidade, cidade.nome";

                
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                
                while (rs.next()) {
                    String numeroEstacao = rs.getString("numeroEstacao");
                    String nomeCidade = rs.getString("nomeCidade");
                    String siglaCidade = rs.getString("siglaCidade");
                    String dataMinima = rs.getString("dataMinima");
                    String dataMaxima = rs.getString("dataMaxima");
                    estacoes.add(new String[]{numeroEstacao, nomeCidade, siglaCidade, dataMinima, dataMaxima});
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estacoes;
    }
}
