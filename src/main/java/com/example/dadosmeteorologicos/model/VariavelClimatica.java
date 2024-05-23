package com.example.dadosmeteorologicos.model;

import java.util.List;

import com.example.dadosmeteorologicos.Services.VariavelClimaticaService;

import lombok.Data;

@Data
public class VariavelClimatica {
    private String tipo;
    private Double valorMinimo;
    private Double valorMaximo;
    private String unidadeMedida;
    private String descricaoConversao;
    private String formulaConversao;
    private static VariavelClimaticaService service = new VariavelClimaticaService();
    private List<VariavelClimatica> variaveisClimaticas;


    public VariavelClimatica(String tipo, Double valorMinimo, Double valorMaximo) {
        this.tipo = tipo;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
    }

    public VariavelClimatica(String tipo, Double valorMinimo, Double valorMaximo, String unidadeMedida, String descricaoConversao, String formulaConversao) {
        this.tipo = tipo;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
        this.unidadeMedida = unidadeMedida;
        this.descricaoConversao = descricaoConversao;
        this.formulaConversao = formulaConversao;
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
                ", unidadeMedida='" + unidadeMedida + '\'' +
                ", descricaoConversao='" + descricaoConversao + '\'' +
                ", formulaConversao='" + formulaConversao + '\'' +
                '}';
    }
}



