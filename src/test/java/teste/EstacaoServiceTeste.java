package teste;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.dadosmeteorologicos.Services.CidadeService;
import com.example.dadosmeteorologicos.Services.EstacaoService;
import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.Estacao;

public class EstacaoServiceTeste {
    private static EstacaoService estacaoService;
    private static CidadeService cidadeService;
    private static IniciaBancoTeste bancoTeste;

    @BeforeAll
    public static void setup() throws SQLException {
        bancoTeste = new IniciaBancoTeste();
        bancoTeste.reiniciarBanco();
        bancoTeste.iniciarBanco();
        bancoTeste.popularBancoTeste();
        estacaoService = new EstacaoService(bancoTeste.conectarBanco());
        cidadeService = new CidadeService(bancoTeste.conectarBanco());
    }

    @AfterEach
    public void limparBanco() {
        bancoTeste.limparBanco();
        bancoTeste.popularBancoTeste();
    }
        

    @AfterAll
    public static void tearDown() {
        bancoTeste.fecharConexao();
        bancoTeste.reiniciarBanco();
    }

    @Test
    public void testeBuscaEstacao() {

        List<Estacao> estacoes = estacaoService.buscaEstacao();
        assertFalse(estacoes.isEmpty());
        
        Estacao estacaoNaoExistente = new Estacao();

        estacaoNaoExistente.setNumero("0000");
        estacaoNaoExistente.setSiglaCidade("XX");

        assertFalse(estacoes.contains(estacaoNaoExistente));

        Estacao estacaoExistente = estacoes.get(0);
        assertEquals("83726", estacaoExistente.getNumero());
        assertEquals("SC", estacaoExistente.getSiglaCidade());

        assertTrue(estacoes.contains(estacaoExistente));
    }
    @Test
    public void testeDeletarEstacao() {
        
        List<Estacao> estacoes = estacaoService.buscaEstacao();
        for(Estacao estacao : estacoes) {
            if (estacao.getNumero().equals("83726")) {
                estacaoService.deletarEstacao(estacao.getId(), estacao.getNumero());
                assertFalse(estacaoService.buscaEstacao().contains(estacao));
            }
        }
        estacoes = estacaoService.buscaEstacao();
    }
    @Test
    public void testeAdicionarNovaEstacao() {
        
        List<Estacao> estacoes = Arrays.asList(
            // Estação existente.
            new Estacao("83726", "SC"),
            // Estação associada a outra cidade
            new Estacao("83726", "SP"),
            // Estação Inexistente
            new Estacao("83727", "JC"),
            // Nova Estação
            new Estacao("9999","SC")
        );

        assertFalse(estacaoService.numeroEstacaoValido(estacoes.get(0).getNumero()));
        assertFalse(estacaoService.numeroEstacaoValido(estacoes.get(1).getNumero()));
        assertTrue(estacaoService.numeroEstacaoValido(estacoes.get(2).getNumero()));
        assertTrue(estacaoService.numeroEstacaoValido(estacoes.get(3).getNumero()));

        assertTrue(estacaoService.siglaCidadeExiste(estacoes.get(0).getSiglaCidade()));
        assertTrue(estacaoService.siglaCidadeExiste(estacoes.get(1).getSiglaCidade()));
        assertFalse(estacaoService.siglaCidadeExiste(estacoes.get(2).getSiglaCidade()));
        Cidade novaCidade = new Cidade("Jacareí", "JC");
        cidadeService.criarCidade(novaCidade.getNome(),novaCidade.getSigla());
        assertTrue(estacaoService.siglaCidadeExiste(estacoes.get(2).getSiglaCidade()));
        assertTrue(estacaoService.siglaCidadeExiste(estacoes.get(3).getSiglaCidade()));

        for(Estacao estacao : estacoes) {
            if(estacaoService.numeroEstacaoValido(estacao.getNumero())) {
                if(estacaoService.siglaCidadeExiste(estacao.getSiglaCidade())) {
                    estacaoService.adicionarNovaEstacao(estacao.getNumero(), estacao.getSiglaCidade());
                }
            }
        }

        List<Estacao> novasEstacoes = estacaoService.buscaEstacao();
        System.out.println(novasEstacoes.toString());
        
        assertEquals(9, novasEstacoes.size());
        for(Estacao estacao : novasEstacoes) {
            if(estacao.getNumero() == "83727") {
                assertEquals("JC", estacao.getSiglaCidade());
            }
            if(estacao.getNumero() == "9999") {
                assertEquals("SC", estacao.getSiglaCidade());
            }
        }
    }

    @Test
    public void testeAtualizarEstacao(){

        // Criando lista com estações contidas no banco;
        List<Estacao> estacoes = estacaoService.buscaEstacao();

        // Verificando se a estação foi criada
        Estacao estacaoAntiga = estacoes.get(0);
        assertEquals("83726", estacaoAntiga.getNumero());
        assertEquals("SC", estacaoAntiga.getSiglaCidade());

        
        estacaoAntiga.setDescricao("Estação Antiga");
        estacaoAntiga.setNome("Estação Antiga");
        estacaoAntiga.setLatitude(0.9999);
        estacaoAntiga.setLongitude(0.9999);

        estacaoService.atualizarEstacao(estacaoAntiga.getId(), estacaoAntiga);

        List<Estacao> novasEstacoes = estacaoService.buscaEstacao();

        for(Estacao estacao : novasEstacoes) {
            if(estacaoAntiga.getId() == estacao.getId()) {
                assertEquals("Estação Antiga", estacaoAntiga.getDescricao());
                assertEquals("Estação Antiga", estacaoAntiga.getNome());
                assertEquals(0.9999, estacaoAntiga.getLatitude());
                assertEquals(0.9999, estacaoAntiga.getLongitude());
            }
        }
    }
}