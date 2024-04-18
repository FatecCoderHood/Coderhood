package com.example.dadosmeteorologicos.db;

import java.sql.Connection;

public class CidadeSQL extends IniciaBanco{
    private Connection conn;

    public CidadeSQL() {
        conn = super.conectarBanco();
    }

    
}
