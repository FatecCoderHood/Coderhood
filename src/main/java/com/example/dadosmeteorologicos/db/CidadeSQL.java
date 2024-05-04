package com.example.dadosmeteorologicos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            String sql = "SELECT * FROM cidade";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

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

    public boolean siglaValida(String siglaCidade) {
        try {
            String sql = "SELECT * FROM cidade WHERE sigla = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, siglaCidade);
            ResultSet rs = stmt.executeQuery();            
            
            if (rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();    
        }  
        return true;
    } 
}
