package com.example.dadosmeteorologicos.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.example.dadosmeteorologicos.Services.BoxPlotService;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;

public class BoxPlotController {
    private BoxPlotService service;
    @FXML
    private TableColumn<?, ?> columnsVelVento;

    @FXML
    private Button btnExecutar;

    @FXML
    private MenuButton menuButtonEstacao;

    @FXML
    private TableColumn<?, ?> columnDirVento;

    @FXML
    private TableColumn<?, ?> columnData;

    @FXML
    private Button btnExportar;

    @FXML
    private TableColumn<?, ?> columnTemperatura;

    @FXML
    private TableColumn<?, ?> columnChuva;

    @FXML
    private DatePicker dataInicial;

    @FXML
    private TableColumn<?, ?> columnEstacao;

    @FXML
    private TableColumn<?, ?> columnLegenda;

    public BoxPlotController() {
        this.service = new BoxPlotService();
    }

    @FXML
    void initialize() {
        System.out.println("Iniciado boxplot");

        btnExecutar.setVisible(true);

        // Adiciona um ouvinte à propriedade de texto do menuButton de estação
        menuButtonEstacao.textProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    checkFields();
                });

        dataInicial.valueProperty().addListener(
                (ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
                    checkFields();
                });

        // Busca as estações disponíveis no banco de dados
        List<String[]> estacoes = service.getEstacoesDoBancoDeDados();

        if (estacoes == null) {
            System.out.println("Estacoes é null");
        } else {
            System.out.println("Número de estações: " + estacoes.size());
        }

        for (String[] estacao : estacoes) {
            System.out.println(Arrays.toString(estacao));
            MenuItem menuItem = new MenuItem(estacao[0] + " - " + estacao[2] + " - " + estacao[1]);
            menuItem.setOnAction(event -> {
                menuButtonEstacao.setText(menuItem.getText());

                menuButtonEstacao.getItems().add(menuItem);

                LocalDate minDate = LocalDate.parse(estacao[3]);
                LocalDate maxDate = LocalDate.parse(estacao[4]);

                dataInicial.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || date.compareTo(minDate) < 0 || date.compareTo(maxDate) > 0);
                    }
                });
            });
            menuButtonEstacao.getItems().add(menuItem);

        }

    }

    private void checkFields() {
        // Se todos os campos estiverem preenchidos, mostra o botão de busca
        if (!menuButtonEstacao.getText().equals("Selecione a cidade") && dataInicial.getValue() != null) {
            btnExecutar.setVisible(true);
        }
    }

}
