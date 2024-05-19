package com.example.dadosmeteorologicos.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.dadosmeteorologicos.Services.BoxPlotService;
import com.example.dadosmeteorologicos.model.Registro;
import com.example.dadosmeteorologicos.model.ValoresBoxPlot;
import com.example.dadosmeteorologicos.model.BoxPlot;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private TableColumn<ValoresBoxPlot, String> columnLegenda;

    @FXML
    private TableColumn<ValoresBoxPlot, String> columnsVelVento;

    @FXML
    private TableColumn<ValoresBoxPlot, String> columnDirVento;

    @FXML
    private TableColumn<ValoresBoxPlot, String> columnTemperatura;

    @FXML
    private TableColumn<ValoresBoxPlot, String> columnUmidade;

    @FXML
    private TableColumn<ValoresBoxPlot, String> columnChuva;

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

    public BoxPlotController() {
        this.service = new BoxPlotService();
    }
    

    @FXML
    void initialize() {
        System.out.println("Iniciado boxplot");


        btnExecutar.setVisible(false);

        

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

    @FXML
    public void selecionarEstacao(ActionEvent event) {
        LocalDate dataSelecionada = dataInicial.getValue();
        String estacaoSelecionada = menuButtonEstacao.getText();

        String[] partes = estacaoSelecionada.split(" - ");
        if (partes.length == 3) {
            String numeroEstacao = partes[0];
            String siglaCidade = partes[2];
            String siglaEstacao = partes[1];
            System.out.println("Estação selecionada: " + numeroEstacao + " - " + siglaCidade + " - " + siglaEstacao);
            System.out.println("Data selecionada: " + dataSelecionada);

            BoxPlot boxPlotSelecionado = new BoxPlot(dataSelecionada, numeroEstacao, siglaCidade, siglaEstacao);

            System.out.println("Data selecionada: " + dataSelecionada + "Texto");
            System.out.println("Estação selecionada: " + numeroEstacao);
            System.out.println("Cidade selecionada: " + siglaCidade);
            System.out.println("Sigla selecionada: " + siglaEstacao);

            criarTabelaCidade(boxPlotSelecionado);



        }
    }

    @FXML
    public void criarTabelaCidade(BoxPlot boxPlotSelecionado) {
        List<BoxPlot> boxPlots = new ArrayList<>();
        boxPlots.add(boxPlotSelecionado);

        columnData.setCellValueFactory(new PropertyValueFactory<>("dataSelecionada"));
        System.out.println("Data: " + boxPlotSelecionado.getDataSelecionada()); // Imprime o valor da primeira célula da coluna Data
    
        columnEstacao.setCellValueFactory(new PropertyValueFactory<>("numeroEstacao"));
        System.out.println("Estação: " + boxPlotSelecionado.getNumeroEstacao()); // Imprime o valor da primeira célula da coluna Estação
    
        columnCidade.setCellValueFactory(new PropertyValueFactory<>("cidadeEsigla"));
        System.out.println("Cidade: " + boxPlotSelecionado.getCidadeEstacao() + boxPlotSelecionado.getSiglaCidade()); // Imprime o valor da primeira célula da coluna Cidade
        
        tabelaEstacao.getItems().setAll(boxPlots);

        columnData.setStyle("-fx-alignment: CENTER;");
        columnEstacao.setStyle("-fx-alignment: CENTER;");
        columnCidade.setStyle("-fx-alignment: CENTER;");

        // Map(Registro, ValoresBoxPlot) dados = service.getDadosBoxPlot(boxPlotSelecionado);

        //TEMPERATURA     private double min; private double q1;private double mediana; private double q3;private double max;

        //UMIDADE         private double min; private double q1;private double mediana; private double q3;private double max;

    }

}
