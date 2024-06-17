package com.example.dadosmeteorologicos.Services;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.dadosmeteorologicos.db.SituacaoSQL;
import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.RegistroSituacao;

public class SituacaoService {

    private SituacaoSQL banco;

   public SituacaoService(){
      this.banco = new SituacaoSQL();
   }

   public SituacaoService(Connection conn){
      this.banco = new SituacaoSQL(conn);
   }

   public Map<Cidade, RegistroSituacao> getRegistroSituacao(List<Cidade> cidades){
        banco.conectarBanco();
        Map<Cidade, RegistroSituacao> registroSituacaoPorCidade = new HashMap<>();
        for (Cidade cidade : cidades) {
            RegistroSituacao registro = banco.getRegistroSituacao(cidade);
            registro.filtraRegistro();
            registro.setCidadeESigla(cidade.getNome() + " - " + cidade.getSigla());
            registroSituacaoPorCidade.put(cidade, registro);
        }
    
        banco.fecharConexao();

        

        return registroSituacaoPorCidade;
    }
}
