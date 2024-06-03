package com.example.dadosmeteorologicos.Services;

import java.sql.Connection;
import java.util.List;

import com.example.dadosmeteorologicos.db.VariavelClimaticaSQL;
import com.example.dadosmeteorologicos.model.VariavelClimatica;

public class VariavelClimaticaService {
    private VariavelClimaticaSQL banco;

    public VariavelClimaticaService() {
        this.banco = new VariavelClimaticaSQL();
    }

    public VariavelClimaticaService(Connection conn) {
        this.banco = new VariavelClimaticaSQL();
        banco.setConn(conn);
    }
 
    public List<VariavelClimatica> getVariaveisClimaticas(){
        // Chama o método getVariaveisClimaticas para obter a lista de variaveis climaticas
        List<VariavelClimatica> variaveisClimaticas = banco.getVariaveisClimaticasBanco();
        // Fecha a conexão com o banco de dados
        banco.fecharConexao();
        // Retorna a lista de variaveis climaticas
        return variaveisClimaticas;
    }

    public void setVariaveisClimaticas(List<VariavelClimatica> variaveis){
        // Chama o método setVariaveisClimaticas para setar as variaveis climaticas
        banco.setVariaveisClimaticasBanco(variaveis);
        // Fecha a conexão com o banco de dados
        banco.fecharConexao();
    }

    public boolean celulasDaTabelaEstaoNulas(){
        // Chama o método celulasDaTabelaEstaoNulas para verificar se as celulas da tabela estão nulas
        boolean celulasNulas = banco.celulasDaTabelaEstaoNulas();
        // Fecha a conexão com o banco de dados
        banco.fecharConexao();
        // Retorna se as celulas da tabela estão nulas
        return celulasNulas;
    }
}
