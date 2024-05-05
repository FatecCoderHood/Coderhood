package com.example.dadosmeteorologicos.model;

import java.util.List;

import com.example.dadosmeteorologicos.Services.VariavelClimaticaService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class VariavelClimatica {

    private String tipo;
    private Double valorMinimo;
    private Double valorMaximo;
    private static VariavelClimaticaService service = new VariavelClimaticaService();
    private List<VariavelClimatica> variaveisClimaticas;

    public VariavelClimatica(String tipo, Double valorMinimo, Double valorMaximo) {
        this.tipo = tipo;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
    }

    public VariavelClimatica() {
        this.variaveisClimaticas = service.getVariaveisClimaticas();
    }

   public boolean tipoSuspeito(String tipo, Double valor){
    if (variaveisClimaticas == null) {
        variaveisClimaticas = getVariaveisClimaticas();
    }
        for (VariavelClimatica variavel : variaveisClimaticas) {
            if(variavel.getTipo().equals(tipo)){
                if (valor == null) return false;
                if (valor >= variavel.getValorMinimo() && valor <= variavel.getValorMaximo()){
                    return false;
                }
            }
        }
        return true;
   }

   public List<VariavelClimatica> getVariaveisClimaticas(){
    System.out.println("VariavelClimatica getVariaveisClimaticas");
        return service.getVariaveisClimaticas();
    }

    public void setVariaveisClimaticas(List<VariavelClimatica> variaveis){
        service.setVariaveisClimaticas(variaveis);
    }

    @Override
    public String toString() {
        return "VariavelClimatica{" +
                "tipo='" + tipo + '\'' +
                ", valorMinimo=" + valorMinimo +
                ", valorMaximo=" + valorMaximo +
                '}';
    }
}



