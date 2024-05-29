package teste;

import static org.junit.Assert.assertFalse;

import org.junit.*;

import com.example.dadosmeteorologicos.Services.VariavelClimaticaService;

public class VariavelClimaticaTeste {
    @Test
    public void teste(){
        VariavelClimaticaService variavelClimaticaService = new VariavelClimaticaService();
        variavelClimaticaService.celulasDaTabelaEstaoNulas();
        assertFalse(variavelClimaticaService.celulasDaTabelaEstaoNulas());
    }
}
