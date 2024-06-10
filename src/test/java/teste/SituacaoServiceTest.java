package teste;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.dadosmeteorologicos.Services.CidadeService;
import com.example.dadosmeteorologicos.Services.LeitorCsvService;
import com.example.dadosmeteorologicos.Services.SituacaoService;
import com.example.dadosmeteorologicos.model.RegistroSituacao;
import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.Registro;

public class SituacaoServiceTest {

    private static SituacaoService situacaoService;
    private static CidadeService cidadeService;
    private static IniciaBancoTeste bancoTeste;
    private static LeitorCsvService leitorCSVService;

    @BeforeAll
    public static void setup() throws SQLException {
        bancoTeste = new IniciaBancoTeste();
        bancoTeste.reiniciarBanco();
        bancoTeste.iniciarBanco();
        bancoTeste.popularBancoTeste();
        situacaoService = new SituacaoService(bancoTeste.conectarBanco());
        cidadeService = new CidadeService(bancoTeste.conectarBanco());
        leitorCSVService = new LeitorCsvService(bancoTeste.conectarBanco());
    }

        

    @AfterAll
    public static void tearDown() {
        bancoTeste.fecharConexao();
        bancoTeste.reiniciarBanco();
    }

    @Test
    public void testeGetRegistroSituacao() {
        criarRegistrosTabelaSituacao();
        List<Cidade> cidades = cidadeService.getCidades();
        Map<Cidade, RegistroSituacao> registroSituacaoPorCidade = situacaoService.getRegistroSituacao(cidades);
        for (Map.Entry<Cidade, RegistroSituacao> entry : registroSituacaoPorCidade.entrySet()) {
            if (entry.getKey().getSigla().equals("TBT"))
                System.out.println(entry);
        }

    }

    public void criarRegistrosTabelaSituacao(){
        List<Registro> registros = Arrays.asList(
            new Registro(LocalDate.parse("2021-01-05"), LocalTime.parse("12:00:00"), "728", "TBT", "chuva", 0.0, false),
            new Registro(LocalDate.parse("2021-01-05"), LocalTime.parse("12:00:00"), "728", "TBT", "dirVento", 0.0, false),
            new Registro(LocalDate.parse("2021-01-05"), LocalTime.parse("12:00:00"), "728", "TBT", "temperaturaMedia", 500.0, true),
            new Registro(LocalDate.parse("2021-01-05"), LocalTime.parse("12:00:00"), "728", "TBT", "umidadeMedia", null, false),
            new Registro(LocalDate.parse("2021-01-05"), LocalTime.parse("12:00:00"), "728", "TBT", "velVento", 0.0, false)
        );
        leitorCSVService.salvarRegistro(registros);
    } 
}