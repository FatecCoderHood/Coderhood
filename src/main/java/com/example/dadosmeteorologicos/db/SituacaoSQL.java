package com.example.dadosmeteorologicos.db;

import com.example.dadosmeteorologicos.model.RegistroSituacao;
import com.example.dadosmeteorologicos.model.Cidade;

import java.sql.*;


public class SituacaoSQL extends IniciaBanco {
    // Atributo de conexão
    protected Connection conn;

    public SituacaoSQL() {
        conn = super.conectarBanco();
    }

    public RegistroSituacao getRegistroSituacao(Cidade cidade){
        RegistroSituacao registro = new RegistroSituacao();
        try{
            if(conn != null){
                String sql = "(SELECT *, 'temperaturaMedia' as tipo FROM registro WHERE siglaCidade = ? AND tipo = 'temperaturaMedia' AND suspeito = FALSE AND valor IS NOT NULL ORDER BY data DESC, hora DESC LIMIT 1) " +
                "UNION ALL " +
                "(SELECT *, 'umidadeMedia' as tipo FROM registro WHERE siglaCidade = ? AND tipo = 'umidadeMedia' AND suspeito = FALSE AND valor IS NOT NULL ORDER BY data DESC, hora DESC LIMIT 1) " +
                "UNION ALL " +
                "(SELECT *, 'velVento' as tipo FROM registro WHERE siglaCidade = ? AND tipo = 'velVento' AND suspeito = FALSE AND valor IS NOT NULL ORDER BY data DESC, hora DESC LIMIT 1) " +
                "UNION ALL " +
                "(SELECT *, 'dirVento' as tipo FROM registro WHERE siglaCidade = ? AND tipo = 'dirVento' AND suspeito = FALSE AND valor IS NOT NULL ORDER BY data DESC, hora DESC LIMIT 1) " +
                "UNION ALL " +
                "(SELECT *, 'chuva' as tipo FROM registro WHERE siglaCidade = ? AND tipo = 'chuva' AND suspeito = FALSE AND valor IS NOT NULL ORDER BY data DESC, hora DESC LIMIT 1)";
                
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, cidade.getSigla());
                stmt.setString(2, cidade.getSigla());
                stmt.setString(3, cidade.getSigla());
                stmt.setString(4, cidade.getSigla());
                stmt.setString(5, cidade.getSigla());
                ResultSet rs = stmt.executeQuery();
    
                while(rs.next()){
                    registro.setSiglaCidade(rs.getString("siglaCidade"));
                    registro.setData(rs.getDate("data").toLocalDate());
                    registro.setHora(rs.getTime("hora").toLocalTime());
    
                    String tipo = rs.getString("tipo");
                    Double valor = rs.getDouble("valor");
    
                    switch (tipo){
                        case "chuva":
                            registro.setChuva(valor);
                            break;
                        case "dirVento":
                            registro.setDirVento(valor);
                            break;
                        case "temperaturaMedia":
                            registro.setTemperaturaMedia(valor);
                            break;
                        case "umidadeMedia":
                            registro.setUmidadeMedia(valor);
                            break;
                        case "velVento":
                            registro.setVelVento(valor);
                            break;
                    }
                }
                return registro;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    }


