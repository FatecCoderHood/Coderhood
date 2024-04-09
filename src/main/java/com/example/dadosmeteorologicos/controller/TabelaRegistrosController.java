package com.example.dadosmeteorologicos.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.dadosmeteorologicos.model.Registro;
import com.example.dadosmeteorologicos.model.RegistroValorMedio;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabelaRegistrosController {

    @FXML
    private TableView<Registro> tabelaRegistros;

    @FXML
    private TableColumn<Registro, String> colunaCidade;

    @FXML
    private TableColumn<Registro, Integer> colunaEstacao;

    @FXML
    private TableColumn<Registro, LocalDate> colunaData;

    @FXML
    private TableColumn<Registro, LocalTime> colunaHora;

    @FXML
    private TableColumn<Registro, Double> colunaTemperaturaMedia;

    @FXML
    private TableColumn<Registro, Double> colunaUmidadeMedia;

    @FXML
    private TableColumn<Registro, Double> colunaVelVento;

    @FXML
    private TableColumn<Registro, Double> colunaDirVento;

    @FXML
    private TableColumn<Registro, Double> colunaChuva;

    private List<RegistroValorMedio> registros;

    public void setRegistros(List<RegistroValorMedio> registros) {
        this.registros = registros;
    }

    // @FXML
    // public void initialize() {
    //     colunaCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
    //     colunaEstacao.setCellValueFactory(new PropertyValueFactory<>("estacao"));
    //     colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
    //     colunaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
    //     colunaTemperaturaMedia.setCellValueFactory(new PropertyValueFactory<>("temperaturaMedia"));
    //     colunaUmidadeMedia.setCellValueFactory(new PropertyValueFactory<>("umidadeMedia"));
    //     colunaVelVento.setCellValueFactory(new PropertyValueFactory<>("velVento"));
    //     colunaDirVento.setCellValueFactory(new PropertyValueFactory<>("dirVento"));
    //     colunaChuva.setCellValueFactory(new PropertyValueFactory<>("chuva"));

    //     if (registros != null) {
    //         tabelaRegistros.setItems(FXCollections.observableArrayList(registros));
    //     }
    // }
}
