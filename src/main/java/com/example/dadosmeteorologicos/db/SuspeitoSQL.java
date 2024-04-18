package com.example.dadosmeteorologicos.db;

import java.sql.Connection;

public class SuspeitoSQL extends IniciaBanco {
    private Connection conn;

    public SuspeitoSQL() {
        conn = super.conectarBanco();
    }
}
