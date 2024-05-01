package com.example.dadosmeteorologicos.db;

import com.example.dadosmeteorologicos.model.Registro;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SuspeitoSQL extends IniciaBanco {
    protected Connection conn;

    public SuspeitoSQL() {
        conn = super.conectarBanco();
    }

    public List<Registro> buscaRegistrosSuspeitosBanco() {
        List<Registro> listaRegistros = new ArrayList<>();
        try {
            if(conn != null){
                String sql = "SELECT * FROM registro WHERE suspeito = true";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
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
        return listaRegistros;
    
}
}