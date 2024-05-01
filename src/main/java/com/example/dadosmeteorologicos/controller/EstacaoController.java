package com.example.dadosmeteorologicos.controller;

import java.util.List;
import java.util.Optional;

import com.example.dadosmeteorologicos.Services.EstacaoService;
import com.example.dadosmeteorologicos.model.Estacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

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

    @FXML
    private Button adicionarEstacaoButton; // Botão para adicionar uma nova estação

    @FXML
    private TextField siglaCidade; // Campo para a sigla da cidade

    @FXML
    private TextField numeroEstacao; // Campo para o número da estação

    

    // Método chamado quando a classe é inicializada
    @FXML
    public void initialize() {
        // ...
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

    // Método para adicionar uma estação
    @FXML
    private void adicionarNovaEstacao(ActionEvent event) {
    // Código para abrir a janela popup e coletar os dados da nova estação
        try {
        // Criando uma nova janela pop-up.
            Stage popupStage = new Stage();
            popupStage.setTitle("Adicionar Estação");

            Label siglaCidadeLabel = new Label("Sigla da Cidade:");
            TextField siglaCidadeField = new TextField();
            siglaCidadeField.setPrefWidth(100); // Define a largura do campo de entrada

            Label numeroEstacaoLabel = new Label("Número da Estação:");
            TextField numeroEstacaoField = new TextField();
            numeroEstacaoField.setPrefWidth(100); // Define a largura do campo de entrada

            // Botões para adicionar e cancelar a estação
            Button adicionarEstacao = new Button("Adicionar Estação");
            Button cancelarEstacao = new Button("Cancelar");
            cancelarEstacao.setOnAction(e -> popupStage.close());

            // Instância do Estação Service
            EstacaoService estacaoService = new EstacaoService();

            adicionarEstacao.setOnAction(e -> {
                // Cria uma nova estação
                String siglaCidadeNovaEstacao = siglaCidadeField.getText();
                String numeroNovaEstacao = numeroEstacaoField.getText();

            estacaoService.adicionarNovaEstacao(siglaCidadeNovaEstacao, numeroNovaEstacao);

            popupStage.close();
        });

                // Cria um layout e adiciona os componentes
        VBox layout = new VBox(10);
        layout.getChildren().addAll(siglaCidadeLabel, siglaCidadeField, numeroEstacaoLabel, numeroEstacaoField, adicionarEstacao, cancelarEstacao);
        layout.setAlignment(Pos.CENTER); // Alinha todos os componentes no centro

        // Cria uma nova cena e a adiciona ao palco
        Scene scene = new Scene(layout, 300, 200);
        popupStage.setScene(scene);

        // Mostra o palco
        popupStage.show();

        popupStage.setX((Screen.getPrimary().getBounds().getWidth() - popupStage.getWidth()) / 2);
        popupStage.setY((Screen.getPrimary().getBounds().getHeight() - popupStage.getHeight()) / 2);

    }catch (Exception e) {
        // Log the exception
        System.err.println("An error occurred:");
        e.printStackTrace();
        }
    }

    // Método para buscar estações
    @FXML
    public void buscaEstacao(ActionEvent event) {
    }
}
