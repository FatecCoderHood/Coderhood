package com.example.dadosmeteorologicos.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.RegistroSituacao;
import com.example.dadosmeteorologicos.Services.SituacaoService;
import com.example.dadosmeteorologicos.Services.CidadeService;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;

public class SituacaoController {

    @FXML
    private TableView<RegistroSituacao> tabelaSituacao;

    @FXML
    private TableColumn<RegistroSituacao, String> colunaCidadeESigla;

    @FXML
    private TableColumn<RegistroSituacao, String> colunaChuva;

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
        colunaCidadeESigla.setCellValueFactory(new PropertyValueFactory<>("cidadeESigla"));
        colunaTemperatura.setCellValueFactory(new PropertyValueFactory<>("temperaturaMedia"));
        colunaUmidade.setCellValueFactory(new PropertyValueFactory<>("umidadeMedia"));
        colunaChuva.setCellValueFactory(new PropertyValueFactory<>("chuva"));
        colunaDirVento.setCellValueFactory(new PropertyValueFactory<>("dirVento"));
        colunaVelVento.setCellValueFactory(new PropertyValueFactory<>("velVento"));

        // Ajustar alinhamento das colunas
        colunaTemperatura.setStyle("-fx-alignment: CENTER;");
        colunaUmidade.setStyle("-fx-alignment: CENTER;");
        colunaChuva.setStyle("-fx-alignment: CENTER;");
        colunaDirVento.setStyle("-fx-alignment: CENTER;");
        colunaVelVento.setStyle("-fx-alignment: CENTER;");
        colunaCidadeESigla.setStyle("-fx-alignment: CENTER;");

        adicionarToolTipDataHora(colunaTemperatura, "dataTemperaturaMedia");
        adicionarToolTipDataHora(colunaUmidade, "dataUmidadeMedia");
        adicionarToolTipDataHora(colunaChuva, "dataChuva");
        adicionarToolTipDataHora(colunaDirVento, "dataDirVento");
        adicionarToolTipDataHora(colunaVelVento, "dataVelVento");
    }

    // Carrega os últimos registros de cada cidade
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

    private void adicionarToolTipDataHora(TableColumn<RegistroSituacao, String> coluna, String tipo) {
        coluna.setCellFactory(col -> new TableCell<RegistroSituacao, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                    setTooltip(null);
                } else {
                    setText(item);
    
                    RegistroSituacao registro = getTableView().getItems().get(getIndex());
    
                    LocalDate date = null;
                    LocalTime time = null;
    
                    switch (tipo) {
                        case "dataTemperaturaMedia":
                            date = registro.getDataTemperaturaMedia();
                            time = registro.getHoraTemperaturaMedia();
                            break;
                        case "dataUmidadeMedia":
                            date = registro.getDataUmidadeMedia();
                            time = registro.getHoraUmidadeMedia();
                            break;
                        case "dataVelVento":
                            date = registro.getDataVelVento();
                            time = registro.getHoraVelVento();
                            break;
                        case "dataDirVento":
                            date = registro.getDataDirVento();
                            time = registro.getHoraDirVento();
                            break;
                        case "dataChuva":
                            date = registro.getDataChuva();
                            time = registro.getHoraChuva();
                            break;
                    }
    
                    if (date != null && time != null) {
                        String tooltipText = date + " " + time;
                        Tooltip tooltip = new Tooltip(tooltipText);
                        setTooltip(tooltip);
                    } else {
                        setTooltip(null);
                    }
                }
            }
        });
    }
    
}
