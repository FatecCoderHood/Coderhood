package teste;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.dadosmeteorologicos.Services.CidadeService;
import com.example.dadosmeteorologicos.model.Cidade;

public class CidadeServiceTeste {
    private static CidadeService cidadeService;
    private static IniciaBancoTeste bancoTeste;

    @BeforeAll
    public static void setup() throws SQLException {
        bancoTeste = new IniciaBancoTeste();
        bancoTeste.iniciarBanco();
        bancoTeste.popularBancoTeste();
        cidadeService = new CidadeService(bancoTeste.conectarBanco());
    }

    @AfterAll
    public static void tearDown() {
        bancoTeste.fecharConexao();
        bancoTeste.reiniciarBanco();
    }

    @AfterEach
    public void limparBanco() {
        bancoTeste.limparBanco();
        bancoTeste.popularBancoTeste();
    }

    @Test
    public void testeGetCidades() {
        List<Cidade> cidades = cidadeService.getCidades();

        Cidade cidadeNãoExistente = new Cidade();
        cidadeNãoExistente.setNome("Cidade Não Existente");
        cidadeNãoExistente.setSigla("XX");

        assertFalse(cidades.contains(cidadeNãoExistente));

        Cidade cidadeExiste = cidades.get(0).getClass().cast(cidades.get(0));

        assertEquals("São José dos Campos", cidadeExiste.getNome());

        assertTrue(cidades.contains(cidadeExiste));
    }

    @Test
    public void testeSiglaValida() {
        // Verifica se a sigla "SP" é válida
        Cidade cidadeJaCriada = new Cidade();
        cidadeJaCriada.setSigla("SP");
        assertFalse(cidadeService.siglaValida(cidadeJaCriada.getSigla()));

        Cidade cidadeNaoCriada = new Cidade();
        cidadeNaoCriada.setSigla("TTT");
        assertTrue(cidadeService.siglaValida(cidadeNaoCriada.getSigla()));
       
    }

    @Test
    public void testeDeletarCidade() {
        List<Cidade> cidades = cidadeService.getCidades();
        // Apagando São josé dos Campos
        Cidade cidadeDeletar = new Cidade();

        for (Cidade cidade : cidades) {
            if (cidade.getNome().equals("São José dos Campos")) {
                cidadeDeletar = cidade;
                System.out.println(cidadeDeletar.toString());
            }
        }
        assertEquals("São José dos Campos", cidadeDeletar.getNome());

        cidadeService.deletarCidade(cidadeDeletar.getId(), cidadeDeletar.getSigla());

        cidades = cidadeService.getCidades();
        assertFalse(cidades.contains(cidadeDeletar));

    }

    @Test
    public void testeValidarSiglaECriarCidade() {
        Cidade cidadeCriada = new Cidade();
        cidadeCriada.setNome("Belo Horizonte");
        cidadeCriada.setSigla("BH");

        Cidade cidadeRepetida = new Cidade();
        cidadeRepetida.setNome("Belo Horizonte");
        cidadeRepetida.setSigla("BH");

        boolean CidadeCriadavalido = cidadeService.siglaValida(cidadeCriada.getSigla());
        boolean CidadeCriadaRepetida = cidadeService.siglaValida(cidadeCriada.getSigla());
        if(CidadeCriadavalido){
            cidadeService.criarCidade(cidadeCriada.getNome(), cidadeCriada.getSigla());
        }

        else if(CidadeCriadaRepetida){
            cidadeService.criarCidade(cidadeRepetida.getNome(), cidadeRepetida.getSigla());
        }

        List<Cidade> cidades = cidadeService.getCidades();
        assertEquals(5, cidades.size());
    }

    @Test
    public void testeAtualizarCidade() {
        List<Cidade> cidades = cidadeService.getCidades();
        Cidade cidadeAtualizar = cidades.get(0);
        assertEquals("São José dos Campos", cidadeAtualizar.getNome());
        cidadeAtualizar.setNome("Teste nome cidade");

        assertNotEquals("São José dos Campos", cidadeAtualizar.getNome());
        assertEquals("Teste nome cidade", cidadeAtualizar.getNome());

        cidadeService.atualizarNomeCidade(cidadeAtualizar.getId(), cidadeAtualizar.getNome());

        cidades = cidadeService.getCidades();
        for (Cidade cidade : cidades) {
            if (cidade.getId() == cidadeAtualizar.getId()) {
                assertEquals("Teste Nome Cidade", cidade.getNome());
                assertNotEquals("Teste nome cidade", cidade.getNome());
            }
        }

    }
    
}
