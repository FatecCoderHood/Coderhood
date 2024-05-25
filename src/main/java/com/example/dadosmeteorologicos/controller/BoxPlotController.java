package com.example.dadosmeteorologicos.controller;

import java.io.FileWriter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.dadosmeteorologicos.Services.BoxPlotService;
import com.example.dadosmeteorologicos.model.BoxPlot;
import com.example.dadosmeteorologicos.model.ValoresBoxPlot;
import com.opencsv.CSVWriter;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BoxPlotController {
    private BoxPlotService service;
    @FXML
    private MenuButton menuButtonEstacao;

    @FXML
    private DatePicker dataInicial;

    @FXML
    private TableView<ValoresBoxPlot> tabelaDados;

    @FXML
    private TableColumn<ValoresBoxPlot, String> columnTipo;

    @FXML
    private TableColumn<ValoresBoxPlot, Double> columnMin;

    @FXML
    private TableColumn<ValoresBoxPlot, Double> columnQ1;

    @FXML
    private TableColumn<ValoresBoxPlot, Double> columnMediana;

    @FXML
    private TableColumn<ValoresBoxPlot, Double> columnQ3;

    @FXML
    private TableColumn<ValoresBoxPlot, Double> columnMax;

    @FXML
    private TableView<BoxPlot> tabelaEstacao;

    @FXML
    private TableColumn<BoxPlot, LocalDate> columnData;

    @FXML
    private TableColumn<BoxPlot, String> columnEstacao;

    @FXML
    private TableColumn<BoxPlot, String> columnCidade;

    @FXML
    private Button btnExportar;

    @FXML
    private Button btnExecutar;

    // dadosBoxPlot e boxPlotSelecionado transformados em atributos do controllador
    // afim de tornalos globais,
    // podendo ser usados em metodo independentes de serem passados como arguementos

    private List<ValoresBoxPlot> dadosBoxPlot;

    private BoxPlot boxPlotSelecionado;

    public BoxPlotController() {
        this.service = new BoxPlotService();
    }

    @FXML
    public void initialize() {
        System.out.println("Iniciado boxplot");

        btnExecutar.setVisible(false);
        btnExportar.setVisible(false);


        // Adiciona um ouvinte à propriedade de texto do menuButton de estação
        menuButtonEstacao.textProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    resetPage();
                    checkFields();
                });

        dataInicial.valueProperty().addListener(
                (ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
                    resetPage();
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


    private void resetPage() {
        tabelaDados.getItems().clear();
        tabelaEstacao.getItems().clear();
        btnExecutar.setVisible(false);
        btnExportar.setVisible(false);
    }


    private void checkFields() {
        // Se todos os campos estiverem preenchidos, mostra o botão de busca
        if (!menuButtonEstacao.getText().equals("Selecione a cidade") && dataInicial.getValue() != null) {
            btnExecutar.setVisible(true);

        }
        
    }

    private List<Double> convertToDoubleList(List<String> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    @FXML
    public void selecionarEstacao(ActionEvent event) {

        btnExportar.setVisible(true);

        LocalDate dataSelecionada = dataInicial.getValue();
        String estacaoSelecionada = menuButtonEstacao.getText();

        String[] partes = estacaoSelecionada.split(" - ");
        if (partes.length == 3) {
            String numeroEstacao = partes[0];
            String siglaCidade = partes[2];
            String siglaEstacao = partes[1];

            boxPlotSelecionado = new BoxPlot(dataSelecionada, numeroEstacao, siglaCidade, siglaEstacao);

            int numeroEstacaoDados = Integer.parseInt(numeroEstacao);
            Map<String, List<String>> boxPlotDados = service.getBoxPlotDados(numeroEstacaoDados, dataSelecionada);

            List<Double> temperatura = convertToDoubleList(boxPlotDados.get("temperaturaMedia"));
            List<Double> umidade = convertToDoubleList(boxPlotDados.get("umidadeMedia"));
            List<Double> velVento = convertToDoubleList(boxPlotDados.get("velVento"));
            List<Double> dirVento = convertToDoubleList(boxPlotDados.get("dirVento"));
            List<Double> chuva = convertToDoubleList(boxPlotDados.get("chuva"));

            ValoresBoxPlot temperaturaBoxPlot = new ValoresBoxPlot("Temperatura", temperatura.toArray(new Double[0]));
            ValoresBoxPlot umidadeBoxPlot = new ValoresBoxPlot("Umidade", umidade.toArray(new Double[0]));
            ValoresBoxPlot velVentoBoxPlot = new ValoresBoxPlot("Velocidade do Vento", velVento.toArray(new Double[0]));
            ValoresBoxPlot dirVentoBoxPlot = new ValoresBoxPlot("Direção do Vento", dirVento.toArray(new Double[0]));
            ValoresBoxPlot chuvaBoxPlot = new ValoresBoxPlot("Chuva", chuva.toArray(new Double[0]));

            dadosBoxPlot = Arrays.asList(
                    temperaturaBoxPlot, umidadeBoxPlot, velVentoBoxPlot, dirVentoBoxPlot, chuvaBoxPlot);

            criarTabelaCidade(boxPlotSelecionado);
            criarTabelaDados(dadosBoxPlot);
        }
    }



    @FXML
    public void criarTabelaCidade(BoxPlot boxPlotSelecionado) {
        // Cria a tabela informando a data, a estação, a cidade.
        List<BoxPlot> boxPlots = new ArrayList<>();
        boxPlots.add(boxPlotSelecionado);

        columnData.setCellValueFactory(new PropertyValueFactory<>("dataSelecionada"));

        columnEstacao.setCellValueFactory(new PropertyValueFactory<>("numeroEstacao"));

        columnCidade.setCellValueFactory(new PropertyValueFactory<>("cidadeEsigla"));

        tabelaEstacao.getItems().setAll(boxPlots);

        columnData.setStyle("-fx-alignment: CENTER;");
        columnEstacao.setStyle("-fx-alignment: CENTER;");
        columnCidade.setStyle("-fx-alignment: CENTER;");

    }

    @FXML
    public void criarTabelaDados(List<ValoresBoxPlot> dadosBoxPlot) {
        System.out.println(dadosBoxPlot.toString());

        columnTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        columnMin.setCellValueFactory(new PropertyValueFactory<>("min"));
        columnQ1.setCellValueFactory(new PropertyValueFactory<>("q1"));
        columnMediana.setCellValueFactory(new PropertyValueFactory<>("mediana"));
        columnQ3.setCellValueFactory(new PropertyValueFactory<>("q3"));
        columnMax.setCellValueFactory(new PropertyValueFactory<>("max"));

        // Aplicando formatação diretamente nas colunas


        tabelaDados.getItems().setAll(dadosBoxPlot);

        columnTipo.setStyle("-fx-alignment: CENTER;");
        columnMin.setStyle("-fx-alignment: CENTER;");
        columnQ1.setStyle("-fx-alignment: CENTER;");
        columnMediana.setStyle("-fx-alignment: CENTER;");
        columnQ3.setStyle("-fx-alignment: CENTER;");
        columnMax.setStyle("-fx-alignment: CENTER;");
    }



    @FXML
    public void exportaCsv(ActionEvent event) {
        try {
            // Nome do csv contem numeroEstacao, siglaCidade, dataSelecionada e BoxPlot (ex:
            // 420_SP_2021-01-01_BoxPlot.csv)
            String NomeCSV = boxPlotSelecionado.getNumeroEstacao() + "_" + boxPlotSelecionado.getSiglaCidade() + "_" +
                    boxPlotSelecionado.getDataSelecionada() + "_BoxPlot" + ".csv";
            String enderecoPastaDownload = System.getenv("USERPROFILE") + "/Downloads/";
            String caminhoCompleto = Paths.get(enderecoPastaDownload, NomeCSV).toString();

            FileWriter fileWriter = new FileWriter(caminhoCompleto);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            String[] cabecalho = { " ", "Minimo", "1º quartil", "Mediana", "3º quartil", "Maximo" };
            csvWriter.writeNext(cabecalho);
            for (ValoresBoxPlot valorLinha : dadosBoxPlot) {
                String[] valoresConvertidos = valorLinha.converteValorParaCsv(valorLinha);
                csvWriter.writeNext(valoresConvertidos);
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