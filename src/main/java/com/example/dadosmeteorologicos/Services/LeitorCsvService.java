package com.example.dadosmeteorologicos.Services;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.text.WordUtils;

import com.example.dadosmeteorologicos.db.LeitorCsvSQL;
import com.example.dadosmeteorologicos.model.Registro;


public class LeitorCsvService {
    private LeitorCsvSQL banco;
    
    public LeitorCsvService() {
        this.banco = new LeitorCsvSQL();
    }

    public LeitorCsvService(Connection conn) {
        this.banco = new LeitorCsvSQL(conn);
    }
    
    public int[] salvarRegistro(List<Registro> listaRegistroDto){
        banco.conectarBanco();
        int[] salvoDuplicado = new int[2];
        salvoDuplicado = banco.salvarRegistro(listaRegistroDto);
        banco.fecharConexao();
        return salvoDuplicado;
    }

    public String validarNomeCidadePelaSigla(String siglaCidade){
        banco.conectarBanco();
        String nomeCidadebanco = banco.validarNomeCidadePelaSigla(siglaCidade);
        banco.fecharConexao();
        return nomeCidadebanco;
    }

    public void criarCidade(String nomeCidade, String siglaCidade){
        banco.conectarBanco();
        banco.criarCidade(WordUtils.capitalizeFully(nomeCidade), siglaCidade.toUpperCase());
        banco.fecharConexao();
    }

    public void criarEstacao(String numeroEstacao, String siglaCidade){
        banco.conectarBanco();
        banco.criarEstacao(numeroEstacao, siglaCidade);
        banco.fecharConexao();
    }

    public boolean validarCidadeEstacao(String siglaCidade, String numeroEstacao){
        banco.conectarBanco();
        boolean cidadeEstacaoValido = banco.validarCidadeEstacao(siglaCidade, numeroEstacao);
        System.out.println("cidadeEstacaoValido " + cidadeEstacaoValido);
        banco.fecharConexao();
        return cidadeEstacaoValido;
    }

    public int registrosSuspeitos(List<Registro> listaRegistroDto){
        int registrosSuspeitos = 0;
        for (Registro registro : listaRegistroDto) {
            if (registro.isSuspeito()) registrosSuspeitos++;
                
        }
        return registrosSuspeitos;
    }
}

