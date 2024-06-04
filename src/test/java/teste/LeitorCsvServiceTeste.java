package teste;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.dadosmeteorologicos.Services.LeitorCsvService;
import com.example.dadosmeteorologicos.db.LeitorCsvSQL;
import com.example.dadosmeteorologicos.model.Registro;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class LeitorCsvServiceTeste {

    private LeitorCsvService leitorCsvService;
    private LeitorCsvSQL leitorCsvSQLMock;

    @Before
    public void setUp() {
        leitorCsvSQLMock = mock(LeitorCsvSQL.class);
        leitorCsvService = new LeitorCsvService(leitorCsvSQLMock);
    }

    @After
    public void tearDown() {
        leitorCsvService = null; 
    }

    @Test
    public void testSalvarRegistro() {
        // Criando alguns registros fictícios para teste
        List<Registro> registros = new ArrayList<>();
        registros.add(new Registro("Temperatura", 25, "SP", false));
        registros.add(new Registro("Umidade", 60, "RJ", true));
    
        // Config mock
        when(leitorCsvSQLMock.salvarRegistro(registros)).thenReturn(new int[] {0, 0});
    
        int[] resultado = leitorCsvService.salvarRegistro(registros);
    
        // Verifica se o tamanho do array retornado é igual a 2
        assertEquals(2, resultado.length);
        // Verifica se o primeiro elemento do array é igual a 0 (registros salvos)
        assertEquals(0, resultado[0]);
        // Verifica se o segundo elemento do array é igual a 0 (registros duplicados)
        assertEquals(0, resultado[1]);
    }

    // Adicionar mais testes
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