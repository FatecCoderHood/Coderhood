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
        List<String[]> estacoes = new ArrayList<>();
        try {
            if (conn != null) {

                String sql = "SELECT estacao.nome AS nomeEstacao, estacao.siglacidade AS siglaCidade, cidade.nome AS nomeCidade, MIN(registro.data) AS dataMinima, MAX(registro.data) AS dataMaxima FROM estacao JOIN cidade ON estacao.siglacidade = cidade.sigla JOIN registro ON estacao.siglacidade = registro.siglacidade GROUP BY estacao.nome, estacao.siglacidade, cidade.nome";

                

                try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    String nomeEstacao = rs.getString("nomeEstacao");
                    String siglaCidade = rs.getString("siglaCidade");
                    String nomeCidade = rs.getString("nomeCidade");
                    String dataPrimeiroRegistro = rs.getDate("dataMinima").toString();
                    String dataUltimoRegistro = rs.getDate("dataMaxima").toString();
                    estacoes.add(new String[] {nomeEstacao, siglaCidade, nomeCidade, dataPrimeiroRegistro, dataUltimoRegistro});
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estacoes;
    }

}
