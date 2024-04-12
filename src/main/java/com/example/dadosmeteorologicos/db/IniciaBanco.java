package com.example.dadosmeteorologicos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;




public class IniciaBanco {

    private String url = "jdbc:postgresql://localhost/ApiFatec";
    private String user = "postgres";
    private String password = "root";
    private Connection conn;

    public IniciaBanco() {
        this.conn = conectarBanco();
    }

    public Connection conectarBanco() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL Stateee: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void fecharConexao() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
        }
    }

    public void iniciarBanco(){
       criarTabelaRegistro();
       criarTabelaCidade();
       criarTabelaEstacao();
    }

    private void criarTabelaRegistro(){
        try {
            if (conn != null) {
                System.out.println("Banco Iniciado");
    
                String sql = "CREATE TABLE IF NOT EXISTS Registro (" +
                    "id SERIAL PRIMARY KEY," +
                    "data DATE," +
                    "hora TIME," +
                    "estacao VARCHAR(255)," +
                    "siglaCidade VARCHAR(10)," +
                    "tipo VARCHAR(255)," +
                    "valor DECIMAL(5,2)," +
                    "suspeito BOOLEAN," +
                    //garante que não possam existir duas linhas na tabela 
                    //Registro com a mesma combinação de estacao, data e hora.
                    "UNIQUE (data, hora, estacao, siglaCidade, tipo)"+
                    ")";
    
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
    
            } else {
                System.out.println("Falha ao conectar no banco!");
            }
        } catch (SQLException e) {
            System.err.format("SQL Stateee: %s\n%s", e.getSQLState(), e.getMessage());
        } 
    }

    private void criarTabelaCidade() {
        try {
            if (conn != null) {
                String sql = "CREATE TABLE IF NOT EXISTS Cidade (" +
                    "id SERIAL PRIMARY KEY," +
                    "nome VARCHAR(255)," +
                    "sigla CHARACTER(6)" +
                    ")";
    
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    private void criarTabelaEstacao() {
        try {
            if (conn != null) {
                String sql = "CREATE TABLE IF NOT EXISTS Estacao (" +
                    "id SERIAL PRIMARY KEY," +
                    "nome VARCHAR(255)," +
                    "siglaCidade CHARACTER(6)" +
                    ")";
    
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}