package com.example.dadosmeteorologicos.model;

import java.util.List;

import com.example.dadosmeteorologicos.Services.VariavelClimaticaService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariavelClimatica {

    private String tipo;

    private Double valorMinimo;
    private Double valorMaximo;
    private List<VariavelClimatica> variaveis = null;

    public VariavelClimatica(String tipo, Double valorMinimo, Double valorMaximo) {
        this.tipo = tipo;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
    }

    public VariavelClimatica() {
        VariavelClimaticaService service = new VariavelClimaticaService();
        variaveis = service.getVariaveisClimaticas();
    }

   public boolean tipoSuspeito(String tipo, double valor){
        for (VariavelClimatica variavel : variaveis) {
            if(variavel.getTipo().equals(tipo)){
                if(valor >= variavel.getValorMinimo() && valor <= variavel.getValorMaximo()){
                    return false;
                }
            }
        }
        return true;
   }

   public void setVariaveisClimaticas(List<VariavelClimatica> variaveis){
        VariavelClimaticaService service = new VariavelClimaticaService();
        service.setVariaveisClimaticas(variaveis);
   }
}



