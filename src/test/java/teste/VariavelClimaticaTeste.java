package teste;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.dadosmeteorologicos.Services.VariavelClimaticaService;
import com.example.dadosmeteorologicos.model.VariavelClimatica;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class VariavelClimaticaTeste {
    private VariavelClimaticaService variavelClimaticaService;
    private IniciaBancoTeste bancoTeste;


    @Before
    public void setup() throws SQLException {
        bancoTeste = new IniciaBancoTeste();
        bancoTeste.iniciarBanco();
        variavelClimaticaService = new VariavelClimaticaService(bancoTeste.conectarBanco());
    }

    @Test
    public void testGetVariavelClimatica() {
        List<VariavelClimatica> variaveisClimaticas = variavelClimaticaService.getVariaveisClimaticas();
        List<String> tipos = Arrays.asList("temperaturaMedia", "umidadeMedia", "velVento", "dirVento", "chuva");
        for (VariavelClimatica variavel : variaveisClimaticas) {
            assertTrue(tipos.contains(variavel.getTipo()));
        }
    }

    @Test
    public void testSetVariaveisClimaticas(){
        List<VariavelClimatica> variaveis = Arrays.asList(
            new VariavelClimatica("temperaturaMedia", 20.0, 30.0, "C", "Temperatura média", "Celsius"),
            new VariavelClimatica("umidadeMedia", 0.0, 100.0, "%", "Umidade média", "Porcentagem"),
            new VariavelClimatica("velVento", 0.0, 100.0, "km/h", "Velocidade do vento", "Kilometros por hora"),
            new VariavelClimatica("dirVento", 0.0, 360.0, "°", "Direção do vento", "Graus"),
            new VariavelClimatica("chuva", 0.0, 100.0, "mm", "Chuva", "Milimetros")
        );
        variavelClimaticaService.setVariaveisClimaticas(variaveis);
        List<VariavelClimatica> variaveisClimaticas = variavelClimaticaService.getVariaveisClimaticas();
        //temperaturaMedia
        assertTrue(variaveisClimaticas.get(0).getValorMinimo().equals(20.0));
        //velVento
        assertTrue(variaveisClimaticas.get(2).getValorMaximo().equals(100.0));
        //Chuva
        assertTrue(variaveisClimaticas.get(4).getUnidadeMedida().equals("mm"));

    }

    @Test
    public void testeCelulasDaTabelaEstaoNulas() {
        assertFalse("As células da tabela não deveriam ser nulas", variavelClimaticaService.celulasDaTabelaEstaoNulas());
    }

    @After
    public void tearDown() {
        IniciaBancoTeste bancoTeste = new IniciaBancoTeste();
        bancoTeste.limparBanco();
    }
}
