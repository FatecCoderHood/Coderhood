package com.example.dadosmeteorologicos.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;

public class BoxPlotController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dataFinal;

    @FXML
    private DatePicker dataInicial;

    @FXML
    private MenuButton menuButtonEstação;

    @FXML
    void initialize() {
        assert dataFinal != null : "fx:id=\"dataFinal\" was not injected: check your FXML file 'BoxPlot.fxml'.";
        assert dataInicial != null : "fx:id=\"dataInicial\" was not injected: check your FXML file 'BoxPlot.fxml'.";
        assert menuButtonEstação != null : "fx:id=\"menuButtonEstação\" was not injected: check your FXML file 'BoxPlot.fxml'.";

    }

}

