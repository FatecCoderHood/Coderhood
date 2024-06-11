package com.example.dadosmeteorologicos.controller;

import java.io.FileWriter;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.RegistroSituacao;
import com.opencsv.CSVWriter;
import com.example.dadosmeteorologicos.Services.SituacaoService;
import com.example.dadosmeteorologicos.Services.CidadeService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private Button exportaCsv;

    private Map<Cidade, RegistroSituacao> registroSituacao;

    @FXML
    public void initialize() {
        exportaCsv.setVisible(false);
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

        registroSituacao = situacaoService.getRegistroSituacao(cidades);

        for (Map.Entry<Cidade, RegistroSituacao> entry : registroSituacao.entrySet()) {
            RegistroSituacao registro = entry.getValue();
            tabelaSituacao.getItems().add(registro);
        }
        exportaCsv.setVisible(true);
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

    public void exportaCsv(ActionEvent event) {
         try {
            SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-YYYY");
            Date dataAtual = new Date();
            String dataFormatada = formatador.format(dataAtual);
            System.out.println(dataFormatada);

            String NomeCSV = "Relatório de situação " + dataFormatada + ".csv";
            String enderecoPastaDownload = System.getenv("USERPROFILE") + "/Downloads/";
            String caminhoCompleto = Paths.get(enderecoPastaDownload, NomeCSV).toString();

            FileWriter fileWriter = new FileWriter(caminhoCompleto);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            String[] cabecalho = { "Cidade", "Data/hora Temperatura", "Temperatura (C)",
            "Data/hora Umidade", "Umidade (%)",
            "Data/hora Vel. Vento", "Vel. Vento (M/S)",
            "Data/hora Dir. Vento", "Dir. Vento (M/S)",
            "Data/hora Chuva", "Chuva (MM)"};
            csvWriter.writeNext(cabecalho);
            for (Map.Entry<Cidade, RegistroSituacao> cidadeEInfo : registroSituacao.entrySet()) {
                RegistroSituacao info = cidadeEInfo.getValue();
                String[] linha = { cidadeEInfo.getKey().getNome(), 
                info.getDataTemperaturaMedia().toString()+ " " +info.getHoraTemperaturaMedia().toString(), info.getTemperaturaMedia(),
                info.getDataUmidadeMedia().toString() + " " +info.getHoraUmidadeMedia().toString(), info.getUmidadeMedia(),
                info.getDataVelVento().toString()+ " " +info.getHoraVelVento().toString(), info.getVelVento(),
                info.getDataDirVento().toString()+ " " +info.getHoraDirVento().toString(), info.getDirVento(),
                info.getDataChuva().toString()+ " " +info.getHoraChuva().toString(), info.getChuva()};
                csvWriter.writeNext(linha);

            }

            csvWriter.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("CSV exportado");
            alert.setContentText("O Arquivo " + NomeCSV + " foi exportado com sucesso para a pasta Downloads");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
