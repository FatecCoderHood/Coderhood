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
    
}
