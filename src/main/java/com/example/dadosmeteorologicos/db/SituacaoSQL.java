package com.example.dadosmeteorologicos.db;

import com.example.dadosmeteorologicos.model.Registro;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SituacaoSQL extends IniciaBanco {
    // Atributo de conexão
    protected Connection conn;

    public SituacaoSQL() {
        conn = super.conectarBanco();
    }

    // Método para buscar registros de situação no banco
    public List<Registro> buscaSituacaoBanco() {
        // Lista de "situação"
        List<Registro> listaRegistros = new ArrayList<>();
        // Tenta buscar os registros  no banco
        try {
            if(conn != null){
                // Query para buscar os registros 
                String sql = "SELECT r1.* FROM registro r1 " +
                "JOIN (SELECT tipo, siglaCidade, MAX(id) AS maxId FROM registro WHERE suspeito = false AND valor IS NOT NULL AND tipo IS NOT NULL AND siglaCidade IS NOT NULL GROUP BY tipo, siglaCidade) r2 " +
                "ON r1.tipo = r2.tipo AND r1.siglaCidade = r2.siglaCidade AND r1.id = r2.maxId " +
                "WHERE r1.tipo IN ('temperaturaMedia', 'umidadeMedia', 'VelVento', 'dirVento', 'chuva') AND r1.suspeito = false AND r1.valor IS NOT NULL AND r1.tipo IS NOT NULL AND r1.siglaCidade IS NOT NULL";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                // Adiciona os registros na lista
                while (rs.next()) {
                    int id = rs.getInt("id");
                    LocalDate data = rs.getDate("data").toLocalDate();
                    LocalTime hora = rs.getTime("hora").toLocalTime();
                    String estacao = rs.getString("estacao");
                    String siglaCidade = rs.getString("siglaCidade");
                    String tipo = rs.getString("tipo");
                    Double valor = rs.getDouble("valor");
                    boolean suspeito = rs.getBoolean("suspeito");
                    Registro registro = new Registro(id, data, hora, estacao, siglaCidade, tipo, valor, suspeito);
                    listaRegistros.add(registro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna a lista de registros
        return listaRegistros;
    }
}

