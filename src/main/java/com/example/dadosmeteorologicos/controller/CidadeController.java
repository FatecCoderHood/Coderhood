// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.dadosmeteorologicos.controller;

import com.example.dadosmeteorologicos.Services.CidadeService;
import com.example.dadosmeteorologicos.model.Cidade;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;


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

    private String cidadeInserida;
    private String siglaInserida;

    private static CidadeService cidadeService = new CidadeService();

    @FXML
    void initialize() {
        List<Cidade> cidadesDoBanco = cidadeService.getCidades();
        criarTabela(cidadesDoBanco);
        colunaNome.setCellFactory(TextFieldTableCell.forTableColumn());
        tabelaCidades.setEditable(true);
        gerenciarCidade();
    }

    @FXML
    public void criarCidade(ActionEvent event) {

        if (!criarDialogo()) {
            return;
        }
        if (cidadeService.siglaValida(siglaInserida)) {
            cidadeService.criarCidade(cidadeInserida, siglaInserida);
            tabelaCidades.getItems().clear();
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
        dialog.setTitle("Nova Cidade");
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
                if (siglaInserida.trim().isEmpty() || siglaInserida.trim().length() < 2) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    if (siglaInserida.trim().isEmpty()) {
                        alert.setTitle("ERRO");
                        alert.setContentText("Campo sigl não podem estar vazios");
                    } else {
                        alert.setTitle("ERRO");
                        alert.setContentText("Sigla precisa ter no mínimo 2 caracteres");
                    }
                    alert.showAndWait();
                    return false;
                }
                return true;
            }
            return false;
        });
        // Retorna o valor dentro do Optional
        return dialog.showAndWait().orElse(false);
    }

    @FXML
    void criarTabela(List<Cidade> cidadesDoBanco) {
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
                            System.out.println("Cidade deletada, ID: "+cidade.getId()+" Sigla cidade: "+cidade.getSigla()+ " Deletado com sucesso!");

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
    }

    private void gerenciarCidade(){
        colunaNome.setOnEditCommit(event -> {
            Cidade cidade = event.getRowValue();
            cidade.setNome(event.getNewValue());
            cidadeService.atualizarCidade(cidade.getId(), cidade.getNome());
            tabelaCidades.getItems().clear();
            List<Cidade> cidadesDoBanco = cidadeService.getCidades();
            criarTabela(cidadesDoBanco);
        });

    }
}