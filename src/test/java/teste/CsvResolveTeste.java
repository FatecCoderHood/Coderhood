package teste;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.dadosmeteorologicos.Services.CSVResolve;
import com.example.dadosmeteorologicos.model.Registro;
import com.example.dadosmeteorologicos.model.RegistroSituacao;


public class CsvResolveTeste {
    private static CSVResolve csvResolve;

    @BeforeAll
    public static void setUp() {
    // C:/Users/mateu/Desktop/Backup/Estudos/Fatec/Coderhood/src/test/java/teste/resources/A777_SP.csv
        String enderecoCSV = "src/test/java/teste/resources/A777_SP.csv";
        csvResolve = new CSVResolve(enderecoCSV);
    }

    @Test
    public void construtorCsvResolve() {
        String caminhoCSV = csvResolve.getCaminhoCSV();
        assertEquals("src/test/java/teste/resources/A777_SP.csv", caminhoCSV);
    }

    @Test
    public void validarCSV(){
        try {
            System.out.println("Teste de validação do CSV");
            boolean validacao = csvResolve.validarCSV();
            assertTrue(validacao);
            String [] cabecalhoCsv = csvResolve.getCabecalhoCSV();
            String[] cabecalhoEsperado = {
                "Data",
                "Hora (UTC)",
                "Temp. Ins. (C)",
                "Temp. Max. (C)",
                "Temp. Min. (C)",
                "Umi. Ins. (%)",
                "Umi. Max. (%)",
                "Umi. Min. (%)",
                "Pto Orvalho Ins. (C)",
                "Pto Orvalho Max. (C)",
                "Pto Orvalho Min. (C)",
                "Pressao Ins. (hPa)",
                "Pressao Max. (hPa)",
                "Pressao Min. (hPa)",
                "Vel. Vento (m/s)",
                "Dir. Vento (m/s)",
                "Raj. Vento (m/s)",
                "Radiacao (KJ/m²)",
                "Chuva (mm)"
            };

            for (int i = 0; i < cabecalhoEsperado.length; i++){
                assertEquals(cabecalhoEsperado[i], cabecalhoCsv[i]);
            }

            assertEquals("777", csvResolve.getCodigoEstacao());
            assertEquals("SP", csvResolve.getSiglaCidade());
            assertTrue(csvResolve.isAutomatico());
            assertFalse(csvResolve.isNomeInvalido());

            List<String[]> csvPadronizado = csvResolve.getCsvPadronizado();

            List<String[]> csvPadronizadoEsperado = Arrays.asList(
                new String[]{"Data", "Hora (UTC)", "Temp. Ins. (C)", "Temp. Max. (C)", "Temp. Min. (C)", "Umi. Ins. (%)", "Umi. Max. (%)", "Umi. Min. (%)", "Pto Orvalho Ins. (C)", "Pto Orvalho Max. (C)", "Pto Orvalho Min. (C)", "Pressao Ins. (hPa)", "Pressao Max. (hPa)", "Pressao Min. (hPa)", "Vel. Vento (m/s)", "Dir. Vento (m/s)", "Raj. Vento (m/s)", "Radiacao (KJ/m²)", "Chuva (mm)"},
                new String[]{"01/11/2023", "0000", "21.1", "20.5", "21.0", "93.0", "93.0", "92.0", "19.9", "20.2", "19.9", "946.5", "946.8", "946.5", "2.7", "46.0", "4.4", "", "0.2"},
                new String[]{"01/11/2023", "0100", "20.9", "21.1", "20.9", "93.0", "94.0", "93.0", "19.7", "20.1", "19.7", "946.2", "946.5", "946.2", "2.6", "48.0", "4.5", "", "0.2"},
                new String[]{"01/11/2023", "0200", "20.8", "20.9", "20.8", "93.0", "93.0", "93.0", "19.6", "19.8", "19.5", "945.8", "946.2", "945.8", "2.0", "29.0", "3.6", "", "0.0"},
                new String[]{"01/11/2023", "0300", "20.6", "20.8", "20.6", "93.0", "93.0", "93.0", "19.4", "19.6", "19.4", "945.5", "945.8", "945.5", "1.1", "57.0", "3.0", "", "0.0"}
            );

            for (int i = 0; i < csvPadronizadoEsperado.size(); i++) {
                String[] esperado = csvPadronizadoEsperado.get(i);
                String[] atual = csvPadronizado.get(i);
                assertEquals(esperado.length, atual.length);
                for (int j = 0; j < esperado.length; j++) {
                    assertEquals(esperado[j], atual[j]);
                }
            }            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    @Test
    public void criarRegistro(){
        List<Registro> resultadoCriacao = csvResolve.criarRegistro();
        List<Registro> registroMock = registroMock();
        System.out.println(resultadoCriacao);
        for (Registro registro : resultadoCriacao){

        }
    }

    public List<Registro> registroMock(){
        List<Registro> registros = Arrays.asList(
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(0, 0), "777", "SP", "temperaturaMedia", 20.75, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(0, 0), "777", "SP", "umidadeMedia", 92.5, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(0, 0), "777", "SP", "velVento", 2.7, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(0, 0), "777", "SP", "dirVento", 46.0, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(0, 0), "777", "SP", "chuva", 0.2, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(1, 0), "777", "SP", "temperaturaMedia", 21.0, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(1, 0), "777", "SP", "umidadeMedia", 93.5, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(1, 0), "777", "SP", "velVento", 2.6, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(1, 0), "777", "SP", "dirVento", 48.0, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(1, 0), "777", "SP", "chuva", 0.2, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(2, 0), "777", "SP", "temperaturaMedia", 20.85, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(2, 0), "777", "SP", "umidadeMedia", 93.0, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(2, 0), "777", "SP", "velVento", 2.0, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(2, 0), "777", "SP", "dirVento", 29.0, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(2, 0), "777", "SP", "chuva", 0.0, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(3, 0), "777", "SP", "temperaturaMedia", 20.7, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(3, 0), "777", "SP", "umidadeMedia", 93.0, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(3, 0), "777", "SP", "velVento", 1.1, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(3, 0), "777", "SP", "dirVento", 57.0, false),
            new Registro(0, LocalDate.of(2023, 11, 1), LocalTime.of(3, 0), "777", "SP", "chuva", 0.0, false)
        );
        return registros;
    }
}
