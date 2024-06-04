package com.example.dadosmeteorologicos.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.CidadeDetalhes;
import com.example.dadosmeteorologicos.model.RegistroValorMedio;
import com.example.dadosmeteorologicos.model.ValorMedioInfo;

public class ValorMedioSQL extends IniciaBanco {

    private Connection conn;

    public ValorMedioSQL() {
        this.conn = super.conectarBanco();
    }

    public ValorMedioSQL(Connection conn) {
        this.conn = conn;
    }

     public List<Cidade> getCidadesMenuItem() {
        List<Cidade> registros = new ArrayList<>();
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
                    Cidade cidade = new Cidade(nome, sigla, 
                        new CidadeDetalhes(dataPrimeiroRegistro, dataUltimoRegistro));
                    registros.add(cidade);
                    System.out.println("valor medioSQL");
                    System.out.println("URL do banco de dados: " + conn.getMetaData().getURL());
                    System.out.println("Usuário do banco de dados: " + conn.getMetaData().getUserName());

                }
            }
        } catch (SQLException e) {
            System.err.format(" CIDADES MENU SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return registros;
    }

    public List<RegistroValorMedio> getRelatorioValorMedio(String siglaCidade, Date dataInicial, Date dataFinal){
        List<RegistroValorMedio> ListaRegistroBD = new ArrayList<>();
    
        try {
            if (conn != null) {
                String sql = "SELECT " +
                                "data, " +
                                "hora, " +
                                "siglaCidade, " +
                                "STRING_AGG(tipo, ', ' ORDER BY tipo) AS tipos, " +
                                "STRING_AGG(COALESCE(valor::text, 'null'), ', ' ORDER BY tipo) AS valores " +
                            "FROM " +
                                "Registro " +
                            "WHERE " +
                                "siglaCidade = ? " +
                                "AND data >= ? " +
                                "AND data <= ? " +
                            "GROUP BY " +
                                "data, hora, estacao, siglaCidade " +
                            "HAVING BOOL_OR(suspeito) = false";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, siglaCidade);
                stmt.setDate(2, new java.sql.Date(dataInicial.getTime()));
                stmt.setDate(3, new java.sql.Date(dataFinal.getTime()));
                ResultSet rs = stmt.executeQuery();
    
                while (rs.next()) {
                    RegistroValorMedio registro = new RegistroValorMedio();
                    registro.setData(rs.getDate("data").toLocalDate());
                    registro.setHora(rs.getTime("hora").toLocalTime());
                    registro.setSiglaCidade(rs.getString("siglaCidade"));
                
                    String tiposConcatenados = rs.getString("tipos");
                    String valoresConcatenados = rs.getString("valores");
                
                    List<String> tipos = Arrays.asList(tiposConcatenados.split(", "));
                    List<String> valores = Arrays.asList(valoresConcatenados.split(", "));
                
                    List<ValorMedioInfo> valorMedioInfos = new ArrayList<>();
                    for (int i = 0; i < tipos.size(); i++) {
                        ValorMedioInfo valorMedioInfo = new ValorMedioInfo();
                        valorMedioInfo.setTipo(tipos.get(i));
                        if (!valores.get(i).equals("null")) {
                            valorMedioInfo.setValor(Double.parseDouble(valores.get(i)));
                        } else {
                            valorMedioInfo.setValor(null);
                        }
    
                        valorMedioInfos.add(valorMedioInfo);
                    }
                    registro.setValorMedioInfos(valorMedioInfos);
                    ListaRegistroBD.add(registro);
                }
            }
        } catch (SQLException e) {
            System.err.format(" RELATORIO VALOR MEDIO SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return ListaRegistroBD;
    }
}
