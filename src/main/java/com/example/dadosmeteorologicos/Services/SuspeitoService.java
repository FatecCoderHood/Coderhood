package com.example.dadosmeteorologicos.Services;

import com.example.dadosmeteorologicos.db.SuspeitoSQL;
import com.example.dadosmeteorologicos.model.Registro;

import java.util.List;

// Classe de serviço para buscar registros suspeitos
public class SuspeitoService {

    // Método para buscar registros suspeitos
    public List<Registro> buscaRegistrosSuspeitos() {
        SuspeitoSQL suspeitoSQL = new SuspeitoSQL();
        List<Registro> registros = suspeitoSQL.buscaRegistrosSuspeitosBanco();
        suspeitoSQL.fecharConexao();
        return registros;     
    }

    // Método para deletar um registro suspeito
    public void deletarRegistroSuspeito(int id) {
        SuspeitoSQL suspeitoSQL = new SuspeitoSQL();
        suspeitoSQL.deletarRegistroSuspeito(id);
        suspeitoSQL.fecharConexao();    
    }

    // Método para editar um registro suspeito
    public void editarRegistroSuspeito(int id, double valor) {
        SuspeitoSQL suspeitoSQL = new SuspeitoSQL();
        suspeitoSQL.editarRegistroSuspeito(id, valor);
        suspeitoSQL.fecharConexao();
    }
    
}
