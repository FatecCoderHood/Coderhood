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
        assertEquals(4, estacoes.size());
        for(Estacao estacao : estacoes) {
            if (estacao.getNumero().equals("83726")) {
                estacaoService.deletarEstacao(estacao.getId(), estacao.getNumero());
                assertFalse(estacaoService.buscaEstacao().contains(estacao));
            }
        }
        estacoes = estacaoService.buscaEstacao();
        assertEquals(3, estacoes.size());
    }
    @Test
    public void testeAdicionarNovaEstacao() {
        
        List<Estacao> estacoes = Arrays.asList(
            // Estação Existente
            new Estacao("83726", "SC"),
            // Estação com número associado a uma estação existente
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
        assertEquals(6, novasEstacoes.size());

    }

    @Test
    public void testeAtualizarEstacao(){

        List<Estacao> estacoes = estacaoService.buscaEstacao();
        Estacao estacaoAntiga = estacoes.get(0);
        assertEquals("83726", estacaoAntiga.getNumero());
        assertEquals("SC", estacaoAntiga.getSiglaCidade());

        Estacao estacaoAtualizada = new Estacao("0000", "XX");

        estacaoAtualizada.setDescricao("Estação Atualizada");
        estacaoAtualizada.setNome("Estação Atualizada");
        estacaoAtualizada.setLatitude(0.9999);
        estacaoAtualizada.setLongitude(0.9999);

        System.out.println(estacaoAntiga.toString());
        System.out.println("...........");
        System.out.println(estacaoAtualizada.toString());
        System.out.println("...........");

        estacaoService.atualizarEstacao(estacaoAntiga.getId(), estacaoAtualizada);

        estacoes = estacaoService.buscaEstacao();

        System.out.println(estacaoAtualizada.toString());
    }
}