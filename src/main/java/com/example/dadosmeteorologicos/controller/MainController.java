package com.example.dadosmeteorologicos.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainController {

    @FXML
    private TabPane abas;

    @FXML
    private Tab tabLeitorCsv;

    @FXML
    private Tab tabValorMedio;

    @FXML
    public void initialize() {
        abas.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            
            if (newTab == tabLeitorCsv) {
                try {
                    Pane leitorCsvPane = FXMLLoader.load(getClass().getResource("/com/example/dadosmeteorologicos/view/LeitorCsv.fxml"));
                    tabLeitorCsv.setContent(leitorCsvPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (newTab == tabValorMedio) {
                try {
                    Pane valorMedioPane = FXMLLoader.load(getClass().getResource("/com/example/dadosmeteorologicos/view/ValorMedio.fxml"));
                    tabValorMedio.setContent(valorMedioPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}