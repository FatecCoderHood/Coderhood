package teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.dadosmeteorologicos.Services.BoxPlotService;
import com.example.dadosmeteorologicos.model.BoxPlot;
import com.example.dadosmeteorologicos.model.Estacao;
import com.example.dadosmeteorologicos.model.ValoresBoxPlot;

import static org.junit.jupiter.api.Assertions.*;

public class BoxPlotServiceTest {
    

    private static BoxPlotService boxPlotService;
    private static IniciaBancoTeste bancoTeste;

    @BeforeAll
    public static void setup() throws SQLException {
        bancoTeste = new IniciaBancoTeste();
        bancoTeste.reiniciarBanco();
        bancoTeste.iniciarBanco();
        bancoTeste.popularBancoTeste();
        Connection conn = bancoTeste.conectarBanco();
        boxPlotService = new BoxPlotService(conn);
    }

    @AfterAll
    public static void tearDown() {
        bancoTeste.fecharConexao();
        bancoTeste.reiniciarBanco();
    }

    @Test
    public void testeGetEstacoesDoBancoDeDados() {
        List<Estacao> estacoes = boxPlotService.getEstacoesDoBancoDeDados();
        assertEquals(6, estacoes.size());
    }

    @Test
    public void testeGetBoxPlotDados() {
        LocalDate dataSelecionada = LocalDate.of(2021, 01, 01);
        String numeroEstacao = "83726";
        String nomeCidade = "São Carlos";
        String siglaCidade = "SC";
        BoxPlot boxPlotSelecionado = new BoxPlot(dataSelecionada, numeroEstacao, nomeCidade, siglaCidade);
        Map <String, List<String>> resultadoDaBusca = boxPlotService.getBoxPlotDados(boxPlotSelecionado.getNumeroEstacao(), boxPlotSelecionado.getDataSelecionada());

        List<ValoresBoxPlot> dadosBoxPlot = resolvebaguncaDoJulio(resultadoDaBusca);
    
        System.out.println(dadosBoxPlot.toString());

        for (ValoresBoxPlot valores : dadosBoxPlot){
            String tipo = valores.getTipo();
            String min = valores.getMin();
            String q1 = valores.getQ1();
            String mediana = valores.getMediana();
            String q3 = valores.getQ3();
            String max = valores.getMax();

            switch (tipo) {
                case "Temperatura": 
                    assertEquals("20,00", min);
                    assertEquals("38,00", q1);
                    assertEquals("38,00", mediana);
                    assertEquals("40,00", q3);
                    assertEquals("40,00", max);
                    break;
                
                case "Umidade": 
                    assertEquals("50,00", min);
                    assertEquals("68,00", q1);
                    assertEquals("68,00", mediana);
                    assertEquals("70,00", q3);
                    assertEquals("70,00", max);
                    break;

                case "Velocidade do Vento": 
                    assertEquals("10,00", min);
                    assertEquals("10,00", q1);
                    assertEquals("10,00", mediana);
                    assertEquals("11,00", q3);
                    assertEquals("11,00", max);
                    break;

                case "Direção do Vento": 
                    assertEquals("120,00", min);
                    assertEquals("121,00", q1);
                    assertEquals("121,00", mediana);
                    assertEquals("180,00", q3);
                    assertEquals("180,00", max);
                    break;

                case "Chuva": 
                    assertEquals("0,00", min);
                    assertEquals("3,00", q1);
                    assertEquals("3,00", mediana);
                    assertEquals("5,00", q3);
                    assertEquals("5,00", max);
                    break;


                default:
                    break;
            }
        }
    }

    private List<ValoresBoxPlot> resolvebaguncaDoJulio( Map <String, List<String>> resultadoDaBusca){
        List<Double> temperatura = convertToDoubleList(resultadoDaBusca.get("temperaturaMedia"));
        List<Double> umidade = convertToDoubleList(resultadoDaBusca.get("umidadeMedia"));
        List<Double> velVento = convertToDoubleList(resultadoDaBusca.get("velVento"));
        List<Double> dirVento = convertToDoubleList(resultadoDaBusca.get("dirVento"));
        List<Double> chuva = convertToDoubleList(resultadoDaBusca.get("chuva"));

        ValoresBoxPlot temperaturaBoxPlot = new ValoresBoxPlot("Temperatura", temperatura.toArray(new Double[0]));
        ValoresBoxPlot umidadeBoxPlot = new ValoresBoxPlot("Umidade", umidade.toArray(new Double[0]));
        ValoresBoxPlot velVentoBoxPlot = new ValoresBoxPlot("Velocidade do Vento", velVento.toArray(new Double[0]));
        ValoresBoxPlot dirVentoBoxPlot = new ValoresBoxPlot("Direção do Vento", dirVento.toArray(new Double[0]));
        ValoresBoxPlot chuvaBoxPlot = new ValoresBoxPlot("Chuva", chuva.toArray(new Double[0]));

        List<ValoresBoxPlot> dadosBoxPlot;

         dadosBoxPlot = Arrays.asList(
                temperaturaBoxPlot, umidadeBoxPlot, velVentoBoxPlot, dirVentoBoxPlot, chuvaBoxPlot);

        return dadosBoxPlot;
    }

    
    private List<Double> convertToDoubleList(List<String> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

}
