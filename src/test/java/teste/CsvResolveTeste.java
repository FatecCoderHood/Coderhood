package teste;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.dadosmeteorologicos.Services.CSVResolve;


public class CsvResolveTeste {
    private static CSVResolve csvResolve;

    @BeforeEach
    public void setUp() {
        String enderecoCSV = "src/test/java/teste/resources/A1701_SP.csv";
        csvResolve = new CSVResolve(enderecoCSV);
    }

    @Test
    public void construtorCsvResolve() {
        String caminhoCSV = csvResolve.getCaminhoCSV();
        assertEquals("src/test/java/teste/resources/A1701_SP.csv", caminhoCSV);
    }

    @Test
    public void validarCSV(){
        try {
            System.out.println("Teste de validação do CSV");
            boolean validacao = csvResolve.validarCSV();
            assertTrue(validacao);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

}
