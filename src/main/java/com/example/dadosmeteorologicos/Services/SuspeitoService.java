package com.example.dadosmeteorologicos.Services;

import com.example.dadosmeteorologicos.db.SuspeitoSQL;
import com.example.dadosmeteorologicos.model.Registro; 
import java.util.List;

public class SuspeitoService {

    public List<Registro> buscaRegistrosSuspeitos() {
        SuspeitoSQL suspeitoSQL = new SuspeitoSQL();
        List<Registro> registros = suspeitoSQL.buscaRegistrosSuspeitosBanco();
        suspeitoSQL.fecharConexao();
        return registros;     
    }
    
}
