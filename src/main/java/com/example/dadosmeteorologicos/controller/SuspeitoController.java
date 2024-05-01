package com.example.dadosmeteorologicos.controller;


import com.example.dadosmeteorologicos.Services.SuspeitoService;
import com.example.dadosmeteorologicos.model.Registro;
import com.example.dadosmeteorologicos.db.IniciaBanco; 

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Statement;

public class SuspeitoController extends IniciaBanco{
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
        // Inicialize as colunas da tabela
        colunaCidade.setCellValueFactory(new PropertyValueFactory<>("siglaCidade"));
        colunaEstacao.setCellValueFactory(new PropertyValueFactory<>("estacao"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        // Defina uma fábrica de células para gerar um botão para cada célula
        Callback<TableColumn<Registro, Registro>, TableCell<Registro, Registro>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Registro, Registro> call(final TableColumn<Registro, Registro> param) {
                final TableCell<Registro, Registro> cell = new TableCell<>() {
                    private final Button btn = new Button("Editar");
        
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Registro registro = getTableView().getItems().get(getIndex());
                            handleEdit(registro);
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
        colunaEditar.setCellFactory(cellFactory);

        // Adicione a nova coluna à tabela
        tabelaSuspeitos.getColumns().add(colunaEditar);

        // Carregue os dados suspeitos do banco de dados
        loadSuspeitos();
    }

    private void loadSuspeitos() {
        List<Registro> registroSuspeito = suspeitoService.buscaRegistrosSuspeitos();
        for (Registro registro : registroSuspeito) {
            System.out.println(registro.toString());
        }
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
            dialog.getDialogPane().setContent(new VBox(8, labelTipo, labelValor, textField));

            // Adicione um botão de confirmação à caixa de diálogo
            ButtonType confirmButtonType = new ButtonType("Confirmar", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(confirmButtonType);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == confirmButtonType) {
                    registroSelecionado.setValor(Double.parseDouble(textField.getText()));
                    return registroSelecionado;
                }
                return null;
            });

            Optional<Registro> result = dialog.showAndWait();

            result.ifPresent(registroAtualizado -> {
                try {
                    String sql = "UPDATE registro SET valor = ? WHERE id = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setDouble(1, registroAtualizado.getValor());
                    stmt.setInt(2, registroAtualizado.getId());
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                loadSuspeitos();
            });
        } else {
            System.out.println("Nenhum registro selecionado");
        }
    }

        @FXML
        public void handleDelete() {
            // Implemente a lógica de exclusão aqui
        }
    }