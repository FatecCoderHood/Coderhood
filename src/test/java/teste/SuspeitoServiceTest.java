package teste;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.example.dadosmeteorologicos.Services.SuspeitoService;
import com.example.dadosmeteorologicos.model.Registro;

public class SuspeitoServiceTest {
    private static SuspeitoService suspeitoService;
    private static IniciaBancoTeste bancoTeste;

    @BeforeAll
    public static void setup() throws SQLException {
        bancoTeste = new IniciaBancoTeste();
        bancoTeste.reiniciarBanco();
        bancoTeste.iniciarBanco();
        bancoTeste.popularBancoTeste();
        Connection conn = bancoTeste.conectarBanco();
        suspeitoService = new SuspeitoService(conn);
    }

    @AfterAll
    public static void tearDown() {
        bancoTeste.fecharConexao();
        bancoTeste.reiniciarBanco();
    }

    @BeforeEach
    public void reset(){
        bancoTeste.reiniciarBanco();
        bancoTeste.iniciarBanco();
        bancoTeste.popularBancoTeste();
    }


    @Test
    public void testeBuscaRegistrosSuspeitos() {
        List<Registro> registrosSuspeitos = suspeitoService.buscaRegistrosSuspeitos();
        assertEquals(5, suspeitoService.buscaRegistrosSuspeitos().size());

        for (Registro registro : registrosSuspeitos) {
            switch (registro.getTipo()) {
                case "temperaturaMedia":
                    assertEquals(70.0, registro.getValor());
                    break;
                case "umidadeMedia":
                    assertEquals(110.0, registro.getValor());
                    break;
                case "velVento":
                    assertEquals(500.0, registro.getValor());
                    break;
                case "dirVento":
                    assertEquals(400.0, registro.getValor());
                    break;
                case "chuva":
                    assertEquals(900.0, registro.getValor());
                    break;            
                default:
                    break;
            }
        }
    }

    @Test
    public void testeDeletarRegistroSuspeito() {
        List<Registro> registrosSuspeitos = suspeitoService.buscaRegistrosSuspeitos();
        for (Registro registro : registrosSuspeitos) {
            if(registro.getTipo().equals("temperaturaMedia")) {
                suspeitoService.deletarRegistroSuspeito(registro.getId());
            }
        }
        assertEquals(4, suspeitoService.buscaRegistrosSuspeitos().size());

        List<Registro> registrosSuspeitosAtualizado = suspeitoService.buscaRegistrosSuspeitos();
        for (Registro registro : registrosSuspeitosAtualizado) {
            if (registro.getTipo().equals("temperaturaMedia")) {
                fail("Registro de temperaturaMedia não foi deletado");
            }
        }
    }

    @Test
    public void testeEditarRegistroSuspeito() {
        List<Registro> registrosSuspeitos = suspeitoService.buscaRegistrosSuspeitos();
        for (Registro registro : registrosSuspeitos) {
            if(registro.getTipo().equals("temperaturaMedia")) {
                suspeitoService.editarRegistroSuspeito(registro.getId(), 30.0);
            }
        }

        List<Registro> registrosSuspeitosAtualizado = suspeitoService.buscaRegistrosSuspeitos();
        for (Registro registro : registrosSuspeitosAtualizado) {
            if (registro.getTipo().equals("temperaturaMedia")) {
                fail("Registro de temperaturaMedia não foi alterado");
            }
        }
        assertEquals(4, suspeitoService.buscaRegistrosSuspeitos().size());
    }

}
