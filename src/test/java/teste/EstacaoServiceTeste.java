package teste;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.dadosmeteorologicos.Services.EstacaoService;
import com.example.dadosmeteorologicos.model.Estacao;

public class EstacaoServiceTeste {
    private static EstacaoService estacaoService;
    private static IniciaBancoTeste bancoTeste;

    @BeforeAll
    public static void setup() throws SQLException {
        bancoTeste = new IniciaBancoTeste();
        bancoTeste.iniciarBanco();
        bancoTeste.popularBancoTeste();
        estacaoService = new EstacaoService(bancoTeste.conectarBanco());
    }

    @AfterAll
    public static void tearDown() {
        bancoTeste.fecharConexao();
        bancoTeste.limparBanco();
    }

    @Test
    public void testeBuscaEstacao() {
        List<Estacao> estacao = estacoesMock();
        
        Estacao estacao = new Estacao();
        estacao.setNumero("83726");
        estacao.setSiglaCidade("SC");

        assertEquals("001", estacao.getNumero());
        assertEquals("SC", estacao.getSiglaCidade());
        assertFalse(estacaoService.buscaEstacao().isEmpty());
        
    }

    
    @Test
    public void testeDeletarEstacao() {

        estacaoService.deletarEstacao(001, "001");
        assertFalse(estacaoService.buscaEstacao().isEmpty());
    }



    @Test
    public void testeAdicionarNovaEstacao() {
        estacao.setNumero("001");
        estacao.setSiglaCidade("SC");
        
        assertEquals("001", estacao.getNumero());
        assertEquals("SC", estacao.getSiglaCidade());

        estacaoService.adicionarNovaEstacao(estacao.getNumero(), estacao.getSiglaCidade());
        assertFalse(estacaoService.buscaEstacao().isEmpty());
    }


    public List<Estacao> estacoesMock() {
        Estacao estacao83726 = new Estacao();
        estacao83726.setNumero("83726");
        estacao83726.setSiglaCidade("SC");

        Estacao estacao777 = new Estacao();
        estacao777.setNumero("777");
        estacao777.setSiglaCidade("SP");

        Estacao estacao420 = new Estacao();
        estacao420.setNumero("420");
        estacao420.setSiglaCidade("SJC");

        Estacao estacao728 = new Estacao();
        estacao728.setNumero("728");
        estacao728.setSiglaCidade("TBT");
    }
    
}

