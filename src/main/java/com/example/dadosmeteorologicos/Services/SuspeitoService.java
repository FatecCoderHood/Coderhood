package com.example.dadosmeteorologicos.Services;

import com.example.dadosmeteorologicos.db.SuspeitoSQL;
import com.example.dadosmeteorologicos.model.Registro;

import java.sql.Connection;
import java.util.List;

// Classe de serviço para buscar registros suspeitos
public class SuspeitoService {
    public SuspeitoSQL banco;

    public SuspeitoService() {
        this.banco = new SuspeitoSQL();
    }

    public SuspeitoService(Connection conn) {
        this.banco = new SuspeitoSQL(conn);
    }

    // Método para buscar registros suspeitos
    public List<Registro> buscaRegistrosSuspeitos() {
        banco.conectarBanco();
        List<Registro> registros = banco.buscaRegistrosSuspeitosBanco();
        banco.fecharConexao();
        return registros;     
    }

    // Método para deletar um registro suspeito
    public void deletarRegistroSuspeito(int id) {
        banco.conectarBanco();
        banco.deletarRegistroSuspeito(id);
        banco.fecharConexao();    
    }

    // Método para editar um registro suspeito
    public void editarRegistroSuspeito(int id, double valor) {
        banco.conectarBanco();
        banco.editarRegistroSuspeito(id, valor);
        banco.fecharConexao();
    }
    
}
