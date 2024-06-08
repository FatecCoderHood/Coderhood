package teste;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.example.dadosmeteorologicos.Services.SituacaoService;
import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.RegistroSituacao;

public class SituacaoServiceTest {

    @Test
    public void testeGetNome() {
        Cidade cidade = new Cidade();
        cidade.setNome("Piranga");
        assertEquals("Piranga", cidade.getNome(), "O nome da cidade deve ser Piranga");
    }

    @Test
    public void testeGetSigla() {
        Cidade cidade = new Cidade();
        cidade.setSigla("PRG");
        assertEquals("PRG", cidade.getSigla(), "A sigla da cidade deve ser PRG");
    }

    @Test
    public void testeSetCidadeESigla() {
        RegistroSituacao registro = new RegistroSituacao();
        registro.setCidadeESigla("Piranga - PRG");
        assertEquals("Piranga - PRG", registro.getCidadeESigla(), "A cidade e a Sigla devem ser 'Piranga - PRG'");
    }

    @Test
    public void testeGetRegistroSituacao() {
        Cidade cidade = new Cidade();
        cidade.setNome("Piranga");
        cidade.setSigla("PRG");

        List<Cidade> cidades = new ArrayList<>();
        cidades.add(cidade);

        SituacaoService situacaoService = new SituacaoService();

        Map<Cidade, RegistroSituacao> registroSituacaoPorCidade = situacaoService.getRegistroSituacao(cidades);

        assertNotNull(registroSituacaoPorCidade, "O mapa não pode ser nulo");
        assertTrue(registroSituacaoPorCidade.containsKey(cidade), "O mapa deve conter o objeto Cidade");

        RegistroSituacao registro = registroSituacaoPorCidade.get(cidade);

        assertNotNull(registro, "O RegistroSituacao não pode ser nulo");
        assertEquals(cidade.getNome() + " - " + cidade.getSigla(), registro.getCidadeESigla(), "O RegistroSituacao não contém a cidade e sigla corretas");
    }
}