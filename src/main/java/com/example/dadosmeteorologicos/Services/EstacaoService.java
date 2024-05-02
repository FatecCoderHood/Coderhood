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

   public void adicionarNovaEstacao(String numeroNovaEstacao, String siglaCidadeNovaEstacao){
      siglaCidadeNovaEstacao = siglaCidadeNovaEstacao.toUpperCase();
      EstacaoSQL estacaoSQL = new EstacaoSQL();
      estacaoSQL.adicionarEstacaoBanco(numeroNovaEstacao, siglaCidadeNovaEstacao);
      estacaoSQL.fecharConexao();
   }

   public Boolean numeroEstacaoValido(String numero){
      System.out.println("numero: " + numero);
      EstacaoSQL estacaoSQL = new EstacaoSQL();
      Boolean siglaValida = estacaoSQL.numeroEstacaoValido(numero);
      estacaoSQL.fecharConexao();
      return siglaValida;
   }

}



