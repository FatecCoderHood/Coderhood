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


}
