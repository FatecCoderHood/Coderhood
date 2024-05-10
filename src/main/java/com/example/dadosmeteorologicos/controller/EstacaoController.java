package com.example.dadosmeteorologicos.controller;



import java.util.List;
import java.util.Optional;

import com.example.dadosmeteorologicos.Services.CidadeService;
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

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TableCell;

public class EstacaoController {

    // Declaração dos componentes da interface gráfica
    @FXML
    private Button buscaEstacao;

    @FXML
    private TableView<Estacao> estacoes; // Tabela para exibir as estações

    @FXML
    private TableColumn<Estacao, String> ColumnSigla; // Coluna para a sigla da cidade

    @FXML
    private TableColumn<Estacao, String> ColumnEstacao; // Coluna para o número da estação

    @FXML
    private TableColumn<Estacao, String> ColumnNome; // Coluna para o nome da cidade

    @FXML
    private TableColumn<Estacao, String> ColumnDescricao; // Coluna para a descrição da estação

    @FXML
    private TableColumn<Estacao, String> ColumnLatitude; // Coluna para a latitude da estação

    @FXML
    private TableColumn<Estacao, String> ColumnLongitude; // Coluna para a longitude da estação

    @FXML
    private TableColumn<Estacao, Void> ColumnButton; // Coluna para os botões de ação

    @FXML
    private Button adicionarNovaEstacao; // Botão para adicionar uma nova estação

    

    private static EstacaoService estacaoService = new EstacaoService();

    private String estacaoInserida;
    private String siglaInserida;
    private String cidadeInserida;
    

    // Método chamado quando a classe é inicializada
    @FXML
    public void initialize() {
        System.out.println("Iniciado estacao");
        List<Estacao> listaEstacao = estacaoService.buscaEstacao();
        criarTabela(listaEstacao);
        ColumnNome.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnDescricao.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnLatitude.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnLongitude.setCellFactory(TextFieldTableCell.forTableColumn());
        estacoes.setEditable(true);
        gerenciarAlteracoes();
    }

    @FXML
    void adicionarNovaEstacao(ActionEvent event) {
        if (!criarDialogo()) {
            return;
        }
    
        if (estacaoService.numeroEstacaoValido(estacaoInserida)) {
            if(!estacaoService.siglaCidadeExiste(siglaInserida)){
                if (!dialogoCriarCidade()) {
                    System.out.println();
                    return;
                }else{
                    CidadeService cidadeService = new CidadeService();
                    cidadeService.criarCidade(cidadeInserida, siglaInserida);
                }
                
            }
            estacaoService.adicionarNovaEstacao(estacaoInserida, siglaInserida);
            estacoes.getItems().clear();
            List<Estacao> listaEstacao = estacaoService.buscaEstacao();
            criarTabela(listaEstacao);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Número de estação já cadastrado!");
            alert.setTitle("ERRO");
            alert.showAndWait();
            return;
        }
    
    }

    @FXML
    void criarTabela(List<Estacao> estacoesDoBanco) {
        ColumnSigla.setCellValueFactory(new PropertyValueFactory<>("siglaCidade"));
        ColumnEstacao.setCellValueFactory(new PropertyValueFactory<>("numero"));

        ColumnButton.setCellFactory(param -> new TableCell<Estacao, Void>() {
            private final Button btn = new Button("Deletar");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    btn.setOnAction(event -> {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmação");
                        alert.setHeaderText("Você tem certeza que deseja excluir essa estação?\n Será deletado todos os registros relacionados a essa estação!");

                        ButtonType ButtonTypeSim = new ButtonType("Sim", ButtonData.YES);
                        ButtonType ButtonTypeNao = new ButtonType("Não", ButtonData.NO);

                        alert.getButtonTypes().setAll(ButtonTypeSim, ButtonTypeNao);

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonTypeSim) {
                            Estacao estacao = getTableView().getItems().get(getIndex());
                            estacaoService.deletarEstacao(estacao.getId(), estacao.getNumero());
                            estacoes.getItems().remove(estacao);
                        }
                    });
                    setGraphic(btn);
                }
            }
        });

        ColumnButton.setStyle("-fx-alignment: CENTER;");
        ColumnEstacao.setStyle("-fx-alignment: CENTER;");
        ColumnSigla.setStyle("-fx-alignment: CENTER;");

        estacoes.getItems().addAll(estacoesDoBanco);
    }

    public Boolean criarDialogo(){
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Nova Estação");
        ButtonType confirmButtonType = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);
    
        TextField CampoNumeroEstacao = new TextField();
        CampoNumeroEstacao.setPrefWidth(200);
        TextField CampoSiglaCidade = new TextField();
        CampoSiglaCidade.setPrefWidth(200);
    
        GridPane grid = new GridPane();
        grid.setPrefWidth(400);
        grid.add(new Label("Número da estação:"), 0, 0);
        grid.add(CampoNumeroEstacao, 1, 0);
        grid.add(new Label("Sigla da cidade:"), 0, 1);
        grid.add(CampoSiglaCidade, 1, 1);
        dialog.getDialogPane().setContent(grid);
    
        // Converte o resultado para um par quando o botão de confirmação é pressionado
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirmButtonType) {
                estacaoInserida = CampoNumeroEstacao.getText();
                siglaInserida = CampoSiglaCidade.getText();
        
                // Verifica se os campos não estão vazios
                if (estacaoInserida.trim().isEmpty() || siglaInserida.trim().isEmpty() || siglaInserida.trim().length() < 2){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERRO");
                    if (estacaoInserida.trim().isEmpty() || siglaInserida.trim().isEmpty()){
                        alert.setContentText("Os campos não podem estar vazios");
                    } else {alert.setContentText("Sigla precisa ter no mínimo 2 caracteres");
                }
                        alert.showAndWait();
                        return false;
                }

                if (!estacaoInserida.matches("\\d+")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Criar Estação");
                    alert.setContentText("O número da estação deve conter apenas números");
                    alert.showAndWait();
                    return false;
                }
                return true;
            }
            return false;
        });
        return dialog.showAndWait().orElse(false);
    }

    private Boolean dialogoCriarCidade(){
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.setTitle("Cidade não encontrada, criar nova cidade?");
        ButtonType confirmButtonType = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirmButtonType, ButtonType.CANCEL);
    
        TextField CampoNomeCidade = new TextField();
        CampoNomeCidade.setPrefWidth(200);
        TextField CampoSiglaCidade = new TextField();
        CampoSiglaCidade.setPrefWidth(200);
        CampoSiglaCidade.setText(siglaInserida);
        CampoSiglaCidade.setDisable(true);
    
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
                if (cidadeInserida.trim().isEmpty() || siglaInserida.trim().isEmpty() ||siglaInserida.trim().length() < 2) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    if (cidadeInserida.trim().isEmpty() || siglaInserida.trim().isEmpty()) {
                        alert.setTitle("ERRO");
                        alert.setContentText("Os campos não podem estar vazios");
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

    private void gerenciarAlteracoes(){
        ColumnNome.setOnEditCommit(event -> {
            Estacao estacao = event.getRowValue();
            estacao.setNome(event.getNewValue());
            System.out.println(estacao.getNome());
            // estacaoService.atualizarEstacao(estacao.getId(), estacao);
        });
        
        ColumnDescricao.setOnEditCommit(event -> {
            Estacao estacao = event.getRowValue();
            estacao.setDescricao(event.getNewValue());
            System.out.println(estacao.getDescricao());
            // estacaoService.atualizarEstacao(estacao.getId(), estacao);
        });
        
        ColumnLatitude.setOnEditCommit(event -> {
            Estacao estacao = event.getRowValue();
            estacao.setLatitude(event.getNewValue());
            System.out.println(estacao.getLatitude());
            // estacaoService.atualizarEstacao(estacao.getId(), estacao);
        });
        
        ColumnLongitude.setOnEditCommit(event -> {
            Estacao estacao = event.getRowValue();
            estacao.setLongitude(event.getNewValue());
            System.out.println(estacao.getLongitude());
            // estacaoService.atualizarEstacao(estacao.getId(), estacao);
        });
    }

}

