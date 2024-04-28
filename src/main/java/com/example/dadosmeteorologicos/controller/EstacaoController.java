package com.example.dadosmeteorologicos.controller;

import java.util.List;

import com.example.dadosmeteorologicos.Services.EstacaoService;
import com.example.dadosmeteorologicos.model.Estacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class EstacaoController {

    @FXML
    private Button buscaEstacao;

    @FXML
    private TableView<Estacao> estacoes;

    @FXML
    private TableColumn<Estacao, Void> ColumnButton;

    @FXML
    private TableColumn<Estacao, String> ColumnSigla;

    @FXML
    private TableColumn<Estacao, String> ColumnEstacao;

    // @FXML
    // private TableColumn<Estacao, String> ColumnCidade;

    @FXML
    public void initialize() {
        // ...
        System.out.println("Iniciado estacao");


        ColumnEstacao.setCellValueFactory(new PropertyValueFactory<>("numero"));
        ColumnSigla.setCellValueFactory(new PropertyValueFactory<>("siglaCidade"));
        // ColumnCidade.setCellValueFactory(new PropertyValueFactory<>("nome"));


        ColumnButton.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Deletar Estação");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    deleteButton.setOnAction(event -> {
                        Estacao estacao = getTableView().getItems().get(getIndex());
                        // Delete a estação
                        //estacaoService.deletaEstacao(estacaoModel);
                        // Remova a estação da tabela
                        estacoes.getItems().remove(estacao);
                    });

                    setGraphic(deleteButton);
                }
            }
        });
    }
    @FXML
    public void buscaEstacao(ActionEvent event) {
        System.out.println("Buscando estacao");
        EstacaoService estacaoService = new EstacaoService();
        List<Estacao> listaEstacao = estacaoService.buscaEstacao();
        System.out.println(estacoes);

        for (Estacao estacao : listaEstacao) {
            estacoes.getItems().add(estacao);
        }
    }


}
