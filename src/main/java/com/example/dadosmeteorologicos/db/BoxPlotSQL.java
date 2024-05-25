package com.example.dadosmeteorologicos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Statement;
import java.time.LocalDate;

public class BoxPlotSQL extends IniciaBanco {

    private Connection conn;

    public BoxPlotSQL() {
        this.conn = super.conectarBanco();
    }

    public List<String[]> getEstacoesMenuItem() {
        List<String[]> estacoes = new ArrayList<String[]>();
        try {
            if (conn != null) {

                String sql = "SELECT estacao.numero AS numeroEstacao," +
                        "estacao.siglacidade AS siglaCidade," +
                        "cidade.nome AS nomeCidade," +
                        "MIN(registro.data) AS dataMinima,"+
                        "MAX(registro.data) AS dataMaxima " +
                        "FROM estacao JOIN cidade ON estacao.siglacidade = cidade.sigla " +
                        "JOIN registro ON estacao.numero = registro.estacao " +
                        "GROUP BY estacao.numero, estacao.siglacidade, cidade.nome";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    String numeroEstacao = rs.getString("numeroEstacao");
                    String nomeCidade = rs.getString("nomeCidade");
                    String siglaCidade = rs.getString("siglaCidade");
                    String dataMinima = rs.getString("dataMinima");
                    String dataMaxima = rs.getString("dataMaxima");
                    estacoes.add(new String[] { numeroEstacao, nomeCidade, siglaCidade, dataMinima, dataMaxima });

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estacoes;
    }

    public Map<String, List<String>> getBoxPlotDados(int numeroEstacao, LocalDate data) {
        Map<String, List<String>> boxPlotDados = new HashMap<>();
        boxPlotDados.put("temperaturaMedia", new ArrayList<>());
        boxPlotDados.put("umidadeMedia", new ArrayList<>());
        boxPlotDados.put("velVento", new ArrayList<>());
        boxPlotDados.put("dirVento", new ArrayList<>());
        boxPlotDados.put("chuva", new ArrayList<>());

        try {
            if (conn != null) {
                String sql = "SELECT registro.tipo, "+
                "registro.valor "+
                "FROM registro JOIN estacao "+
                "ON registro.siglacidade = estacao.siglacidade "+
                "AND registro.estacao = estacao.numero " +
                "WHERE estacao.numero = ?::VARCHAR AND registro.data = ?";


                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setLong(1, numeroEstacao);
                pstmt.setDate(2, java.sql.Date.valueOf(data));

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    String tipo = rs.getString("tipo");
                    String valor = rs.getString("valor");



                    if (boxPlotDados.containsKey(tipo)) {
                        boxPlotDados.get(tipo).add(valor);
                    }
                }
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        return boxPlotDados;
    }
}
