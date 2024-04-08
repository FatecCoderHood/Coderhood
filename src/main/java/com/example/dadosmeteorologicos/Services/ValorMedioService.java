package com.example.dadosmeteorologicos.Services;

import java.sql.Date;
import java.util.List;

import com.example.dadosmeteorologicos.db.ValorMedioSQL;
import com.example.dadosmeteorologicos.model.RegistroDto;


public class ValorMedioService {
  
  // Método para obter uma lista de cidades do banco de dados
  public List<String[]> getCidadesDoBancoDeDados(){
    // Cria uma nova instância da classe ValorMedioSQL
    ValorMedioSQL banco = new ValorMedioSQL();
    // Chama o método getCidadesMenuItem para obter a lista de cidades
    List<String[]> cidades = banco.getCidadesMenuItem();
    // Fecha a conexão com o banco de dados
    banco.fecharConexao();
    // Retorna a lista de cidades
    return cidades;
  }
  
  // Método para consultar uma cidade por ID e datas
  public List<RegistroDto> consultaCidadePorIdEDatas(String id, Date dataInicial, Date dataFinal){
    // Cria uma nova instância da classe ValorMedioSQL
    ValorMedioSQL banco = new ValorMedioSQL(); 
    // Chama o método getRelatorioValorMedio para obter a lista de registros da cidade
    List<RegistroDto> cidades = banco.getRelatorioValorMedio(id, dataInicial, dataFinal);
    // Fecha a conexão com o banco de dados
    banco.fecharConexao();
    // Retorna a lista de registros da cidade
    return cidades;
  }
}
