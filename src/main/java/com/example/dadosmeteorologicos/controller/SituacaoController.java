package com.example.dadosmeteorologicos.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.RegistroSituacao;
import com.example.dadosmeteorologicos.Services.SituacaoService;
import com.example.dadosmeteorologicos.Services.CidadeService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;



public class SituacaoController {

    @FXML
    private TableView<RegistroSituacao> tabelaSituacao;

    @FXML
    private TableColumn<RegistroSituacao, LocalDate> colunaData;

    @FXML
    private TableColumn<RegistroSituacao, LocalTime> colunaHora;

    @FXML
    private TableColumn<RegistroSituacao, String> colunaEstacao;

    @FXML
    private TableColumn<RegistroSituacao, String> colunaChuva;

    @FXML
    private TableColumn<RegistroSituacao, String> colunaCidade;

    @FXML
    private TableColumn<RegistroSituacao, String> colunaDirVento;

    @FXML
    private TableColumn<RegistroSituacao, String> colunaTemperatura;

    @FXML
    private TableColumn<RegistroSituacao, String> colunaUmidade;

    @FXML
    private TableColumn<RegistroSituacao, String> colunaVelVento;


    @FXML
    public void initialize() {
        System.out.println("Iniciado situação");
        criarTabela();
        loadSituacao();
    }

    public void criarTabela() {
        // Inicialize as colunas da tabela
        colunaCidade.setCellValueFactory(new PropertyValueFactory<>("siglaCidade"));
        colunaEstacao.setCellValueFactory(new PropertyValueFactory<>("estacao"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colunaTemperatura.setCellValueFactory(new PropertyValueFactory<>("temperaturaMedia"));
        colunaUmidade.setCellValueFactory(new PropertyValueFactory<>("umidadeMedia"));
        colunaChuva.setCellValueFactory(new PropertyValueFactory<>("chuva"));
        colunaDirVento.setCellValueFactory(new PropertyValueFactory<>("dirVento"));
        colunaVelVento.setCellValueFactory(new PropertyValueFactory<>("velVento"));

        colunaCidade.setStyle("-fx-alignment: CENTER;");
        colunaEstacao.setStyle("-fx-alignment: CENTER;");
        colunaData.setStyle("-fx-alignment: CENTER;");
        colunaHora.setStyle("-fx-alignment: CENTER;");
        colunaTemperatura.setStyle("-fx-alignment: CENTER;");
        colunaUmidade.setStyle("-fx-alignment: CENTER;");
        colunaChuva.setStyle("-fx-alignment: CENTER;");
        colunaDirVento.setStyle("-fx-alignment: CENTER;");
        colunaVelVento.setStyle("-fx-alignment: CENTER;");
    }

    // Carrega os ultimos registros de cada cidade
    private void loadSituacao() {
        SituacaoService situacaoService = new SituacaoService();
        CidadeService cidadeService = new CidadeService();
        List<Cidade> cidades = cidadeService.getCidades();

        Map<Cidade, RegistroSituacao> registroSituacao = situacaoService.getRegistroSituacao(cidades);
        System.out.println(registroSituacao);

        for (Map.Entry<Cidade, RegistroSituacao> entry : registroSituacao.entrySet()) {
            RegistroSituacao registro = entry.getValue();
            tabelaSituacao.getItems().add(registro);
        }
    }
}
