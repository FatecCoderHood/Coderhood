package com.example.dadosmeteorologicos.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.dadosmeteorologicos.model.RegistroValorMedio;
import com.example.dadosmeteorologicos.model.ValorMedioInfo;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabelaRegistrosController {

    @FXML
    private TableView<RegistroValorMedio> tabelaRegistros;

    @FXML
    private TableColumn<RegistroValorMedio, String> colunaCidade;

    @FXML
    private TableColumn<RegistroValorMedio, Integer> colunaEstacao;

    @FXML
    private TableColumn<RegistroValorMedio, LocalDate> colunaData;

    @FXML
    private TableColumn<RegistroValorMedio, LocalTime> colunaHora;

    @FXML
    private TableColumn<RegistroValorMedio, String> colunaTemperaturaMedia;

    @FXML
    private TableColumn<RegistroValorMedio, String> colunaUmidadeMedia;

    @FXML
    private TableColumn<RegistroValorMedio, String> colunaVelVento;

    @FXML
    private TableColumn<RegistroValorMedio, String> colunaDirVento;

    @FXML
    private TableColumn<RegistroValorMedio, String> colunaChuva;

    private ObservableList<RegistroValorMedio> registros;

    public void setRegistros(List<RegistroValorMedio> registros) {
        this.registros = FXCollections.observableArrayList(registros);
        tabelaRegistros.setItems(this.registros);
    }

    @FXML
    public void initialize() {
        colunaCidade.setCellValueFactory(new PropertyValueFactory<>("siglaCidade"));
        colunaEstacao.setCellValueFactory(new PropertyValueFactory<>("estacao"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
    
        colunaTemperaturaMedia.setCellValueFactory(cellData -> new SimpleStringProperty(getValorPorTipo(cellData.getValue(), "temperaturaMedia")));
        colunaUmidadeMedia.setCellValueFactory(cellData -> new SimpleStringProperty(getValorPorTipo(cellData.getValue(), "umidadeMedia")));
        colunaVelVento.setCellValueFactory(cellData -> new SimpleStringProperty(getValorPorTipo(cellData.getValue(), "velVento")));
        colunaDirVento.setCellValueFactory(cellData -> new SimpleStringProperty(getValorPorTipo(cellData.getValue(), "dirVento")));
        colunaChuva.setCellValueFactory(cellData -> new SimpleStringProperty(getValorPorTipo(cellData.getValue(), "chuva")));
        
        colunaCidade.setStyle("-fx-alignment: CENTER;");
        colunaEstacao.setStyle("-fx-alignment: CENTER;");
        colunaData.setStyle("-fx-alignment: CENTER;");
        colunaHora.setStyle("-fx-alignment: CENTER;");
        colunaTemperaturaMedia.setStyle("-fx-alignment: CENTER;");
        colunaUmidadeMedia.setStyle("-fx-alignment: CENTER;");
        colunaVelVento.setStyle("-fx-alignment: CENTER;");
        colunaDirVento.setStyle("-fx-alignment: CENTER;");
        colunaChuva.setStyle("-fx-alignment: CENTER;");

    }
    
    private String getValorPorTipo(RegistroValorMedio registro, String tipo) {
        for (ValorMedioInfo info : registro.getValorMedioInfos()) {
            if (info.getTipo().equals(tipo)) {
                return info.getValor() != null ? info.getValor().toString() : "-";
            }
        }
        return "-";
    }
}