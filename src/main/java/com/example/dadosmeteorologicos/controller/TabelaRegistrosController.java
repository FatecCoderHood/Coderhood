package com.example.dadosmeteorologicos.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.dadosmeteorologicos.model.RegistroDto;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabelaRegistrosController {

    @FXML
    private TableView<RegistroDto> tabelaRegistros;

    @FXML
    private TableColumn<RegistroDto, String> colunaCidade;

    @FXML
    private TableColumn<RegistroDto, Integer> colunaEstacao;

    @FXML
    private TableColumn<RegistroDto, LocalDate> colunaData;

    @FXML
    private TableColumn<RegistroDto, LocalTime> colunaHora;

    @FXML
    private TableColumn<RegistroDto, Double> colunaTemperaturaMedia;

    @FXML
    private TableColumn<RegistroDto, Double> colunaUmidadeMedia;

    @FXML
    private TableColumn<RegistroDto, Double> colunaVelVento;

    @FXML
    private TableColumn<RegistroDto, Double> colunaDirVento;

    @FXML
    private TableColumn<RegistroDto, Double> colunaChuva;

    private List<RegistroDto> registros;

    public void setRegistros(List<RegistroDto> registros) {
        this.registros = registros;
    }

    @FXML
    public void initialize() {
        colunaCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colunaEstacao.setCellValueFactory(new PropertyValueFactory<>("estacao"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colunaTemperaturaMedia.setCellValueFactory(new PropertyValueFactory<>("temperaturaMedia"));
        colunaUmidadeMedia.setCellValueFactory(new PropertyValueFactory<>("umidadeMedia"));
        colunaVelVento.setCellValueFactory(new PropertyValueFactory<>("velVento"));
        colunaDirVento.setCellValueFactory(new PropertyValueFactory<>("dirVento"));
        colunaChuva.setCellValueFactory(new PropertyValueFactory<>("chuva"));

        if (registros != null) {
            tabelaRegistros.setItems(FXCollections.observableArrayList(registros));
        }
    }
}
