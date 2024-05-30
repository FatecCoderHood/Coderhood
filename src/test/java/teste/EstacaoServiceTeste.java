package teste;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.dadosmeteorologicos.Services.EstacaoService;

public class EstacaoServiceTeste {

    @Test
    public void testeBuscaEstacao() {
        EstacaoService estacaoService = new EstacaoService();
        estacaoService.buscaEstacao();
        assertFalse(estacaoService.buscaEstacao().isEmpty());
    }

    @Test
    public void testeDeletarEstacao() {
        EstacaoService estacaoService = new EstacaoService();
        estacaoService.deletarEstacao(1, "001");
        assertFalse(estacaoService.buscaEstacao().isEmpty());
    }
}
