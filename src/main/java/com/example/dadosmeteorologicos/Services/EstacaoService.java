package com.example.dadosmeteorologicos.Services;

import java.util.List;

import com.example.dadosmeteorologicos.db.EstacaoSQL;
import com.example.dadosmeteorologicos.model.Estacao;

public class EstacaoService {

   public List<Estacao> buscaEstacao(){
        EstacaoSQL estacaoSQL = new EstacaoSQL();
        List<Estacao> listaEstacao = estacaoSQL.buscaEstacaoBanco();
        estacaoSQL.fecharConexao();
        return listaEstacao;

   }

}