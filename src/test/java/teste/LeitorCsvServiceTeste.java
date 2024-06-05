package teste;

import com.example.dadosmeteorologicos.Services.LeitorCsvService;
import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.Estacao;
import com.example.dadosmeteorologicos.model.Registro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @BeforeAll
    public static void setUp() {
        bancoTeste = new IniciaBancoTeste(); 
        bancoTeste.iniciarBanco();
        bancoTeste.popularBancoTeste();  
        leitorCsvService = new LeitorCsvService(bancoTeste.conectarBanco());
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

    }

   















    @Test
    public void validarCidadeEstacao() {
        Estacao estacao = new Estacao();
        estacao.setSiglaCidade("SC");
        estacao.setNumero("7777");
        
        boolean verificandoEstacao = leitorCsvService.validarCidadeEstacao(estacao.getSiglaCidade(), estacao.getNumero());

        assertTrue(verificandoEstacao, "A estação não é válida para a cidade com a sigla 'SC'");

    }




   /* @Test
    public void validarCidadeEstacao() {
        Cidade cidade = new Cidade();
        cidade.setNome("Taubaté");
        cidade.setSigla("TBT");
        
        boolean validandoSigla = leitorCsvService.validarNomeCidadePelaSigla(cidade.getSigla());

        assertEquals("Taubaté", validandoSigla);

    }*/

    public List<Registro> criaRegistrosQueSeraoSalvosNobanco() {
        List<Registro> listaRegistroMock = new ArrayList<>();
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "83726", "SC", "temperaturaMedia", 20.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "83726", "SC", "temperaturaMedia", 20.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "83726", "SC", "umidadeMedia", 50.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "83726", "SC", "velVento", 10.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "83726", "SC", "dirVento", 180.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "83726", "SC", "chuva", 0.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "8888", "SC", "temperaturaMedia", 55.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "8888", "SC", "umidadeMedia", 50.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "8888", "SC", "velVento", 10.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "8888", "SC", "dirVento", 190.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "8888", "SC", "chuva", 5.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "7777", "SC", "temperaturaMedia", 10.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "7777", "SC", "umidadeMedia", 80.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "7777", "SC", "velVento", 10.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "7777", "SC", "dirVento", 190.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(0, 0, 0), "7777", "SC", "chuva", 5.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "83726", "SC", "temperaturaMedia", 40.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "83726", "SC", "umidadeMedia", 70.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "83726", "SC", "velVento", 10.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "83726", "SC", "dirVento", 120.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "83726", "SC", "chuva", 5.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "8888", "SC", "temperaturaMedia", null, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "8888", "SC", "umidadeMedia", 50.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "8888", "SC", "velVento", 10.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "8888", "SC", "dirVento", 190.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "8888", "SC", "chuva", 5.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "6666", "SC", "temperaturaMedia", 10.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "6666", "SC", "umidadeMedia", 50.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "6666", "SC", "velVento", 10.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "6666", "SC", "dirVento", 190.0, false));
        listaRegistroMock.add(new Registro(LocalDate.of(2021, 1, 3), LocalTime.of(1, 0, 0), "6666", "SC", "chuva", 5.0, false));

        return listaRegistroMock;
    }

}





/*
// Teste salvarRegistro
int[] resultado = salvarRegistro(listaRegistroDto);

 // Teste validarNomeCidadePelaSigla
String nomeCidade = validarNomeCidadePelaSigla("SP");

// Teste criarCidade
criarCidade("São Paulo", "SP");

// Teste criarEstacao
criarEstacao("001", "SP");

// Teste  validarCidadeEstacao
boolean cidadeEstacaoValido = validarCidadeEstacao("SP", "001");

// Criando uma lista de registros fictícios para teste
List<Registro> listaRegistroDto = new ArrayList<>();
listaRegistroDto.add(new Registro("Temperatura", 25, "Leitura da temperatura ambiente", false));
listaRegistroDto.add(new Registro("Umidade", 60, "Leitura da umidade relativa do ar", true));

// Teste registrosSuspeitos
int registrosSuspeitos = registrosSuspeitos(listaRegistroDto);

 */