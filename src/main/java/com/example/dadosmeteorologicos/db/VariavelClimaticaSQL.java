package com.example.dadosmeteorologicos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.dadosmeteorologicos.model.VariavelClimatica;

public class VariavelClimaticaSQL extends IniciaBanco{

    private Connection conn;

    public VariavelClimaticaSQL() {
        this.conn = super.conectarBanco();
    }

    
    public void fecharConexao() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
        }
    }

    public boolean celulasDaTabelaEstaoNulas(){
        try {
            if (conn != null) {
                String sql = "SELECT * FROM variavel_climatica";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    if(rs.getString("tipo") == null || rs.getString("minima") == null || rs.getString("maxima") == null){
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return false;
    }

    public List<VariavelClimatica>  getVariaveisClimaticasBanco(){
        List<VariavelClimatica> variaveis = new ArrayList<>();
        try {
            if (conn != null) {
                String sql = "SELECT * FROM variavel_climatica";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    VariavelClimatica variavel = new VariavelClimatica();
                    variavel.setTipo(rs.getString("tipo"));
                    variavel.setValorMinimo(rs.getDouble("minima"));
                    variavel.setValorMaximo(rs.getDouble("maxima"));
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return variaveis;
    }
    

    public void setVariaveisClimaticasBanco(List<VariavelClimatica> variaveis){
        try {
            if (conn != null) {
                for (VariavelClimatica variavel : variaveis){
                    String sql = "UPDATE variavel_climatica SET " +
                                "tipo = ?," +
                                "minima = ?," +
                                "maxima = ?";

                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, variavel.getTipo());
                    stmt.setDouble(2, variavel.getValorMinimo());
                    stmt.setDouble(3, variavel.getValorMaximo());

                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    


}
