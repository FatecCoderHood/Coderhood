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
            System.err.format("inicia banco SQL Stateee: %s\n%s", e.getSQLState(), e.getMessage());
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
                System.err.format("fecha conexao SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            }
        }
    }

    public void iniciarBanco(){
        criarTabelaVariavelClimatica();
        criarTabelaRegistro();
        criarTabelaCidade();
        criarTabelaEstacao();
       
    }

    private void criarTabelaRegistro(){
        try {
            if (conn != null) {
                System.out.println("Tabela registro");
    
                String sql = "CREATE TABLE IF NOT EXISTS registro (" +
                    "id SERIAL PRIMARY KEY," +
                    "data DATE," +
                    "hora TIME," +
                    "estacao VARCHAR(255)," +
                    "siglaCidade VARCHAR(05)," +
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
            System.err.format("Criar Tabela registro SQL Stateee: %s\n%s", e.getSQLState(), e.getMessage());
        } 
    }

    private void criarTabelaCidade() {
        try {
            if (conn != null) {
                System.out.println("Tabela cidade");
                String sql = "CREATE TABLE IF NOT EXISTS cidade (" +
                    "id SERIAL PRIMARY KEY," +
                    "nome VARCHAR(255)," +
                    "sigla VARCHAR(05)" +
                    ")";
    
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.format("Criar Tabela cidade SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    private void criarTabelaEstacao() {
        try {
            if (conn != null) {
                System.out.println("Tabela estacao");
                String sql = "CREATE TABLE IF NOT EXISTS estacao (" +
                    "id SERIAL PRIMARY KEY," +
                    "nome VARCHAR(255)," +
                    "siglaCidade VARCHAR(05)" +
                    ")";
    
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.format("Criar tabela estacao SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    private void criarTabelaVariavelClimatica(){
        try {
            if (conn != null) {
                System.out.println("Tabela variavel climatica");
                String sql = "CREATE TABLE IF NOT EXISTS variavel_climatica (" +
                    "id SERIAL PRIMARY KEY," +
                    "tipo VARCHAR(255)," +
                    "ValorMinimo DECIMAL(5,2)," +
                    "valorMaximo DECIMAL(5,2))";
    
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.format("Criar tabela variavel climatica SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}