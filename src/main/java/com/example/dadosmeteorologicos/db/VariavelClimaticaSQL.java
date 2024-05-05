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

    public boolean celulasDaTabelaEstaoNulas(){
        try {
            if (conn != null) {
                String sql = "SELECT * FROM variavel_climatica";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                if (!rs.next()) {
                    return true;
                } else {
                    while (rs.next()) {
                        if(rs.getString("tipo") == null || rs.getString("ValorMinimo") == null 
                            || rs.getString("ValorMaximo") == null){
                            return true;
                        }
                    } 
                }
            }
        } catch (SQLException e) {
            System.err.format(" verifica celulas tabela SQL State: %s\n%s", e.getSQLState(), e.getMessage());
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
                    VariavelClimatica variavel = new VariavelClimatica(rs.getString("tipo"), rs.getDouble("ValorMinimo"), rs.getDouble("ValorMaximo"));
                    variaveis.add(variavel);
                }
            }
        } catch (SQLException e) {
            System.err.format(" get variaveis climaticas SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return variaveis;
    }
    

    public void setVariaveisClimaticasBanco(List<VariavelClimatica> variaveis){
        try {
            if (conn != null) {
                String checkSql = "SELECT count(*) FROM variavel_climatica";
                Statement checkStmt = conn.createStatement();
                ResultSet checkRs = checkStmt.executeQuery(checkSql);
                checkRs.next();
                int count = checkRs.getInt(1);
    
                if (count == 0) {
                    // A tabela está vazia
                    for (VariavelClimatica variavel : variaveis){
                        String insertSql = "INSERT INTO variavel_climatica (tipo, ValorMinimo, ValorMaximo) VALUES (?, ?, ?)";
                        PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                        insertStmt.setString(1, variavel.getTipo());
                        insertStmt.setDouble(2, variavel.getValorMinimo());
                        insertStmt.setDouble(3, variavel.getValorMaximo());
                        insertStmt.executeUpdate();
                    }
                } else {
                    // A tabela não está vazia, então atualize os registros
                    for (VariavelClimatica variavel : variaveis){
                        String updateSql = "UPDATE variavel_climatica SET ValorMinimo = ?, ValorMaximo = ? WHERE tipo = ?";
                        PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                        updateStmt.setDouble(1, variavel.getValorMinimo());
                        updateStmt.setDouble(2, variavel.getValorMaximo());
                        updateStmt.setString(3, variavel.getTipo());
                        updateStmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.err.format("setVariaveisClimaticas SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

}
