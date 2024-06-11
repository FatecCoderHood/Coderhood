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

    public CidadeSQL(Connection conn){
        this.conn = conn;
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

    public Boolean deletarCidadeBanco(int id, String siglaCidade) {
        try {
            if (conn != null) {
                String sqlCidade = "DELETE FROM cidade WHERE id = ?";
                PreparedStatement stmtCidade = conn.prepareStatement(sqlCidade);
                stmtCidade.setInt(1, id);
                stmtCidade.executeUpdate();
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    
        try {
            if (conn != null) {
                String sqlEstacao = "DELETE FROM estacao WHERE siglacidade = ?";
                PreparedStatement stmtEstacao = conn.prepareStatement(sqlEstacao);
                stmtEstacao.setString(1, siglaCidade);
                stmtEstacao.executeUpdate();
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        try {
            if (conn != null) {
                siglaCidade = siglaCidade.trim();
                String sqlRegistro = "DELETE FROM registro WHERE siglacidade = ?";
                PreparedStatement stmtRegistro = conn.prepareStatement(sqlRegistro);
                stmtRegistro.setString(1, siglaCidade);
                stmtRegistro.executeUpdate();
            }
        }catch (Exception e) {
                e.printStackTrace();
                return false;
            }
                return true;
            }


    public void criarCidade(String nomeCidade, String siglaCidade) {
        try {
            String sql = "INSERT INTO cidade (nome, sigla) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomeCidade);
            stmt.setString(2, siglaCidade);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarNomeCidadeBanco(int id, String nome) {
        try {
            String sql = "UPDATE cidade SET nome = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
