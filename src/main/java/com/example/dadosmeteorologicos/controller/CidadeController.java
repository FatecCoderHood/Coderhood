package com.example.dadosmeteorologicos.controller;

import java.util.List;

import com.example.dadosmeteorologicos.Services.CidadeService;
import com.example.dadosmeteorologicos.model.Cidade;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CidadeController {
    @FXML
    private VBox vboxCidades;

    private static CidadeService cidadeService = new CidadeService();

    public void initialize() {
        cidadeService = new CidadeService();
        List<Cidade> cidades = cidadeService.getCidades();

        for (Cidade cidade : cidades) {
            HBox hboxCidade = new HBox();

            Label nome = new Label(cidade.getNome());
            TextField sigla = new TextField(cidade.getSigla());

            Button btnDeletar = new Button("Deletar");
            btnDeletar.setOnAction(event -> {
                System.out.println("Deletando cidade: " + cidade.getId());
                // cidadeService.deletarCidade(cidade.getId());
            });

            hboxCidade.getChildren().addAll(nome, sigla, btnDeletar);
            vboxCidades.getChildren().add(hboxCidade);
        }
    }


}
