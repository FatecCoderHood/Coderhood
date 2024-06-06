package com.example.dadosmeteorologicos.Services;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.text.WordUtils;

import com.example.dadosmeteorologicos.db.CidadeSQL;
import com.example.dadosmeteorologicos.model.Cidade;

public class CidadeService {
    private CidadeSQL banco;

    public CidadeService(){
        this.banco = new CidadeSQL();
    }

    public CidadeService(Connection conn){
        this.banco = new CidadeSQL(conn);
    }

    public List<Cidade> getCidades() {
        banco.conectarBanco();
        List<Cidade> listaCidades = banco.buscaCidadesBanco();
        banco.fecharConexao();
        return listaCidades;
    }

    public boolean siglaValida(String siglaCidade) {
        siglaCidade = siglaCidade.toUpperCase();
        banco.conectarBanco();
        boolean siglaValida = banco.siglaValida(siglaCidade);
        banco.fecharConexao();
        return siglaValida;
    }

    public Boolean deletarCidade (int id, String sigla) {
        banco.conectarBanco();
        banco.deletarCidadeBanco(id, sigla);
        banco.fecharConexao();
        return true;
    }
    
    public void criarCidade(String nomeCidade, String siglaCidade) {
        banco.conectarBanco();
        banco.criarCidade(WordUtils.capitalizeFully(nomeCidade), siglaCidade.toUpperCase());
        banco.fecharConexao();
    }

    public void atualizarCidade(int id, String nome) {
        banco.conectarBanco();
        banco.atualizarCidadeBanco(id, WordUtils.capitalizeFully(nome));
        banco.fecharConexao();
    }

}
