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

   public Boolean deletarEstacao(int id, String numero){
      EstacaoSQL estacaoSQL = new EstacaoSQL();
      estacaoSQL.deletarEstacaoBanco(id, numero);
      estacaoSQL.fecharConexao();
      return true;
   }

   public String adicionarEstacao(int id, String nome, String sigla){
      EstacaoSQL estacaoSQL = new EstacaoSQL();
      estacaoSQL.adicionarEstacaoBanco(id, nome, sigla);
      estacaoSQL.fecharConexao();
      return nome;
   }

}
