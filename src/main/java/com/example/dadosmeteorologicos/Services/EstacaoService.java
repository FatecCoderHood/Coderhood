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
      List<Estacao> listaEstacao = banco.buscaEstacaoBanco();
      banco.fecharConexao();
      return listaEstacao;
   }

   public Boolean deletarEstacao(int id, String numero){
      banco.conectarBanco();
      banco.deletarEstacaoBanco(id, numero);
      banco.fecharConexao();
      return true;
   }

   public void adicionarNovaEstacao(String numeroNovaEstacao, String siglaCidadeNovaEstacao){
      banco.conectarBanco();
      siglaCidadeNovaEstacao = siglaCidadeNovaEstacao.toUpperCase();
      banco.adicionarEstacaoBanco(numeroNovaEstacao, siglaCidadeNovaEstacao);
      banco.fecharConexao();
   }

   public Boolean numeroEstacaoValido(String numero){
      banco.conectarBanco();
      Boolean siglaValida = banco.numeroEstacaoValido(numero);
      banco.fecharConexao();
      return siglaValida;
   }

   public Boolean siglaCidadeExiste(String sigla){
      banco.conectarBanco();
      sigla = sigla.toUpperCase();
      Boolean siglaValida = banco.siglaCidadeExiste(sigla);
      banco.fecharConexao();
      return siglaValida;
   }

   public void atualizarEstacao(int id, Estacao estacao){
      banco.conectarBanco();
      banco.atualizarEstacaoBanco(id, estacao);
      banco.fecharConexao();
   }
}



