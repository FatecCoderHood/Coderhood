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
                String sql = "SELECT estacao.nome AS nomeEstacao, estacao.siglacidade AS siglaCidade, cidade.nome AS nomeCidade FROM estacao JOIN cidade ON estacao.siglacidade = cidade.sigla";

                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql);

                 while (rs.next()) {
                     String nomeEstacao = rs.getString("nomeEstacao");
                     String siglaCidade = rs.getString("siglaCidade");
                     String nomeCidade = rs.getString("nomeCidade");
                     String dataPrimeiroRegistro = rs.getDate("data_primeiro_registro").toString();
                     String dataUltimoRegistro = rs.getDate("data_ultimo_registro").toString();
                     estacoes.add(new String[] {nomeEstacao, siglaCidade, nomeCidade, dataPrimeiroRegistro, dataUltimoRegistro});
                 }
            }
        } catch (Exception e) {
            System.err.format(" ");
        }
        return estacoes;
    }

}
