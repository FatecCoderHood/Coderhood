package com.example.dadosmeteorologicos.Services;

import java.sql.Connection;
import java.util.List;

import com.example.dadosmeteorologicos.db.EstacaoSQL;
import com.example.dadosmeteorologicos.model.Estacao;

public class EstacaoService {
   private EstacaoSQL banco;

   public EstacaoService(){
      this.banco = new EstacaoSQL();
  }

   public EstacaoService(Connection conn){
      this.banco = new EstacaoSQL(conn);
   }

   public List<Estacao> buscaEstacao(){
      banco.conectarBanco();
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
      banco.conectarBanco();
      EstacaoSQL estacaoSQL = new EstacaoSQL();
      Boolean siglaValida = estacaoSQL.numeroEstacaoValido(numero);
      estacaoSQL.fecharConexao();
      return siglaValida;
   }

   public Boolean siglaCidadeExiste(String sigla){
      banco.conectarBanco();
      sigla = sigla.toUpperCase();
      EstacaoSQL estacaoSQL = new EstacaoSQL();
      Boolean siglaValida = estacaoSQL.siglaCidadeExiste(sigla);
      estacaoSQL.fecharConexao();
      return siglaValida;
   }

   public void atualizarEstacao(int id, Estacao estacao){
      EstacaoSQL banco = new EstacaoSQL();
      banco.atualizarEstacaoBanco(id, estacao);
      banco.fecharConexao();
   }

}



