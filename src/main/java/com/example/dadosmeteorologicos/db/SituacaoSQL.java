package com.example.dadosmeteorologicos.db;

import com.example.dadosmeteorologicos.model.RegistroSituacao;

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
    public List<RegistroSituacao> buscaSituacaoBanco() {
        List<RegistroSituacao> listaRegistros = new ArrayList<>();
        try {
            if(conn != null){
                String sql = "SELECT r1.* FROM registro r1 " +
                "JOIN (SELECT tipo, siglaCidade, MAX(id) AS maxId FROM registro WHERE suspeito = false GROUP BY tipo, siglaCidade) r2 " +
                "ON r1.tipo = r2.tipo AND r1.siglaCidade = r2.siglaCidade AND r1.id = r2.maxId " +
                "WHERE r1.tipo IN ('temperaturaMedia', 'umidadeMedia', 'VelVento', 'dirVento', 'chuva') AND r1.suspeito = false " +
                "ORDER BY r1.id DESC";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Double valor = rs.getDouble("valor");
    
                    // Check if valor is null
                    if (valor == null) {
                        continue;  // Skip this record
                    }
    
                    int id = rs.getInt("id");
                    LocalDate data = rs.getDate("data").toLocalDate();
                    LocalTime hora = rs.getTime("hora").toLocalTime();
                    String estacao = rs.getString("estacao");
                    String siglaCidade = rs.getString("siglaCidade");
                    String tipo = rs.getString("tipo");
                    boolean suspeito = rs.getBoolean("suspeito");
    
                    RegistroSituacao registroSituacao = new RegistroSituacao();
                    registroSituacao.setId(id);
                    registroSituacao.setData(data);
                    registroSituacao.setHora(hora);
                    registroSituacao.setEstacao(estacao);
                    registroSituacao.setSiglaCidade(siglaCidade);
                    registroSituacao.setSuspeito(suspeito);

                switch (tipo) {
                    case "temperaturaMedia":
                        registroSituacao.setTemperaturaMedia(valor);
                        break;
                    case "umidadeMedia":
                        registroSituacao.setUmidadeMedia(valor);
                        break;
                    case "VelVento":
                        registroSituacao.setVelVento(valor);
                        break;
                    case "dirVento":
                        registroSituacao.setDirVento(valor);
                        break;
                    case "chuva":
                        registroSituacao.setChuva(valor);
                        break;
                }

                listaRegistros.add(registroSituacao);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return listaRegistros;
}
}

