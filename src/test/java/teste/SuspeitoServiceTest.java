package teste;



import com.example.dadosmeteorologicos.Services.SuspeitoService;

import static org.junit.Assert.*;

import org.junit.*;

public class SuspeitoServiceTest {

    private SuspeitoService suspeitoService;

    @BeforeEach
    public void setUp() {
        suspeitoService = new SuspeitoService();
    }

    @Test
    public void testBuscaRegistrosSuspeitos() {
        List<Registro> registros = suspeitoService.buscaRegistrosSuspeitos();
        assertNotNull(registros, "The list of suspicious records should not be null");
        // Add more assertions based on your expectations
    }

    @Test
    public void testDeletarRegistroSuspeito() {
        // You need to know the id of a suspicious record that exists in your database
        int id = 1;
        suspeitoService.deletarRegistroSuspeito(id);
        // Add assertions to verify that the record was deleted
    }

    @Test
    public void testEditarRegistroSuspeito() {
        // You need to know the id of a suspicious record that exists in your database
        int id = 1;
        double valor = 10.0;
        suspeitoService.editarRegistroSuspeito(id, valor);
        // Add assertions to verify that the record was updated
    }
}
