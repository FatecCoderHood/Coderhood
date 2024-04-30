package com.example.dadosmeteorologicos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.dadosmeteorologicos.model.Estacao;


public class EstacaoSQL extends IniciaBanco{

    private Connection conn;

    public EstacaoSQL() {
        conn = super.conectarBanco();
    }


    public List<Estacao> buscaEstacaoBanco(){
        List<Estacao> listaEstacao = new ArrayList<>();
        try {
            if(conn != null){
                String sql = "SELECT * FROM Estacao"; 
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    Estacao estacao = new Estacao();
                    estacao.setId(rs.getInt("id"));
                    estacao.setNumero(rs.getString("nome"));
                    estacao.setSiglaCidade(rs.getString("siglaCidade"));
                    listaEstacao.add(estacao);
                }
            }
        } catch (Exception e) {
        e.printStackTrace();
        }
        return listaEstacao;
    }

    public Boolean deletarEstacaoBanco(int id, String numero) {
        try {
            if(conn != null){
                String sqlEstacao = "DELETE FROM Estacao WHERE id = ?";
                PreparedStatement stmtEstacao = conn.prepareStatement(sqlEstacao);
                stmtEstacao.setInt(1, id);
                stmtEstacao.executeUpdate();

                String sqlRegistro =  "DELETE FROM Registro WHERE estacao = ?";
                PreparedStatement stmtRegistro = conn.prepareStatement(sqlRegistro);
                stmtRegistro.setString(1, numero);
                stmtRegistro.executeUpdate();
                
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public String adicionarEstacaoBanco(int id, String cidade, String numero){
        String result = "";
        try {
            if(conn != null){
                String sqlEstacao = "INSERT INTO Estacao (id, cidade, numero) VALUES (?, ?, ?)";
                PreparedStatement stmtEstacao = conn.prepareStatement(sqlEstacao);
                stmtEstacao.setInt(1, id);
                stmtEstacao.setString(2, cidade);
                stmtEstacao.setString(3, numero);
                int rowsAffected = stmtEstacao.executeUpdate();
                if(rowsAffected > 0){
                    result = "Estação adicionada com sucesso!";
                } else {
                    result = "Falha ao adicionar estação.";
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            result = "Erro ao adicionar estação: " + e.getMessage();
        }
        return result;
    }
}
    

    
