package com.example.dadosmeteorologicos.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.dadosmeteorologicos.model.Registro;
import com.example.dadosmeteorologicos.model.RegistroValorMedio;

public class ValorMedioSQL extends IniciaBanco {

    private Connection conn;

    public ValorMedioSQL() {
        this.conn = super.conectarBanco();
    }

     public List<String[]> getCidadesMenuItem() {
        List<String[]> registros = new ArrayList<>();
        try {
            if (conn != null) {
                String sql = "SELECT " +
                            "Cidade.nome AS nome, " +
                            "Cidade.sigla AS sigla, " +
                            "MIN(Registro.data) AS data_primeiro_registro, " +
                            "MAX(Registro.data) AS data_ultimo_registro " +
                            "FROM " +
                                "Cidade " +
                            "JOIN " +
                                "Registro ON Cidade.sigla = Registro.siglaCidade " +
                            "WHERE " +
                                "Registro.siglaCidade = Cidade.sigla " +
                            "GROUP BY " +
                                "Cidade.nome, Cidade.sigla";
    
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
    
                while (rs.next()) {
                    String nome = rs.getString("nome");
                    String sigla = rs.getString("sigla");
                    String dataPrimeiroRegistro = rs.getDate("data_primeiro_registro").toString();
                    String dataUltimoRegistro = rs.getDate("data_ultimo_registro").toString();
                    registros.add(new String[] {nome, sigla, dataPrimeiroRegistro, dataUltimoRegistro});
                }
            }
        } catch (SQLException e) {
            System.err.format(" CIDADES MENU SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return registros;
    }

    public List<RegistroValorMedio> getRelatorioValorMedio(String siglaCidade, Date dataInicial, Date dataFinal){
        List<RegistroValorMedio> relatorioValorMedio = new ArrayList<>();
    
        try {
            if (conn != null) {
                String sql = "SELECT " + 
                "data," + 
                "hora," + 
                "estacao," +
                "siglaCidade," +
                "STRING_AGG(tipo, ', ' ORDER BY tipo) AS tipos," +
                "STRING_AGG(valor::text, ', ' ORDER BY tipo) AS valores," +
                "STRING_AGG(suspeito::text, ', ' ORDER BY tipo) AS suspeitos " +
                "FROM " +
                "Registro " +
                "WHERE " +
                "siglaCidade = ? " +
                "AND data >= ? " +
                "AND data <= ? " +
                "GROUP BY " +
                "data, hora, estacao, siglaCidade;";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, siglaCidade);
                stmt.setDate(2, new java.sql.Date(dataInicial.getTime()));
                stmt.setDate(3, new java.sql.Date(dataFinal.getTime()));
                ResultSet rs = stmt.executeQuery();

                

                while (rs.next()) {
                    String suspeitosConcatenados = rs.getString("suspeitos");
                    if (suspeitosConcatenados.contains("true")) {
                        // Se há algum tipo suspeito, não adicionamos este registro ao relatório
                        continue;
                    }
                    RegistroValorMedio registroValorMedio = new RegistroValorMedio();
                    registroValorMedio.setId(rs.getInt("id")) ;
                    registroValorMedio.setData(rs.getDate("data").toLocalDate());
                    registroValorMedio.setHora(rs.getTime("hora").toLocalTime());
                    registroValorMedio.setEstacao(rs.getString("estacao"));
                    registroValorMedio.setSiglaCidade(rs.getString("siglaCidade"));
                    
                    String[] tipos = rs.getString("tipos").split(", ");
                    String[] valores = rs.getString("valores").split(", ");

                    for (int i = 0; i < tipos.length; i++) {
                        switch (tipos[i]) {
                            case "temperaturaMedia":
                            registroValorMedio.setTemperaturaMedia(Double.parseDouble(valores[i]));
                                break;
                            case "umidadeMedia":
                                registroValorMedio.setUmidadeMedia(Double.parseDouble(valores[i]));
                                break;
                            case "velVento":
                                registroValorMedio.setVelVento(Double.parseDouble(valores[i]));
                                break;
                            case "dirVento":
                                registroValorMedio.setDirVento(Double.parseDouble(valores[i]));
                                break;
                            case "chuva":
                                registroValorMedio.setChuva(Double.parseDouble(valores[i]));
                                break;
                        }
                    }
                    relatorioValorMedio.add(registroValorMedio);
                }
                System.out.println("Relatorio Valor Medio: " + relatorioValorMedio.size());
            }
        } catch (SQLException e) {
            System.err.format(" RELATORIO VALOR MEDIO SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        
        return relatorioValorMedio;
    }

    

}
