package com.example.dadosmeteorologicos.controller;

import java.util.List;

import com.example.dadosmeteorologicos.Services.EstacaoService;
import com.example.dadosmeteorologicos.model.Estacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class EstacaoController {

    @FXML
    private Button buscaEstacao;
    @FXML
    private TextArea estacoes;

    @FXML
    public void initialize() {
       
        System.out.println("Iniciado estacao");
    }

    @FXML
    public void buscaEstacao(ActionEvent event) {
        System.out.println("Buscando estacao");
        EstacaoService estacaoService = new EstacaoService();
        List<Estacao> listaEstacao = estacaoService.buscaEstacao();

        for (Estacao estacao : listaEstacao) {
            estacoes.appendText(estacao.toString() + "\n");
        }
    }


}
