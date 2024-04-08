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

import com.example.dadosmeteorologicos.model.RegistroDto;

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
                                "Registro ON Cidade.sigla = Registro.cidade " +
                            "WHERE " +
                                "Registro.cidade = Cidade.sigla " +
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
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return registros;
    }

    public List<RegistroDto> getRelatorioValorMedio(String id, Date dataInicial, Date dataFinal){
        List<RegistroDto> relatorioValorMedio = new ArrayList<>();
    
        try {
            if (conn != null) {
                String sql = "SELECT * FROM registro WHERE cidade = ? AND data >= ? AND data <= ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                stmt.setDate(2, dataInicial);
                stmt.setDate(3, dataFinal);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int ide = rs.getInt("id");
                    String cidade = rs.getString("cidade");
                    String estacao = rs.getString("estacao");
                    LocalDate data = rs.getDate("data").toLocalDate();
                    LocalTime hora = rs.getTime("hora").toLocalTime();
                    Double temperaturaMedia = rs.getDouble("temperaturaMedia");
                    Double umidadeMedia = rs.getDouble("umidadeMedia");
                    Double velVento = rs.getDouble("velVento");
                    Double dirVento = rs.getDouble("dirVento");
                    Double chuva = rs.getDouble("chuva");
                    Boolean temperaturaSuspeita = rs.getBoolean("temperaturaSuspeita");
                    Boolean umidadeSuspeita = rs.getBoolean("umidadeSuspeita");
                    Boolean velocidadeVentoSuspeita = rs.getBoolean("velocidadeVentoSuspeita");
                    Boolean direcaoVentoSuspeita = rs.getBoolean("direcaoVentoSuspeita");
                    Boolean chuvaSuspeita = rs.getBoolean("chuvaSuspeita");

                    RegistroDto registro = new RegistroDto(ide, cidade, estacao, data, hora, temperaturaMedia, umidadeMedia, velVento, dirVento, chuva, temperaturaSuspeita, umidadeSuspeita, velocidadeVentoSuspeita, direcaoVentoSuspeita, chuvaSuspeita);
                    relatorioValorMedio.add(registro);
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    
        return relatorioValorMedio;
    }

    

}
