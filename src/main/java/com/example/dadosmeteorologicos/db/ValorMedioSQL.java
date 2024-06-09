package com.example.dadosmeteorologicos.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.CidadeDetalhes;
import com.example.dadosmeteorologicos.model.RegistroValorMedio;

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
                String sql = "SELECT siglacidade, data, hora, " +
                "AVG(CASE WHEN tipo = 'dirVento' THEN valor ELSE NULL END) AS dirVento, " +
                "AVG(CASE WHEN tipo = 'temperaturaMedia' THEN valor ELSE NULL END) AS temperaturaMedia, " +
                "AVG(CASE WHEN tipo = 'umidadeMedia' THEN valor ELSE NULL END) AS umidadeMedia, " +
                "AVG(CASE WHEN tipo = 'velVento' THEN valor ELSE NULL END) AS velVento, " +
                "AVG(CASE WHEN tipo = 'chuva' THEN valor ELSE NULL END) AS chuva " +
                "FROM registro " +
                " WHERE valor IS NOT NULL and siglaCidade = ? and data >= ? and data <= ? " +
                "GROUP BY siglacidade, data, hora " +
                "ORDER BY data, hora";
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
                    registro.carregarInfoValores(rs);
                    ListaRegistroBD.add(registro);
                }
            }
        } catch (SQLException e) {
            System.err.format(" RELATORIO VALOR MEDIO SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return ListaRegistroBD;
    }
}
