package com.example.dadosmeteorologicos.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.example.dadosmeteorologicos.App;
import com.example.dadosmeteorologicos.Services.ValorMedioService;
import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.RegistroValorMedio;
import com.example.dadosmeteorologicos.model.ValorMedioInfo;
import com.opencsv.CSVWriter;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class ValorMedioController {
    private ValorMedioService service;
    @FXML
    private MenuButton menuButton;
    @FXML
    private DatePicker dataInicial;
    @FXML
    private DatePicker dataFinal;
    @FXML
    private Button executar;
    @FXML
    private Button exportaCsv;

    private String siglaCidade;

    private List<RegistroValorMedio> resultado;

    private LocalDate dataSelecionadaInicial;
    private LocalDate dataSelecionadaFinal;
    

    public ValorMedioController() {
        this.service = new ValorMedioService();
    }

    @FXML
    public void initialize() {
        System.out.println("Iniciado valor medio");
        executar.setVisible(false);
        exportaCsv.setVisible(false);

        // Adiciona um ouvinte à propriedade de texto do menuButton
        menuButton.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            checkFields();
        });

        // Adiciona um ouvinte à propriedade de valor do dataInicial
        dataInicial.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            checkFields();
        });

        // Adiciona um ouvinte à propriedade de valor do dataFinal
        dataFinal.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            checkFields();
        });

        // Busca as cidades do banco de dados
        service = new ValorMedioService();
        List<Cidade> cidades = service.getCidadesDoBancoDeDados();

        // Adiciona um MenuItem para cada cidade
        for (Cidade cidade : cidades) {
            String texto = cidade.getNome() + " - " + cidade.getSigla()  + " dados: " + 
                cidade.getCidadeDetalhes().getDataPrimeiroRegistro()+ " até " + 
                cidade.getCidadeDetalhes().getDataUltimoRegistro();
            Label label = new Label(texto);
            // Aplica o CSS ao Label
            label.setStyle("-fx-font-size: 15px; -fx-font-family: 'Arial'; -fx-alignment: CENTER;");

            // Cria um MenuItem e adiciona o Label a ele
            MenuItem menuItem = new MenuItem();
            menuItem.setGraphic(label);
            menuItem.setId("menuItem" + cidade.getNome());
            menuItem.setOnAction(event -> {
                siglaCidade = cidade.getSigla();
                menuButton.setText(cidade.getNome() + " - " + cidade.getSigla() + 
                    " dados: " + cidade.getCidadeDetalhes().getDataPrimeiroRegistro() + 
                    " até " + cidade.getCidadeDetalhes().getDataUltimoRegistro());

            // Atualiza os limites do DatePicker
            LocalDate minDate = LocalDate.parse(cidade.getCidadeDetalhes().getDataPrimeiroRegistro());
            LocalDate maxDate = LocalDate.parse(cidade.getCidadeDetalhes().getDataUltimoRegistro());

            dataInicial.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.compareTo(minDate) < 0 || date.compareTo(maxDate) > 0);
                }
            });

            dataFinal.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.compareTo(minDate) < 0 || date.compareTo(maxDate) > 0);
                }
                });
            });
            menuButton.getItems().add(menuItem);
        }

        dataInicial.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            dataSelecionadaInicial = newValue;
            checkFields();
        });

        dataFinal.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
            dataSelecionadaFinal = newValue;
            checkFields();
        });
    }

    private void checkFields() {
        // Se todos os campos estiverem preenchidos, mostra o botão de busca
        if (!menuButton.getText().equals("Selecione a cidade") && dataInicial.getValue() != null && dataFinal.getValue() != null) {
            executar.setVisible(true);
            exportaCsv.setVisible(true);
        }
    }

    @FXML
    public void onExecutarClicked() {
        resultadoValorMedio(dataSelecionadaInicial, dataSelecionadaFinal);
        // Carrega a tela de resultados
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("view/TabelaRegistro.fxml"));
            Parent root = loader.load();
            TabelaRegistrosController controller = loader.getController();
            controller.setRegistros(resultado);
            //controller.initialize();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resultadoValorMedio(LocalDate dataSelecionadaInicial, LocalDate dataSelecionadaFinal){
        java.sql.Date dataInicialSqlDate = java.sql.Date.valueOf(dataSelecionadaInicial);
        java.sql.Date dataFinalSqlDate = java.sql.Date.valueOf(dataSelecionadaFinal);
        service = new ValorMedioService();
        resultado = service.getValorMedio(siglaCidade, dataInicialSqlDate, dataFinalSqlDate);
    }

    public void exportaCsv(ActionEvent event) {
        resultadoValorMedio(dataSelecionadaInicial, dataSelecionadaFinal);
        try{
            System.out.println("Exportando CSV");
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-YYYY");
            String dataInicialFormatada = dataSelecionadaInicial.format(formatador);
            String dataFinalFormatada = dataSelecionadaFinal.format(formatador);
            

            String NomeCSV = "Relatório de valor médio(" + siglaCidade + "-"+ dataInicialFormatada +" até "+ dataFinalFormatada + ").csv";
            String enderecoPastaDownload = System.getenv("USERPROFILE") + "/Downloads/";
            String caminhoCompleto = Paths.get(enderecoPastaDownload, NomeCSV).toString();

            FileWriter fileWriter = new FileWriter(caminhoCompleto);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            String[] cabecalho = { "Cidade", "Data", "Hora", "Temperatura (C)",
            "Umidade (%)",
            "Vel. Vento (M/S)",
            "Dir. Vento (M/S)",
            "Chuva (MM)"};
            csvWriter.writeNext(cabecalho);
            for (RegistroValorMedio registro : resultado) {
                String[] linha = new String[8];
                linha[0] = registro.getSiglaCidade();
                linha[1] = registro.getData().toString();
                linha[2] = registro.getHora().toString(); 
                for (ValorMedioInfo info : registro.getValorMedioInfos()) {
                    switch (info.getTipo()) {
                        case "temperaturaMedia":
                            linha[3] = info.getValor().toString();
                            break;
                        case "umidadeMedia":
                            linha[4] = info.getValor().toString();
                            break;
                        case "velVento":
                            linha[5] = info.getValor().toString();
                            break;
                        case "dirVento":
                            linha[6] = info.getValor().toString();
                            break;
                        case "chuva":
                            linha[7] = info.getValor().toString();
                            break;
                    }
                }
                csvWriter.writeNext(linha);
            }
            csvWriter.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("CSV exportado");
            alert.setContentText("O Arquivo " + NomeCSV + " foi exportado com sucesso para a pasta Downloads");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}