package com.example.dadosmeteorologicos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.dadosmeteorologicos.model.RegistroSituacao;
import com.example.dadosmeteorologicos.Services.SituacaoService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;



public class SituacaoController {

    @FXML
    private AnchorPane Situacao;

    @FXML
    private TableColumn<?, ?> colunaChuva;

    @FXML
    private TableColumn<?, ?> colunaCidade;

    @FXML
    private TableColumn<?, ?> colunaData;

    @FXML
    private TableColumn<?, ?> colunaDirVento;

    @FXML
    private TableColumn<?, ?> colunaEstacao;

    @FXML
    private TableColumn<?, ?> colunaHora;

    @FXML
    private TableColumn<?, ?> colunaTemperatura;

    @FXML
    private TableColumn<?, ?> colunaUmidade;

    @FXML
    private TableColumn<?, ?> colunaVelVento;

    @FXML
    private TableView<RegistroSituacao> tabelaSituacao;

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
        colunaTemperatura.setCellValueFactory(new PropertyValueFactory<>("temperatura"));
        colunaUmidade.setCellValueFactory(new PropertyValueFactory<>("umidade"));
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
        List<RegistroSituacao> registros = situacaoService.buscaSituacaoService();
        ObservableList<RegistroSituacao> listaSituacao = FXCollections.observableArrayList();

        // Use a map to store the latest Registro for each city
        Map<String, RegistroSituacao> ultimoRegistroPorCidade = new HashMap<>();

    for (RegistroSituacao registroSituacao : registros) {
        RegistroSituacao registro = new RegistroSituacao();
        registro.setId(registroSituacao.getId());
        registro.setEstacao(registroSituacao.getEstacao());
        registro.setData(registroSituacao.getData());
        registro.setHora(registroSituacao.getHora());
        registro.setTemperaturaMedia(registroSituacao.getTemperaturaMedia());
        registro.setUmidadeMedia(registroSituacao.getUmidadeMedia());
        registro.setChuva(registroSituacao.getChuva());
        registro.setDirVento(registroSituacao.getDirVento());
        registro.setVelVento(registroSituacao.getVelVento());

        // Get the city from the Registro
        String cidadeString = registroSituacao.getSiglaCidade();

        // If this is the first Registro for this city or if this Registro is newer than the current latest, update the map
        if (!ultimoRegistroPorCidade.containsKey(cidadeString) || registro.getData().isAfter(ultimoRegistroPorCidade.get(cidadeString).getData())) {
            ultimoRegistroPorCidade.put(cidadeString, registroSituacao);
        }
    }

    // Add the latest Registro for each city to the ObservableList
    listaSituacao.addAll(ultimoRegistroPorCidade.values());

    tabelaSituacao.setItems(listaSituacao);
}
}
