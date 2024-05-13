package com.example.dadosmeteorologicos.Services;

import java.util.List;

import com.example.dadosmeteorologicos.db.SituacaoSQL;
import com.example.dadosmeteorologicos.model.Registro;

public class SituacaoService {

    // Método para buscar registros de situação
    public List<Registro> buscaSituacaoService() {
        SituacaoSQL situacaoSQL = new SituacaoSQL();
        List<Registro> listaRegistros = situacaoSQL.buscaSituacaoBanco();
        situacaoSQL.fecharConexao();
        return listaRegistros;     
    }
}
