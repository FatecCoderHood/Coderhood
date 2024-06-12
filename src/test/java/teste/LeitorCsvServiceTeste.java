package teste;

import com.example.dadosmeteorologicos.Services.CidadeService;
import com.example.dadosmeteorologicos.Services.EstacaoService;
import com.example.dadosmeteorologicos.Services.LeitorCsvService;
import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.Estacao;
import com.example.dadosmeteorologicos.model.Registro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LeitorCsvServiceTeste {

    private static LeitorCsvService leitorCsvService;
    private static IniciaBancoTeste bancoTeste;
    private static Connection conn;
    private static CidadeService cidadeService;
    private static EstacaoService estacaoService;


    @BeforeAll
    public static void setUp() {
        bancoTeste = new IniciaBancoTeste();
        bancoTeste.reiniciarBanco();
        bancoTeste.iniciarBanco();
        bancoTeste.popularBancoTeste();  
        conn = bancoTeste.conectarBanco();
        leitorCsvService = new LeitorCsvService(conn);
        cidadeService = new CidadeService(conn);
        estacaoService = new EstacaoService(conn);
    }

    @AfterAll
    public static void tearDown() {
        bancoTeste.fecharConexao();
        bancoTeste.reiniciarBanco();
    }

    @Test
    public void testSalvarRegistro() {
        List<Registro> listaRegistro = criaRegistrosQueSeraoSalvosNobanco();
        int[] infoRegistrosSalvos = leitorCsvService.salvarRegistro(listaRegistro);

        int salvos = 0;
        int duplicados = 1;

        assertEquals(30, infoRegistrosSalvos[salvos]);
        assertEquals(1, infoRegistrosSalvos[duplicados]);
    }
    
    @Test
    public void validarNomeCidadePelaSigla() {
        Cidade cidade = new Cidade();
        cidade.setNome("Taubaté");
        cidade.setSigla("TBT");
        
        String validandoSigla = leitorCsvService.validarNomeCidadePelaSigla(cidade.getSigla());

        assertEquals("Taubaté", validandoSigla);
        assertNotEquals("nao é o nome da cidade", validandoSigla);

    }

    @Test
    public void testCriarCidade() {
        Cidade cidadeRepertida = new Cidade();
        cidadeRepertida.setNome("Teste");
        cidadeRepertida.setSigla("SP");

        leitorCsvService.criarCidade(cidadeRepertida.getNome(), cidadeRepertida.getSigla());

        assertEquals(4, cidadeService.getCidades().size());

        Cidade cidadeNova= new Cidade();
        cidadeNova.setNome("TTTT");
        cidadeNova.setSigla("TT");

        leitorCsvService.criarCidade(cidadeNova.getNome(), cidadeNova.getSigla());

        assertEquals(5, cidadeService.getCidades().size());

    }
    


    @Test
    public void testCriarEstacao() {
        Estacao estacaorepetida = new Estacao();
        estacaorepetida.setSiglaCidade("SC");
        estacaorepetida.setNumero("6666");
        System.out.println(estacaoService.buscaEstacao().toString());

        
        leitorCsvService.criarEstacao(estacaorepetida.getSiglaCidade(), estacaorepetida.getNumero());
        

        assertEquals(8, estacaoService.buscaEstacao().size());

        Estacao estacaoNova = new Estacao();
        estacaoNova.setSiglaCidade("TET");
        estacaoNova.setNumero("999");

        leitorCsvService.criarEstacao(estacaoNova.getSiglaCidade(), estacaoNova.getNumero());

        assertEquals(9, estacaoService.buscaEstacao().size());
        
    }

    @Test
    public void testvalidarCidadeEstacao() {
        Estacao estacao = new Estacao();
        estacao.setSiglaCidade("SC");
        estacao.setNumero("7777");
        
        boolean verificandoEstacao = leitorCsvService.validarCidadeEstacao(estacao.getSiglaCidade(), estacao.getNumero());

        assertTrue(verificandoEstacao, "A estação não é válida para a cidade com a sigla 'SC'");

    }

    @Test
    public void testregistrosSuspeitos() {
        List<Registro> listaRegistros = criaRegistrosQueSeraoSalvosNobanco();
        int quantidadeRegistrosSuspeitos = leitorCsvService.registrosSuspeitos(listaRegistros);

        assertEquals(6, quantidadeRegistrosSuspeitos);
    
    }
 

    public List<Registro> criaRegistrosQueSeraoSalvosNobanco() {
        List<Registro> listaRegistroMock = new ArrayList<>();
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "83726", "SC", "temperaturaMedia", 20.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "83726", "SC", "temperaturaMedia", 20.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "83726", "SC", "umidadeMedia", 50.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "83726", "SC", "velVento", 10.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "83726", "SC", "dirVento", 180.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "83726", "SC", "chuva", 0.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "8888", "SC", "temperaturaMedia", 55.0, true));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "8888", "SC", "umidadeMedia", 50.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "8888", "SC", "velVento", 10.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "8888", "SC", "dirVento", 190.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "8888", "SC", "chuva", 5.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "7777", "SC", "temperaturaMedia", 10.0, true));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "7777", "SC", "umidadeMedia", 80.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "7777", "SC", "velVento", 10.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "7777", "SC", "dirVento", 190.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "7777", "SC", "chuva", 5.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "83726", "SC", "temperaturaMedia", 40.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "83726", "SC", "umidadeMedia", 70.0, true));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "83726", "SC", "velVento", 10.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "83726", "SC", "dirVento", 120.0, true));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "83726", "SC", "chuva", 5.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "8888", "SC", "temperaturaMedia", null, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "8888", "SC", "umidadeMedia", 50.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "8888", "SC", "velVento", 10.0, true));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "8888", "SC", "dirVento", 190.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "8888", "SC", "chuva", 5.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "6666", "SC", "temperaturaMedia", 10.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "6666", "SC", "umidadeMedia", 50.0, true));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "6666", "SC", "velVento", 10.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "6666", "SC", "dirVento", 190.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "6666", "SC", "chuva", 5.0, false));

        return listaRegistroMock;
    }

}
