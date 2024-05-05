package com.example.dadosmeteorologicos.controller;

import com.example.dadosmeteorologicos.Services.SuspeitoService;
import com.example.dadosmeteorologicos.model.Registro;
import com.example.dadosmeteorologicos.model.VariavelClimatica;
import com.example.dadosmeteorologicos.db.SuspeitoSQL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class SuspeitoController extends SuspeitoSQL {
    @FXML
    private TableView<Registro> tabelaSuspeitos;
    @FXML
    private TableColumn<Registro, String> colunaCidade, colunaEstacao;
    @FXML
    private TableColumn<Registro, LocalDate> colunaData;
    @FXML
    private TableColumn<Registro, LocalTime> colunaHora;
    @FXML
    private TableColumn<Registro, String> colunaTipo;
    @FXML
    private TableColumn<Registro, Double> colunaValor;
    @FXML
    private ObservableList<Registro> suspeitos = FXCollections.observableArrayList();
    @FXML
    private static SuspeitoService suspeitoService = new SuspeitoService();
    @FXML
    TableColumn<Registro, Registro> colunaEditar = new TableColumn<>("Editar");

    @FXML
    public void initialize() {
        System.out.println("Iniciado suspeito");
        CriarTabela();
        // Carregue os dados suspeitos do banco de dados
        loadSuspeitos();
    }

    public void CriarTabela() {
        // Inicialize as colunas da tabela
        colunaCidade.setCellValueFactory(new PropertyValueFactory<>("siglaCidade"));
        colunaEstacao.setCellValueFactory(new PropertyValueFactory<>("estacao"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        TableColumn<Registro, Registro> colunaExcluir = new TableColumn<>("Excluir");
        // Defina uma fábrica de células para gerar um botão para cada célula
        Callback<TableColumn<Registro, Registro>, TableCell<Registro, Registro>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Registro, Registro> call(final TableColumn<Registro, Registro> param) {
                final TableCell<Registro, Registro> cell = new TableCell<>() {
                    private final Button btn = new Button("Editar");

                    {
                        // Ação de clique do botão
                        btn.setOnAction((ActionEvent event) -> {
                            // Obtenha o registro da linha clicada
                            Registro registro = getTableView().getItems().get(getIndex());
                            // Chame o método handleEdit para editar o registro
                            handleEdit(registro);
                        });
                    }

                    // Atualize o item da célula
                    @Override
                    public void updateItem(Registro item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        // Defina a fábrica de células para a coluna de edição
        colunaEditar.setCellFactory(cellFactory);

        // Adicione a nova coluna à tabela
        tabelaSuspeitos.getColumns().add(colunaEditar);

        // Defina uma fábrica de células para gerar um botão para cada célula
        Callback<TableColumn<Registro, Registro>, TableCell<Registro, Registro>> cellFactoryDelete = new Callback<>() {
            @Override
            public TableCell<Registro, Registro> call(final TableColumn<Registro, Registro> param) {
                final TableCell<Registro, Registro> cell = new TableCell<>() {
                    private final Button btn = new Button("Excluir");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Registro registro = getTableView().getItems().get(getIndex());
                            handleDelete(registro);
                        });
                    }

                    @Override
                    public void updateItem(Registro item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colunaCidade.setStyle("-fx-alignment: CENTER;");
        colunaEstacao.setStyle("-fx-alignment: CENTER;");
        colunaData.setStyle("-fx-alignment: CENTER;");
        colunaHora.setStyle("-fx-alignment: CENTER;");
        colunaTipo.setStyle("-fx-alignment: CENTER;");
        colunaValor.setStyle("-fx-alignment: CENTER;");
        colunaExcluir.setCellFactory(cellFactoryDelete);
        tabelaSuspeitos.getColumns().add(colunaExcluir);

    }

    // Carrega os registros suspeitos do banco de dados
    private void loadSuspeitos() {
        List<Registro> registroSuspeito = suspeitoService.buscaRegistrosSuspeitos();
        // Atribur dados puxados do banco que estão na lista registro à tabela
        ObservableList<Registro> suspeitos = FXCollections.observableArrayList(registroSuspeito);
        tabelaSuspeitos.setItems(suspeitos);
    }

    @FXML
    public void handleEdit(Registro registro) {
        Registro registroSelecionado = registro;
        if (registroSelecionado != null) {
            Dialog<Registro> dialog = new Dialog<>();
            dialog.setTitle("Editar Registro");

            Label labelTipo = new Label("Tipo: " + registroSelecionado.getTipo());
            Label labelValor = new Label("Valor atual: " + registroSelecionado.getValor());
            TextField textField = new TextField();
            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*(\\.\\d*)?")) {
                    textField.setText(oldValue);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro de entrada");
                    alert.setHeaderText(null);
                    alert.setContentText("Por favor, insira um número válido.(Ex: 25.5)");
                    alert.showAndWait();
                }
            });
            dialog.getDialogPane().setContent(new VBox(8, labelTipo, labelValor, textField));

            ButtonType confirmButtonType = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(confirmButtonType);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == confirmButtonType) {
                    VariavelClimatica variavelClimatica = new VariavelClimatica();
                    if (variavelClimatica.tipoSuspeito(registroSelecionado.getTipo(),
                            Double.parseDouble(textField.getText()))) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Erro de entrada");
                        alert.setHeaderText(null);
                        alert.setContentText("Por favor, insira um valor dentro da faixa configurada.");
                        alert.showAndWait();
                        return null;
                    }
                    suspeitoService.editarRegistroSuspeito(registroSelecionado.getId(),
                            Double.parseDouble(textField.getText()));

                }
                return null;
            });
            dialog.showAndWait();
        }
        // Atualize a tabela
        tabelaSuspeitos.refresh();
        loadSuspeitos();
    }

    // Exclui um registro do banco de dados
    @FXML
    public void handleDelete(Registro registro) {
        Registro registroSelecionado = registro;
        if (registroSelecionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Excluir Registro");
            alert.setHeaderText("Você está prestes a excluir um registro.");
            alert.setContentText("Tipo: " + registroSelecionado.getTipo() + "\nValor: " + registroSelecionado.getValor()
                    + "\n\nTem certeza que deseja continuar?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                suspeitoService.deletarRegistroSuspeito(registroSelecionado.getData(), registroSelecionado.getHora(),
                        registroSelecionado.getEstacao(), registroSelecionado.getSiglaCidade());
                loadSuspeitos();
            } else {
                alert.close();
            }
        } else {
            System.out.println("Nenhum registro selecionado");
        }
        // Atualize a tabela
        tabelaSuspeitos.refresh();
        loadSuspeitos();
    }
}