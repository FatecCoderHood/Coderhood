// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.dadosmeteorologicos.controller;

import com.example.dadosmeteorologicos.Services.CidadeService;
import com.example.dadosmeteorologicos.model.Cidade;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;



public class CidadeController {

    @FXML
    private TableView<Cidade> tabelaCidades;

    @FXML
    private TableColumn<Cidade, String> colunaSigla;

    @FXML
    private TableColumn<Cidade, String> colunaNome;

    @FXML
    private TableColumn<Cidade, String> colunaBotao;

    @FXML
    private Button btnCriar;
  
    private static CidadeService cidadeService = new CidadeService();

    @FXML
    void initialize() {
        List<Cidade> cidadesDoBanco = cidadeService.getCidades();
        criarTabela(cidadesDoBanco);
     }


    public void criarTabela(List<Cidade> cidadesDoBanco) {
    colunaSigla.setCellValueFactory(new PropertyValueFactory<>("sigla"));
    colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

    colunaBotao.setCellValueFactory(param -> new SimpleStringProperty("Excluir"));
    colunaBotao.setCellFactory(param -> new TableCell<>() {
        private final Button btn = new Button();

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (item == null || empty) {
                setGraphic(null);
            } else {
                btn.setText(item);
                btn.setOnAction(event -> {
                    Cidade cidade = getTableView().getItems().get(getIndex());
                    System.out.println("Excluindo cidade: " + cidade.getId());
                    
                });
                setGraphic(btn);
            }
        }
    });

    colunaNome.setStyle( "-fx-alignment: CENTER;");
    colunaSigla.setStyle( "-fx-alignment: CENTER;");
    colunaBotao.setStyle( "-fx-alignment: CENTER;");

    tabelaCidades.getItems().addAll(cidadesDoBanco);
}

}


   

