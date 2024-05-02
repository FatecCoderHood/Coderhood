package com.example.dadosmeteorologicos.controller;

import java.util.List;
import java.util.Optional;

import com.example.dadosmeteorologicos.Services.EstacaoService;
import com.example.dadosmeteorologicos.model.Estacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class EstacaoController {

    // Declaração dos componentes da interface gráfica
    @FXML
    private Button buscaEstacao;

    @FXML
    private TableView<Estacao> estacoes; // Tabela para exibir as estações

    @FXML
    private TableColumn<Estacao, Void> ColumnButton; // Coluna para os botões de ação

    @FXML
    private TableColumn<Estacao, String> ColumnSigla; // Coluna para a sigla da cidade

    @FXML
    private TableColumn<Estacao, String> ColumnEstacao; // Coluna para o número da estação

    private String estacaoInserida;
    private String siglaInserida;

    @FXML
    void adicionarNovaEstacao(ActionEvent event) {
        if (!criarDialogo()){
            return;
        }

        

        if(estacaoInserida.trim().isEmpty() || siglaInserida.trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.showAndWait();
            return;
        }

        if (estacaoService.siglaValida(siglaInserida)){
            estacaoService.criarEstacao(estacaoInserida, siglaInserida);
            estacoes.getItems().clear();
            List<Estacao> listaEstacao = estacaoService.buscaEstacao();
            criarTabela(listaEstacao);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Estação já inserida");
            alert.showAndWait();
            return;
        }

    }


    public Boolean criarDialogo(){
        Dialog<Boolean> dialog = new Dialog<>();
        ButtonType confirmButtonType = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);

        TextField campoEstacao = new TextField();
        campoEstacao.setPrefWidth(200);
        TextField campoSigla = new TextField();
        campoSigla.setPrefWidth(200);

        GridPane grid = new GridPane();
        grid.setPrefHeight(400);
        grid.add(new Label("Numero da Estação: "), 0, 0);
        grid.add(campoEstacao, 1, 0);
        grid.add(new Label("Sigla da cidade: "), 0, 1);
        grid.add(campoSigla, 1, 1);
        dialog.getDialogPane().setContent(grid);

        // Controlando o tamanho do diálogo
        dialog.getDialogPane().setMinHeight(350);
        dialog.getDialogPane().setMinWidth(150);
        dialog.getDialogPane().setMaxHeight(350);
        dialog.getDialogPane().setMaxWidth(150);


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButtonType) {
                estacaoInserida = campoEstacao.getText();
                siglaInserida = campoSigla.getText();
                

                if (estacaoInserida.trim().isEmpty() || siglaInserida.trim().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Preencha todos os campos");
                    alert.showAndWait();
                    return false;
                }
                return true;
    }
    return false;

    });
    
    return dialog.showAndWait().isPresent();
}

    

    // Método chamado quando a classe é inicializada
    @FXML
    public void initialize() {

        System.out.println("Iniciado estacao");

        // Busca as estações
        System.out.println("Buscando estacao");
        EstacaoService estacaoService = new EstacaoService();
        List<Estacao> listaEstacao = estacaoService.buscaEstacao();
        System.out.println(estacoes);
    
        // Adiciona as estações na tabela
        for (Estacao estacao : listaEstacao) {
            estacoes.getItems().add(estacao);
        }

        // Configura as colunas da tabela
        ColumnEstacao.setCellValueFactory(new PropertyValueFactory<>("numero"));
        ColumnSigla.setCellValueFactory(new PropertyValueFactory<>("siglaCidade"));

        // Configura a coluna de botões
        ColumnButton.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Deletar Estação");
            
            // Sobrescreve o método updateItem
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                // Se a célula estiver vazia, não mostra nada
                if (empty) {
                    setGraphic(null);
                } else {
                    // Configura o botão de deletar
                    deleteButton.setOnAction(event -> {
                        // Mostra um alerta de confirmação
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmação");
                        alert.setHeaderText("Você tem certeza que deseja deletar esta estação?");

                        ButtonType buttonTypeSim = new ButtonType("Sim", ButtonData.YES);
                        ButtonType buttonTypeNao = new ButtonType("Não", ButtonData.NO);

                        alert.getButtonTypes().setAll(buttonTypeSim, buttonTypeNao);

                        Optional<ButtonType> result = alert.showAndWait();
                        // Se o usuário confirmar, deleta a estação
                        if (result.isPresent() && result.get() == buttonTypeSim){
                        Estacao estacao = getTableView().getItems().get(getIndex());
                        System.out.println(estacao.getId() + " " + estacao.getNumero() + " " + estacao.getSiglaCidade());
                        
                        estacaoService.deletarEstacao(estacao.getId(), estacao.getNumero());
                        
                        // Remove a estação da tabela
                        estacoes.getItems().remove(estacao);
                }
            });

                    // Mostra o botão na célula
                    setGraphic(deleteButton);
                }

            }
        });

    }

}

