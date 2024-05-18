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
                String sql = "SELECT * from registro WHERE siglaCidade = ? AND suspeito = false AND valor is not null ORDER BY data DESC, hora DESC LIMIT 5";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, cidade.getSigla());
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){

                    registro.setSiglaCidade(rs.getString("siglaCidade"));
                    registro.setEstacao(rs.getString("estacao"));
                    registro.setData(rs.getDate("data").toLocalDate());
                    registro.setHora(rs.getTime("hora").toLocalTime());

                    String tipo = rs.getString("tipo");
                    Double valor = rs.getDouble("valor");

                    System.out.println("data:" + registro.getData() + "Hora:" + registro.getHora() + "Tipo: " + tipo + " Valor: " + valor);

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

