package com.example.dadosmeteorologicos.Services;

import java.util.List;

import org.apache.commons.text.WordUtils;

import com.example.dadosmeteorologicos.db.LeitorCsvSQL;
import com.example.dadosmeteorologicos.model.Registro;

public class LeitorCsvService {    

    public int[] salvarRegistro(List<Registro> listaRegistroDto){
        int[] salvoDuplicado = new int[2];
        LeitorCsvSQL banco = new LeitorCsvSQL();
        salvoDuplicado = banco.salvarRegistro(listaRegistroDto);
        banco.fecharConexao();
        return salvoDuplicado;
    }
    public String validarNomeCidadePelaSigla(String siglaCidade){
        LeitorCsvSQL banco = new LeitorCsvSQL();
        String nomeCidadebanco = banco.validarNomeCidadePelaSigla(siglaCidade);
        banco.fecharConexao();
        return nomeCidadebanco;
    }

    public void criarCidade(String nomeCidade, String siglaCidade){
        LeitorCsvSQL banco = new LeitorCsvSQL();
        banco.criarCidade(WordUtils.capitalizeFully(nomeCidade), siglaCidade);
        banco.fecharConexao();
    }

    public void criarEstacao(String numeroEstacao, String siglaCidade){
        LeitorCsvSQL banco = new LeitorCsvSQL();
        banco.criarEstacao(numeroEstacao, siglaCidade);
        banco.fecharConexao();
    }

    public boolean validarCidadeEstacao(String siglaCidade, String numeroEstacao){
        LeitorCsvSQL banco = new LeitorCsvSQL();
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
