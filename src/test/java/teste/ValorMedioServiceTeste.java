package teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.dadosmeteorologicos.Services.ValorMedioService;
import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.CidadeDetalhes;
import com.example.dadosmeteorologicos.model.RegistroValorMedio;


public class ValorMedioServiceTeste {
    private static ValorMedioService valorMedioService;
    private static IniciaBancoTeste bancoTeste;

       @BeforeAll
    public static void setup() throws SQLException {
        bancoTeste = new IniciaBancoTeste();
        bancoTeste.iniciarBanco();
        bancoTeste.popularBancoTeste();
        valorMedioService = new ValorMedioService(bancoTeste.conectarBanco());
    }

    @AfterAll
    public static void tearDown() {
        bancoTeste.fecharConexao();
        bancoTeste.reiniciarBanco();
    }

    @Test
    public void getCidadesDoBancoDeDados(){
        List<Cidade> cidades = valorMedioService.getCidadesDoBancoDeDados();
        System.out.println(cidades.size());
        List<Cidade> cidadesMock = cidadesMock();

        for (Cidade cidade : cidades) {
            for (Cidade cidadeMock : cidadesMock) {                
                if (cidade.getNome().equals(cidadeMock.getNome())) {
                    assertEquals(cidade.getNome(), cidadeMock.getNome());
                    assertEquals(cidade.getSigla(), cidadeMock.getSigla());
                    assertEquals(cidade.getCidadeDetalhes().getDataPrimeiroRegistro(), cidadeMock.getCidadeDetalhes().getDataPrimeiroRegistro());
                    assertEquals(cidade.getCidadeDetalhes().getDataUltimoRegistro(), cidadeMock.getCidadeDetalhes().getDataUltimoRegistro());
                }
            }
        }
    }

    @Test
    public void consultaCidadePorIdEDatas(){
       List<RegistroValorMedio> registroValorMedio = valorMedioService.getValorMedio("SC", 
        Date.valueOf("2021-01-01"),  Date.valueOf("2021-01-01"));
        for (RegistroValorMedio registro : registroValorMedio) {
            System.out.println(registro.toString());
        }
    }

    public List<Cidade> cidadesMock(){
        List<Cidade> cidadesMockadas = new ArrayList<>();
        Cidade saoPaulo = new Cidade();
        saoPaulo.setNome("São Paulo");
        saoPaulo.setSigla("SP");
        saoPaulo.setCidadeDetalhes(new CidadeDetalhes("2021-01-01", 
            "2021-01-01"));
        Cidade taubate = new Cidade();
        taubate.setNome("Taubaté");
        taubate.setSigla("TBT");
        taubate.setCidadeDetalhes(new CidadeDetalhes("2021-01-01", 
            "2021-01-02"));

        Cidade saoCarlos = new Cidade();
        saoCarlos.setNome("São Carlos");
        saoCarlos.setSigla("SC");
        saoCarlos.setCidadeDetalhes(new CidadeDetalhes("2021-01-01", 
            "2021-01-01"));
        cidadesMockadas.add(taubate);
        cidadesMockadas.add(saoPaulo);
        cidadesMockadas.add(saoCarlos);
        return cidadesMockadas;
    }



        

}
