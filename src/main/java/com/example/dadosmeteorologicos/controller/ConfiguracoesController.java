package com.example.dadosmeteorologicos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import com.example.dadosmeteorologicos.model.VariavelClimatica;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class ConfiguracoesController {
    @FXML
    private TextField temperaturaMin;

    @FXML
    private TextField temperaturaMax;

    @FXML
    private TextField umidadeMin;

    @FXML
    private TextField umidadeMax;

    @FXML
    private TextField velocidadeVentoMin;

    @FXML
    private TextField velocidadeVentoMax;

    @FXML
    private TextField direcaoVentoMin;

    @FXML
    private TextField direcaoVentoMax;

    @FXML
    private TextField chuvaMin;

    @FXML
    private TextField chuvaMax;

    @FXML
    private TextField medidaTemperatura;

    @FXML
    private TextField medidaUmidade;

    @FXML
    private TextField medidaVelocVento;

    @FXML
    private TextField medidaDirVento;

    @FXML
    private TextField medidaChuva;

    @FXML
    private TextField descricaoTemperatura;

    @FXML
    private TextField descricaoUmidade;

    @FXML
    private TextField descricaoVelocVento;

    @FXML
    private TextField descricaoDirVento;

    @FXML
    private TextField descricaoChuva;

    @FXML
    private TextField formulaTemperatura;

    @FXML
    private TextField formulaUmidade;

    @FXML
    private TextField formulaVelocVento;

    @FXML
    private TextField formulaDirVento;

    @FXML
    private TextField formulaChuva;
    
    @FXML
    private Button btSalvarFaixaSuspeito;
    
    @FXML
    public void initialize() {
        System.out.println("Iniciando ConfiguracoesController");
        VariavelClimatica variavelClimatica = new VariavelClimatica();
        List<VariavelClimatica> variaveis = variavelClimatica.getVariaveisClimaticas();
        iniciarCampos(variaveis);
    }

    public void iniciarCampos(List<VariavelClimatica> variaveis){
        for (VariavelClimatica variavel : variaveis){
            switch (variavel.getTipo()) {
                case "temperaturaMedia":
                    temperaturaMin.setText(String.valueOf(variavel.getValorMinimo()));
                    temperaturaMax.setText(String.valueOf(variavel.getValorMaximo()));
                    medidaTemperatura.setText(variavel.getUnidadeMedida());
                    descricaoTemperatura.setText(variavel.getDescricaoConversao());
                    formulaTemperatura.setText(variavel.getFormulaConversao());
                    break;
                case "umidadeMedia":
                    umidadeMin.setText(String.valueOf(variavel.getValorMinimo()));
                    umidadeMax.setText(String.valueOf(variavel.getValorMaximo()));
                    medidaUmidade.setText(variavel.getUnidadeMedida());
                    descricaoUmidade.setText(variavel.getDescricaoConversao());
                    formulaUmidade.setText(variavel.getFormulaConversao());
                    break;
                case "velVento":
                    velocidadeVentoMin.setText(String.valueOf(variavel.getValorMinimo()));
                    velocidadeVentoMax.setText(String.valueOf(variavel.getValorMaximo()));
                    medidaVelocVento.setText(variavel.getUnidadeMedida());
                    descricaoVelocVento.setText(variavel.getDescricaoConversao());
                    formulaVelocVento.setText(variavel.getFormulaConversao());
                    break;
                case "dirVento":
                    direcaoVentoMin.setText(String.valueOf(variavel.getValorMinimo()));
                    direcaoVentoMax.setText(String.valueOf(variavel.getValorMaximo()));
                    medidaDirVento.setText(variavel.getUnidadeMedida());
                    descricaoDirVento.setText(variavel.getDescricaoConversao());
                    formulaDirVento.setText(variavel.getFormulaConversao());
                    break;
                case "chuva":
                    chuvaMin.setText(String.valueOf(variavel.getValorMinimo()));
                    chuvaMax.setText(String.valueOf(variavel.getValorMaximo()));
                    medidaChuva.setText(variavel.getUnidadeMedida());
                    descricaoChuva.setText(variavel.getDescricaoConversao());
                    formulaChuva.setText(variavel.getFormulaConversao());
                    break;
            }
        }
    }

    @FXML
    public void SalvarFaixaSuspeito(ActionEvent event) {
        List<VariavelClimatica> variaveis = new ArrayList<>();
        try {
            assegurarNumerico(temperaturaMin);
            assegurarNumerico(temperaturaMax);
            assegurarNumerico(umidadeMin);
            assegurarNumerico(umidadeMax);
            assegurarNumerico(velocidadeVentoMin);
            assegurarNumerico(velocidadeVentoMax);
            assegurarNumerico(direcaoVentoMin);
            assegurarNumerico(direcaoVentoMax);
            assegurarNumerico(chuvaMin);
            assegurarNumerico(chuvaMax);

            variaveis.add(new VariavelClimatica("temperaturaMedia", Double.parseDouble(temperaturaMin.getText()), 
                Double.parseDouble(temperaturaMax.getText()), medidaTemperatura.getText(), descricaoTemperatura.getText(), formulaTemperatura.getText()));

            variaveis.add(new VariavelClimatica("umidadeMedia", Double.parseDouble(umidadeMin.getText()), 
                Double.parseDouble(umidadeMax.getText()), medidaUmidade.getText(), descricaoUmidade.getText(), formulaUmidade.getText()));   

            variaveis.add(new VariavelClimatica("velVento", Double.parseDouble(velocidadeVentoMin.getText()), 
                Double.parseDouble(velocidadeVentoMax.getText()), medidaVelocVento.getText(), descricaoVelocVento.getText(), formulaVelocVento.getText()));   
            variaveis.add(new VariavelClimatica("dirVento", Double.parseDouble(direcaoVentoMin.getText()), 
                Double.parseDouble(direcaoVentoMax.getText()), medidaDirVento.getText(), descricaoDirVento.getText(), formulaDirVento.getText())); 
            variaveis.add(new VariavelClimatica("chuva", Double.parseDouble(chuvaMin.getText()), 
                Double.parseDouble(chuvaMax.getText()), medidaChuva.getText(), descricaoChuva.getText(), formulaChuva.getText()));   
          
            new VariavelClimatica().setVariaveisClimaticas(variaveis);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Valores Salvos");
            alert.setContentText("Faixa de valores suspeitos salva com sucesso");
            alert.showAndWait();
            
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Por favor, insira apenas números");
            alert.showAndWait();
        }
    }

    private void assegurarNumerico(TextField campo) {
        UnaryOperator<TextFormatter.Change> filtro = change -> {
            String texto = change.getText();
            if (texto.matches("-?\\d*(\\.\\d*)?")) {
                return change;
            }
            return null;
        };
        campo.setTextFormatter(new TextFormatter<>(filtro));
    }
}
