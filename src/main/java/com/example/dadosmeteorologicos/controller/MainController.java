package com.example.dadosmeteorologicos.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView imageViewFundo;

    @FXML
    private ImageView imageViewZeus;

    @FXML
    private ImageView imageViewLogo;

    @FXML
    private Tab tabEstacao;

    @FXML
    private Tab tabCidade;
    
    @FXML
    private Tab tabConf;

    @FXML
    public void initialize() {
        
        Image imageFundo = new Image(getClass().getResourceAsStream("/com/example/dadosmeteorologicos/imagens/fundo.jpg"));
        imageViewFundo.setImage(imageFundo);
        
        Image imageLogoCoderHood = new Image(getClass().getResourceAsStream("/com/example/dadosmeteorologicos/imagens/logo.png"));
        imageViewLogo.setImage(imageLogoCoderHood);
        
        Image imageLogoZeus = new Image(getClass().getResourceAsStream("/com/example/dadosmeteorologicos/imagens/Zeus.png"));
        imageViewZeus.setImage(imageLogoZeus);

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
            } else if (newTab == tabEstacao){
                try {
                    Pane estacaoPane = FXMLLoader.load(getClass().getResource("/com/example/dadosmeteorologicos/view/Estacao.fxml"));
                    tabEstacao.setContent(estacaoPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(newTab == tabConf){
                try {
                    Pane confPane = FXMLLoader.load(getClass().getResource("/com/example/dadosmeteorologicos/view/Configuracoes.fxml"));
                    tabConf.setContent(confPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(newTab == tabCidade){
                try {
                    Pane cidadePane = FXMLLoader.load(getClass().getResource("/com/example/dadosmeteorologicos/view/Cidade.fxml"));
                    tabCidade.setContent(cidadePane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        });
    }
}