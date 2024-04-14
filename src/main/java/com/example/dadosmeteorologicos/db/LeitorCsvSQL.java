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

    public String validarNomeCidadePelaSigla(String siglaCidade){
        // Este método retorna o nome da cidade no banco se a sigla ja existir.
        String nomeCidadeBanco = null;
        try {
            if (conn != null) {
                String sql = "SELECT * FROM Cidade WHERE sigla = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, siglaCidade.toUpperCase());
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    nomeCidadeBanco = rs.getString("nome");
                }
            }
        } catch (SQLException e) {
            System.err.format("verificarCidadeExiste SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return nomeCidadeBanco;
    }

    public void criarCidade(String nomeCidade, String siglaCidade){
        try {
            if (conn != null) {
                String sql = "SELECT * FROM Cidade WHERE sigla = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, nomeCidade);
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) {
                    sql = "INSERT INTO Cidade (nome, sigla) VALUES (?, ?)";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, nomeCidade);
                    stmt.setString(2, siglaCidade.toUpperCase());
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.format("criarCidade SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void criarEstacao(String numeroEstacao, String siglaCidade){
        try {
            if (conn != null) {
                String sql = "SELECT * FROM Estacao WHERE nome = ? AND siglaCidade = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, numeroEstacao);
                stmt.setString(2, siglaCidade.toUpperCase());
                ResultSet rs = stmt.executeQuery();
                if (!rs.next()) {
                    sql = "INSERT INTO Estacao (nome, siglaCidade) VALUES (?, ?)";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, numeroEstacao);
                    stmt.setString(2, siglaCidade.toUpperCase());
                    stmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.format("criarEstacao SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public boolean validarCidadeEstacao(String siglaCidade, String numeroEstacao){
        // Este método verifica se a cidade e a estação fornecidas correspondem.
        boolean cidadeEstacaoValido = true;
        try {
            // Verifica se a conexão com o banco de dados está estabelecida
            if (conn != null) {
                // Prepara a consulta SQL para verificar se a cidade e a estação correspondem
                String sql = "SELECT siglaCidade FROM Estacao WHERE nome = ?";
                // PreparedStatement é uma interface usada para executar consultas SQL parametrizadas.
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, numeroEstacao);
                ResultSet rs = stmt.executeQuery();
    
                if(rs.next()) {
                    String siglaCidadeEstacao = rs.getString("siglaCidade");
                    System.out.println("siglaCidadeEstacao " + siglaCidadeEstacao + " siglaCidade " + siglaCidade);
                    if(!siglaCidadeEstacao.trim().equals(siglaCidade.trim())){
                        cidadeEstacaoValido = false; 
                    }              
                }
            }
        } catch (SQLException e) {
            System.err.format("validarCidadeEstacao SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return cidadeEstacaoValido;
    }

}
