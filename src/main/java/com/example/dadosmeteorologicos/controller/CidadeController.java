// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.dadosmeteorologicos.controller;

import com.example.dadosmeteorologicos.Services.CidadeService;
import com.example.dadosmeteorologicos.model.Cidade;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;


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

    private String cidadeInserida;
    private String siglaInserida;

  
    private static CidadeService cidadeService = new CidadeService();

    @FXML
    void initialize() {
        List<Cidade> cidadesDoBanco = cidadeService.getCidades();
        criarTabela(cidadesDoBanco);
     }

    @FXML
    public void criarCidade(ActionEvent event) {

        if (!criarDialogo()) {
            return;
        }
    
        // Verifica se cidadeInserida e siglaInserida não estão vazias
        if (cidadeInserida.trim().isEmpty() || siglaInserida.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Os campos não podem estar vazios");
            alert.showAndWait();
            return; // Retorna do método se qualquer campo estiver vazio
        }
    
        if (cidadeService.siglaValida(siglaInserida)) {
            cidadeService.criarCidade(cidadeInserida, siglaInserida);
            List<Cidade> cidadesDoBanco = cidadeService.getCidades();
            criarTabela(cidadesDoBanco);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Sigla já cadastrada");
            alert.showAndWait();
            return;
        }

    }

    public Boolean criarDialogo(){
        Dialog<Boolean> dialog = new Dialog<>();
        ButtonType confirmButtonType = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);
    
        TextField CampoNomeCidade = new TextField();
        CampoNomeCidade.setPrefWidth(200);
        TextField CampoSiglaCidade = new TextField();
        CampoSiglaCidade.setPrefWidth(200);
    
        GridPane grid = new GridPane();
        grid.setPrefWidth(400);
        grid.add(new Label("Nome da cidade:"), 0, 0);
        grid.add(CampoNomeCidade, 1, 0);
        grid.add(new Label("Sigla da cidade:"), 0, 1);
        grid.add(CampoSiglaCidade, 1, 1);
        dialog.getDialogPane().setContent(grid);
    
        // Converte o resultado para um par quando o botão de confirmação é pressionado
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButtonType) {
                cidadeInserida = CampoNomeCidade.getText();
                siglaInserida = CampoSiglaCidade.getText();
        
                // Verifica se os campos não estão vazios
                if (cidadeInserida.trim().isEmpty() || siglaInserida.trim().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Os campos não podem estar vazios");
                    alert.showAndWait();
                    return false;
                }
            }
            return true;
        });
    
        // Retorna o valor dentro do Optional
        return dialog.showAndWait().orElse(false);
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