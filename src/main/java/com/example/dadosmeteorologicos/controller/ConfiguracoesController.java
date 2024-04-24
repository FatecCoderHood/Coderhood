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
    private Button btSalvarFaixaSuspeito;
    
    @FXML
    public void initialize() {
        List<VariavelClimatica> variaveis = new VariavelClimatica().getVariaveis();
        iniciarCampos(variaveis);
    }

    public void iniciarCampos(List<VariavelClimatica> variaveis){
        for (VariavelClimatica variavel : variaveis){
            switch (variavel.getTipo()) {
                case "temperaturaMedia":
                    temperaturaMin.setText(String.valueOf(variavel.getValorMinimo()));
                    temperaturaMax.setText(String.valueOf(variavel.getValorMaximo()));
                    break;
                case "umidadeMedia":
                    umidadeMin.setText(String.valueOf(variavel.getValorMinimo()));
                    umidadeMax.setText(String.valueOf(variavel.getValorMaximo()));
                    break;
                case "velVento":
                    velocidadeVentoMin.setText(String.valueOf(variavel.getValorMinimo()));
                    velocidadeVentoMax.setText(String.valueOf(variavel.getValorMaximo()));
                    break;
                case "dirVento":
                    direcaoVentoMin.setText(String.valueOf(variavel.getValorMinimo()));
                    direcaoVentoMax.setText(String.valueOf(variavel.getValorMaximo()));
                    break;
                case "chuva":
                    chuvaMin.setText(String.valueOf(variavel.getValorMinimo()));
                    chuvaMax.setText(String.valueOf(variavel.getValorMaximo()));
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

            variaveis.add(new VariavelClimatica("temperaturaMedia", Double.parseDouble(temperaturaMin.getText()), Double.parseDouble(temperaturaMax.getText())));
            variaveis.add(new VariavelClimatica("umidadeMedia", Double.parseDouble(umidadeMin.getText()), Double.parseDouble(umidadeMax.getText())));    
            variaveis.add(new VariavelClimatica("velVento", Double.parseDouble(velocidadeVentoMin.getText()), Double.parseDouble(velocidadeVentoMax.getText())));   
            variaveis.add(new VariavelClimatica("dirVento", Double.parseDouble(direcaoVentoMin.getText()), Double.parseDouble(direcaoVentoMax.getText()))); 
            variaveis.add(new VariavelClimatica("chuva", Double.parseDouble(chuvaMin.getText()), Double.parseDouble(chuvaMax.getText())));   
          
            new VariavelClimatica().setVariaveis(variaveis);
            
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
