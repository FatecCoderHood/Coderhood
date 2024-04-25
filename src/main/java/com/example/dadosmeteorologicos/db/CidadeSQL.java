package com.example.dadosmeteorologicos.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.dadosmeteorologicos.model.Cidade;

public class CidadeSQL extends IniciaBanco{
    private Connection conn;

    public CidadeSQL() {
        conn = super.conectarBanco();
    }

    public List<Cidade> buscaCidadesBanco() {
        List<Cidade> listaCidades = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cidade");
            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(rs.getInt("id"));
                cidade.setNome(rs.getString("nome"));
                cidade.setSigla(rs.getString("sigla"));
                listaCidades.add(cidade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCidades;
    }

    
}
