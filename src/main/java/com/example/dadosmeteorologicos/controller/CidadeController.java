// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.dadosmeteorologicos.controller;

import com.example.dadosmeteorologicos.Services.CidadeService;
import com.example.dadosmeteorologicos.model.Cidade;
import java.util.List;
import java.util.Optional;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonBar.ButtonData;



public class CidadeController {

    @FXML
    private TableView<Cidade> tabelaCidades;

    @FXML
    private TableColumn<Cidade, String> colunaSigla;

    @FXML
    private TableColumn<Cidade, String> colunaNome;

    @FXML
    private TableColumn<Cidade, Void> colunaBotao;

    @FXML
    private Button btnCriar;
  
    @FXML
    public void initialize() {
        System.out.println("Iniciando Cidade");
        CidadeService cidadeService = new CidadeService();
        List<Cidade> cidadesDoBanco = cidadeService.getCidades();



        colunaSigla.setCellValueFactory(new PropertyValueFactory<>("sigla"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        colunaBotao.setCellFactory(param -> new TableCell<Cidade, Void>() {
            private final Button btn = new Button ("Deletar Cidade");
        
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    btn.setOnAction(event -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmação");
                        alert.setHeaderText("Você tem certeza que deseja excluir essa cidade?");
        
                        ButtonType ButtonTypeSim = new ButtonType("Sim", ButtonData.YES);
                        ButtonType ButtonTypeNao = new ButtonType("Não", ButtonData.NO);
        
                        alert.getButtonTypes().setAll(ButtonTypeSim, ButtonTypeNao);
        
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonTypeSim) {
                            Cidade cidade = getTableView().getItems().get(getIndex());
                            cidadeService.deletarCidade(cidade.getId(), cidade.getSigla());
        

                            tabelaCidades.getItems().remove(cidade);
                        }
                    });
                    setGraphic(btn);
                }
            }
        });

        colunaNome.setStyle( "-fx-alignment: CENTER;");
        colunaSigla.setStyle( "-fx-alignment: CENTER;");
        colunaBotao.setStyle( "-fx-alignment: CENTER;");

        tabelaCidades.getItems().addAll(cidadesDoBanco);
        // List<Cidade> cidadesDoBanco = cidadeService.getCidades();
        // criarTabela(cidadesDoBanco);
    }
       // private static CidadeService cidadeService = new CidadeService();


}

   

