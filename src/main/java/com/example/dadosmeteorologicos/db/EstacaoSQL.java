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

    public EstacaoSQL(Connection conn) {
        this.conn = conn;
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
                    estacao.setNumero(rs.getString("numero"));
                    estacao.setSiglaCidade(rs.getString("siglaCidade"));
                    estacao.setNome(rs.getString("nome"));
                    estacao.setDescricao(rs.getString("descricao"));
                    estacao.setLatitude(rs.getDouble("latitude"));
                    estacao.setLongitude(rs.getDouble("longitude"));
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

    public Boolean numeroEstacaoValido(String numeroEstacao) {
        try {
            if(conn != null){
                String sql = "SELECT * FROM estacao WHERE numero = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, numeroEstacao);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    


    public void adicionarEstacaoBanco(String numeroNovaEstacao, String siglaCidadeNovaEstacao) {
        try {
            if(conn != null){
                String sql = "INSERT INTO estacao (numero, siglacidade) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, numeroNovaEstacao);
                stmt.setString(2, siglaCidadeNovaEstacao);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean siglaCidadeExiste(String sigla) {
        try {
            if(conn != null){
                String sql = "SELECT * FROM cidade WHERE sigla = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, sigla);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void atualizarEstacaoBanco(int id, Estacao estacao){
        try {
            if(conn != null){
                String sql = "UPDATE estacao SET nome = ?, descricao = ?, latitude = ?, longitude = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, estacao.getNome());
                stmt.setString(2, estacao.getDescricao());
                stmt.setDouble(3, estacao.getLatitude());
                stmt.setDouble(4, estacao.getLongitude());
                stmt.setInt(5, id);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

