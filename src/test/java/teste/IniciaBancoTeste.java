package teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.Data;

@Data
public class IniciaBancoTeste {
    private String nomeDB = "apifatecteste";
    private String url = "jdbc:postgresql://localhost/" + nomeDB;
    private String user = "postgres";
    private String password = "root";
    private Connection conn;

    public IniciaBancoTeste() {
        this.conn = conectarBanco();
        criarDataBase();
    }

    public Connection conectarBanco() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
            } else {
                System.out.println("falha ao conectar no PostgreSQL");
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
        try{
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                criarTabelaVariavelClimatica();
                criarTabelaRegistro();
                criarTabelaCidade();
                criarTabelaEstacao();
                conn.close();
            }
        }catch(SQLException e){
            System.err.format("iniciarBanco SQL Stateee: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void limparBanco(){
        try {
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                String sql = "DELETE FROM registro; DELETE FROM cidade; DELETE FROM estacao; DELETE FROM variavel_climatica";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            } 
        } catch (SQLException e) {
            System.err.format("limparBanco SQL Stateee: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
    
    public void criarDataBase(){
        System.out.println("---------");
        System.out.println("Criando banco de dados");
        System.out.println("---------");
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost/", user, password);
            if (conn != null) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT 1 FROM pg_database WHERE datname='" + nomeDB + "'");
            if (!rs.next()) {
                stmt.execute("CREATE DATABASE " + nomeDB);
            }
            stmt.close();
        } else {
            System.out.println("Banco ja existe!");
        }
        } catch (SQLException e) {
            System.err.format("criarDataBase SQL Stateee: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void reiniciarBanco(){
        try {
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                String sql = "DROP TABLE IF EXISTS registro, cidade, estacao, variavel_climatica";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
                conn.close();
            } 
        } catch (SQLException e) {
            System.err.format("reiniciarBanco SQL Stateee: %s\n%s", e.getSQLState(), e.getMessage());
        }
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
                    "numero VARCHAR(255) UNIQUE," +
                    "siglaCidade VARCHAR(05)," +
                    "nome VARCHAR(255)," +
                    "descricao VARCHAR(255)," +
                    "latitude DOUBLE PRECISION," +
                    "longitude DOUBLE PRECISION" +
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
                    "valorMinimo DECIMAL(5,2)," +
                    "valorMaximo DECIMAL(5,2)," +
                    "unidadeMedida VARCHAR(20)," +
                    "descricaoConversao VARCHAR(255)," +
                    "formulaConversao VARCHAR(255))" ;
    
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.format("Criar tabela variavel climatica SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void popularBancoTeste(){
        try {
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                popularTabelaCidade();
                popularTabelaEstacao();
                popularTabelaVariavelClimatica();
                popularTabelaRegistro();
                conn.close();
            } 
        } catch (SQLException e) {
            System.err.format("popularBancoTeste SQL Stateee: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    private void popularTabelaCidade(){
        try {
            if (conn != null) {
                System.out.println("Popular tabela cidade");
                String sql = "INSERT INTO cidade (nome, sigla) VALUES " +
                    "('São José dos Campos', 'SJC')," +
                    "('São Paulo', 'SP')," +
                    "('Taubaté', 'TBT')," +
                    "('São Carlos', 'SC')";
    
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            System.err.format("Popular tabela cidade SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    private void popularTabelaEstacao(){
        try {
            if (conn != null) {
                System.out.println("Popular tabela estacao");
                String sql = "INSERT INTO estacao (numero, siglaCidade, nome, descricao, latitude, longitude) VALUES " +
                    "('83726', 'SC', 'TESTE', 'TESTE', -23.223701, -45.900907)," +
                    "('8888', 'SC', 'TESTE', 'TESTE', -23.223701, -45.900907)," +
                    "('7777', 'SC', 'TESTE', 'TESTE', -23.223701, -45.900907)," +
                    "('6666', 'SC', 'TESTE', 'TESTE', -23.223701, -45.900907)," +
                    "('777', 'SP', 'TESTE', 'TESTE', -23.223701, -45.900907)," +
                    "('420', 'SJC', 'TESTE', 'TESTE', -23.223701, -45.900907)," +
                    "('728', 'TBT', 'TESTE', 'TESTE', -23.223701, -45.900907)";
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.err.format("Popular tabela estacao SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    private void popularTabelaVariavelClimatica(){
        try {
            if (conn != null) {
                System.out.println("Popular tabela variavel climatica");
                String sql = "INSERT INTO variavel_climatica (tipo, valorMinimo, valorMaximo, unidadeMedida, descricaoConversao, formulaConversao) VALUES " +
                    "('temperaturaMedia', -20.0, 60.0, '°C', 'Celsius para Kelvin', 'c = kelvin - 273')," +
                    "('umidadeMedia', 0.0, 100.0, '%', 'Umidade relativa', 'umidade')," +
                    "('velVento', 0.0, 30.0, 'hPa', 'Pressão atmosférica', 'pressao')," +
                    "('dirVento', 0.0, 360.0, 'm/s', 'Direção do vento', 'direcao')," +
                    "('chuva', 0.0, 400.0, 'mm', 'Velocidade do vento', 'velocidade')";
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.err.format("Popular tabela variavel climatica SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    private void popularTabelaRegistro(){
        try {
            if (conn != null) {
                System.out.println("Popular tabela registro");
                String sql = "INSERT INTO registro (data, hora, estacao, siglaCidade, tipo, valor, suspeito) VALUES " +
                    //SC 3 registros diferente para cada tipo
                    "('2021-01-01', '00:00:00', '83726', 'SC', 'temperaturaMedia', 20.0, false)," +
                    "('2021-01-01', '00:00:00', '83726', 'SC', 'umidadeMedia', 50.0, false)," +
                    "('2021-01-01', '00:00:00', '83726', 'SC', 'velVento', 10.0, false)," +
                    "('2021-01-01', '00:00:00', '83726', 'SC', 'dirVento', 180.0, false)," +
                    "('2021-01-01', '00:00:00', '83726', 'SC', 'chuva', 0.0, false)," +
                    "('2021-01-01', '00:00:00', '8888', 'SC', 'temperaturaMedia', 55, false)," +
                    "('2021-01-01', '00:00:00', '8888', 'SC', 'umidadeMedia', 50.0, false)," +
                    "('2021-01-01', '00:00:00', '8888', 'SC', 'velVento', 10.0, false)," +
                    "('2021-01-01', '00:00:00', '8888', 'SC', 'dirVento', 190.0, false)," +
                    "('2021-01-01', '00:00:00', '8888', 'SC', 'chuva', 5.0, false)," +
                    "('2021-01-01', '00:00:00', '7777', 'SC', 'temperaturaMedia', 10, false)," +
                    "('2021-01-01', '00:00:00', '7777', 'SC', 'umidadeMedia', 80.0, false)," +
                    "('2021-01-01', '00:00:00', '7777', 'SC', 'velVento', 10.0, false)," +
                    "('2021-01-01', '00:00:00', '7777', 'SC', 'dirVento', 190.0, false)," +
                    "('2021-01-01', '00:00:00', '7777', 'SC', 'chuva', 5.0, false)," +
                    "('2021-01-01', '01:00:00', '83726', 'SC', 'temperaturaMedia', 40.0, false)," +
                    "('2021-01-01', '01:00:00', '83726', 'SC', 'umidadeMedia', 70.0, false)," +
                    "('2021-01-01', '01:00:00', '83726', 'SC', 'velVento', 10.0, false)," +
                    "('2021-01-01', '01:00:00', '83726', 'SC', 'dirVento', 120.0, false)," +
                    "('2021-01-01', '01:00:00', '83726', 'SC', 'chuva', 5.0, false)," +
                    "('2021-01-01', '01:00:00', '8888', 'SC', 'temperaturaMedia', NULL, false)," +
                    "('2021-01-01', '01:00:00', '8888', 'SC', 'umidadeMedia', 50.0, false)," +
                    "('2021-01-01', '01:00:00', '8888', 'SC', 'velVento', 10.0, false)," +
                    "('2021-01-01', '01:00:00', '8888', 'SC', 'dirVento', 190.0, false)," +
                    "('2021-01-01', '01:00:00', '8888', 'SC', 'chuva', 5.0, false)," +
                    "('2021-01-01', '01:00:00', '6666', 'SC', 'temperaturaMedia', 10, false)," +
                    "('2021-01-01', '01:00:00', '6666', 'SC', 'umidadeMedia', 50.0, false)," +
                    "('2021-01-01', '01:00:00', '6666', 'SC', 'velVento', 10.0, false)," +
                    "('2021-01-01', '01:00:00', '6666', 'SC', 'dirVento', 190.0, false)," +
                    "('2021-01-01', '01:00:00', '6666', 'SC', 'chuva', 5.0, false)," +

                    //SP 1 registro para cada tipo
                    "('2021-01-01', '00:00:00', '777', 'SP', 'temperaturaMedia', 20.0, false)," +
                    "('2021-01-01', '00:00:00', '777', 'SP', 'umidadeMedia', 50.0, false)," +
                    "('2021-01-01', '00:00:00', '777', 'SP', 'velVento', 10.0, false)," +
                    "('2021-01-01', '00:00:00', '777', 'SP', 'dirVento', 180.0, false)," +
                    "('2021-01-01', '00:00:00', '777', 'SP', 'chuva', 0.0, false)," +
                    //TBT 1 registro para cada tipo
                    "('2021-01-01', '00:00:00', '728', 'TBT', 'temperaturaMedia', 20.0, false)," +
                    "('2021-01-01', '00:00:00', '728', 'TBT', 'umidadeMedia', 50.0, false)," +
                    "('2021-01-01', '00:00:00', '728', 'TBT', 'velVento', 10.0, false)," +
                    "('2021-01-01', '00:00:00', '728', 'TBT', 'dirVento', 180.0, false)," +
                    "('2021-01-02', '00:00:00', '728', 'TBT', 'chuva', 0.0, false)";

                    
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.err.format("Popular tabela registro SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}

