package teste;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.dadosmeteorologicos.Services.ValorMedioService;
import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.CidadeDetalhes;
import com.example.dadosmeteorologicos.model.RegistroValorMedio;
import com.example.dadosmeteorologicos.model.ValorMedioInfo;


public class ValorMedioServiceTest {
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
    public void getValorMedio(){
        List<RegistroValorMedio> registroValorMedio = valorMedioService.getValorMedio("SC", 
        Date.valueOf("2021-01-01"),  Date.valueOf("2021-01-01"));
        RegistroValorMedio primeiroRegistro = registroValorMedio.get(0);
        List<ValorMedioInfo> primeiroValorMedioInfo = primeiroRegistro.getValorMedioInfos();

        // Verifique os valores do primeiro registro
        assertEquals(LocalDate.parse("2021-01-01"), primeiroRegistro.getData());
        assertEquals(LocalTime.parse("00:00"), primeiroRegistro.getHora());
        assertEquals("SC", primeiroRegistro.getSiglaCidade());

        for (ValorMedioInfo info : primeiroValorMedioInfo) {
            switch(info.getTipo()){
                case "temperaturaMedia":
                    assertEquals(28.3, info.getValor(), 1);
                    break;
                case "umidadeMedia":
                    assertEquals(60.0, info.getValor(), 1);
                    break;
                case "velVento":
                    assertEquals(10.0, info.getValor(), 1);
                    break;
                case "dirVento":
                    assertEquals(186.67, info.getValor(), 1);
                    break;
                case "chuva":
                    assertEquals(3.33, info.getValor(), 1);
                    break;
            }
        }

        RegistroValorMedio segundoRegistro = registroValorMedio.get(1);
        List<ValorMedioInfo> segundoValorMedioInfo = segundoRegistro.getValorMedioInfos();
        assertEquals(LocalDate.parse("2021-01-01"), segundoRegistro.getData());
        assertEquals(LocalTime.parse("01:00"), segundoRegistro.getHora());

        for (ValorMedioInfo info : segundoValorMedioInfo) {
            switch(info.getTipo()){
                case "temperaturaMedia":
                    assertEquals(25, info.getValor(), 1);
                    break;
                case "umidadeMedia":
                    assertEquals(56.67, info.getValor(), 1);
                    break;
                case "velVento":
                    assertEquals(10.0, info.getValor(), 1);
                    break;
                case "dirVento":
                    assertEquals(166.67, info.getValor(), 1);
                    break;
                case "chuva":
                    assertEquals(5, info.getValor(), 1);
                    break;
            }
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
