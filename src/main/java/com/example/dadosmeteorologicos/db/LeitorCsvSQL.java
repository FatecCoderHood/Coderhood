package com.example.dadosmeteorologicos.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.dadosmeteorologicos.model.Registro;

public class LeitorCsvSQL extends IniciaBanco{
    private Connection conn;

    public LeitorCsvSQL() {
        this.conn = super.conectarBanco();
    }

    public int[] salvarRegistro(List<Registro> listaRegistroDto) {
        int[] salvoDuplicado = new int[]{0, 0};
        try {
            if (conn != null) {
                conn.setAutoCommit(false);
                int registrosSalvos = 0;
                int registrosDuplicados = 0;

                String sql = "INSERT INTO Registro " + 
                "(data, hora, estacao, siglaCidade, tipo, valor, suspeito)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)"+
                "ON CONFLICT(data, hora, estacao, siglaCidade, tipo) DO NOTHING";
                // talvez seja possivel ja verificar aqui se o registro está suspeito.
                 // PreparedStatement é uma interface usada para executar consultas SQL parametrizadas. 
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    for (Registro registro : listaRegistroDto) {
                        pstmt.setDate(1, java.sql.Date.valueOf(registro.getData()));
                        pstmt.setTime(2, java.sql.Time.valueOf(registro.getHora()));
                        pstmt.setString(3, registro.getEstacao());
                        pstmt.setString(4, registro.getSiglaCidade());
                        pstmt.setString(5, registro.getTipo());
                        setDoubleOrNull(pstmt, 6, registro.getValor());
                        pstmt.setBoolean(7, registro.isSuspeito());

                        // O método executeUpdate() retorna um inteiro que representa o número de linhas 
                        // afetadas pela operação SQL. Isso inclui as linhas inseridas, 
                        // atualizadas ou excluídas.
                        // No contexto do seu código, registrosAfetados == 0 verifica se a operação INSERT não afetou 
                        // nenhuma linha.
                        int registrosAfetados = pstmt.executeUpdate();
                        if (registrosAfetados == 0) {
                            registrosDuplicados++;
                        } else {
                            registrosSalvos++;
                        }  
                    }
                    conn.commit(); // Confirma as alterações
                    salvoDuplicado[0] = registrosSalvos;
                    salvoDuplicado[1] = registrosDuplicados;
                } catch (SQLException e) {
                    conn.rollback(); // Desfaz as alterações se ocorrer um erro
                    throw e;
                }
                return salvoDuplicado;
            }
        } catch (SQLException e) {
            System.err.format("SQL Stateee: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return salvoDuplicado;
    }

    private void setDoubleOrNull(PreparedStatement pstmt, int indice, Double value) throws SQLException {
        if (value != null) {
            pstmt.setDouble(indice, value);
        } else {
            pstmt.setNull(indice, java.sql.Types.DOUBLE);
        }
    }

    public  boolean verificarCidadeExiste(String siglaCidade){
        // Este método verifica se a cidade com a sigla fornecida já existe no banco de dados.
        boolean cidadeExiste = false;
        try {
            // Verifica se a conexão com o banco de dados está estabelecida
            if (conn != null) {
                // Prepara a consulta SQL para verificar se a cidade já existe no banco de dados
                String sql = "SELECT * FROM Cidade WHERE sigla = ?";
                // PreparedStatement é uma interface usada para executar consultas SQL parametrizadas. 
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, siglaCidade.toUpperCase());
                ResultSet rs = stmt.executeQuery();

                // Se a cidade existir no banco de dados, cidadeEstacaoExiste é true
                if (rs.next()) {
                    cidadeExiste = true;
                }
            }
        } catch (SQLException e) {
            System.err.format("verificarCidadeExiste SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return cidadeExiste;
    }

    public void criarCidade(String nomeCidade, String siglaCidade){
        try {
            if (conn != null) {
                String sql = "INSERT INTO Cidade (nome, sigla) VALUES (?, ?)";
                // PreparedStatement é uma interface usada para executar consultas SQL parametrizadas.
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, nomeCidade);
                stmt.setString(2, siglaCidade.toUpperCase());
                stmt.executeUpdate();
              
            }
        } catch (SQLException e) {
            System.err.format("criarCidade SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public String ObterNomeCidade(String siglaCidade){
        // Este método retorna o nome da cidade com base na sigla da cidade.
        String nomeCidade = null;
        try {
            // Verifica se a conexão com o banco de dados está estabelecida
            if (conn != null) {
                // Prepara a consulta SQL para obter o nome da cidade
                String sql = "SELECT nome FROM Cidade WHERE sigla = ?";
                // PreparedStatement é uma interface usada para executar consultas SQL parametrizadas. 
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, siglaCidade.toUpperCase());
                ResultSet rs = stmt.executeQuery();

                // Se a consulta SQL retornar pelo menos uma linha, obtém o nome da cidade
                if (rs.next()) {
                    nomeCidade = rs.getString("nome");
                }
            }
        } catch (SQLException e) {
            System.err.format("ObterNomeCidade SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return nomeCidade;
    }

    public boolean verificarEstacaoExiste(String numeroEstacao){
        // Este método verifica se a estação com o número fornecido já existe no banco de dados.
        boolean estacaoExiste = false;
        try {
            if (conn != null) {
                // Prepara a consulta SQL para verificar se a estação já existe no banco de dados
                String sql = "SELECT * FROM Estacao WHERE nome = ?";
                // PreparedStatement é uma interface usada para executar consultas SQL parametrizadas. 
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, numeroEstacao);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    estacaoExiste = true;
                }
            }
        } catch (SQLException e) {
            System.err.format("verificarEstacaoExiste SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return estacaoExiste;
    }

    public void criarEstacao(String numeroEstacao, String siglaCidade){
        try {
            if (conn != null) {
    
                String sql = "INSERT INTO Estacao (nome, siglaCidade) VALUES (?, ?)";
                // PreparedStatement é uma interface usada para executar consultas SQL parametrizadas.
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, numeroEstacao);
                stmt.setString(2, siglaCidade.toUpperCase());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.format("criarEstacao SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public boolean validarCidadeEstacao(String siglaCidade, String numeroEstacao){
        // Este método verifica se a cidade e a estação fornecidas correspondem.
        boolean cidadeEstacaoValido = false;
        try {
            // Verifica se a conexão com o banco de dados está estabelecida
            if (conn != null) {
                // Prepara a consulta SQL para verificar se a cidade e a estação correspondem
                String sql = "SELECT siglaCidade FROM Estacao WHERE nome = ?";
                // PreparedStatement é uma interface usada para executar consultas SQL parametrizadas.
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, numeroEstacao);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String siglaCidadeEstacao = rs.getString("siglaCidade");
                    System.out.println("siglaCidadeEstacao " + siglaCidadeEstacao + " siglaCidade " + siglaCidade);
                    if(siglaCidadeEstacao.trim().equals(siglaCidade.trim())){
                        cidadeEstacaoValido = true;
                    }              
                }else{
                    cidadeEstacaoValido = true;
                }
            }
        } catch (SQLException e) {
            System.err.format("validarCidadeEstacao SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return cidadeEstacaoValido;
    }

}
