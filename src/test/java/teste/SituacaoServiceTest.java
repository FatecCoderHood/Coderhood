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
        
        for (Map.Entry<Cidade, RegistroSituacao> mapa : registroSituacaoPorCidade.entrySet()) {
            if (mapa.getKey().getSigla().equals("TBT")){
                RegistroSituacao registro = mapa.getValue();
                assertEquals(LocalDate.parse("2021-01-01"), registro.getDataTemperaturaMedia());
                assertEquals(LocalTime.parse("00:00:00"), registro.getHoraTemperaturaMedia());
                assertEquals("20.0", registro.getTemperaturaMedia());
                assertEquals(LocalDate.parse("2021-01-05"), registro.getDataUmidadeMedia());
                assertEquals(LocalTime.parse("12:00:00"), registro.getHoraUmidadeMedia());
                assertEquals("30.0", registro.getUmidadeMedia());
                assertEquals(LocalDate.parse("2021-01-01"), registro.getDataVelVento());
                assertEquals(LocalTime.parse("00:00:00"), registro.getHoraVelVento());
                assertEquals("10.0", registro.getVelVento());
                assertEquals(LocalDate.parse("2021-01-05"), registro.getDataDirVento());
                assertEquals(LocalTime.parse("12:00:00"), registro.getHoraDirVento());
                assertEquals("0.0", registro.getDirVento());
                assertEquals(LocalDate.parse("2021-01-05"), registro.getDataChuva());
                assertEquals(LocalTime.parse("12:00:00"), registro.getHoraChuva());
                assertEquals("15.0", registro.getChuva());
            }
        }

    }

    public void criarRegistrosTabelaSituacao(){
        List<Registro> registros = Arrays.asList(
            new Registro(LocalDate.parse("2021-01-05"), LocalTime.parse("12:00:00"), 
                "728", "TBT", "temperaturaMedia", 70.0, true),
            new Registro(LocalDate.parse("2021-01-05"), LocalTime.parse("12:00:00"), 
                "728", "TBT", "umidadeMedia", 30.0, false),
            new Registro(LocalDate.parse("2021-01-05"), LocalTime.parse("12:00:00"), 
                "728", "TBT", "velVento", null, false),
            new Registro(LocalDate.parse("2021-01-05"), LocalTime.parse("12:00:00"),
                "728", "TBT", "dirVento", 0.0, false),
            new Registro(LocalDate.parse("2021-01-05"), LocalTime.parse("12:00:00"), 
                "728", "TBT", "chuva", 15.0, false)
        );
        leitorCSVService.salvarRegistro(registros);
    } 
}