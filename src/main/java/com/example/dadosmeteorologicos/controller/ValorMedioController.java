package com.example.dadosmeteorologicos.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.example.dadosmeteorologicos.App;
import com.example.dadosmeteorologicos.Services.ValorMedioService;
import com.example.dadosmeteorologicos.model.Cidade;
import com.example.dadosmeteorologicos.model.RegistroValorMedio;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    String siglaCidade;


    @FXML
    public void initialize() {
        System.out.println("Iniciado valor medio");
        executar.setVisible(false);

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
        //cidade[0] Nome cidade
        //cidade[1] Sigla cidade
        //cidade[2] Data primeiro registro
        //cidade[3] Data ultimo registro
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
    }

    private void checkFields() {
        // Se todos os campos estiverem preenchidos, mostra o botão de busca
        if (!menuButton.getText().equals("Selecione a cidade") && dataInicial.getValue() != null && dataFinal.getValue() != null) {
            executar.setVisible(true);
        }
    }

    @FXML
    public void onExecutarClicked() {
        // Obtém as datas selecionadas
        LocalDate dataSelecionadaInicial = dataInicial.getValue();
        LocalDate dataSelecionadaFinal = dataFinal.getValue();
        java.sql.Date dataInicialSqlDate = java.sql.Date.valueOf(dataSelecionadaInicial);
        java.sql.Date dataFinalSqlDate = java.sql.Date.valueOf(dataSelecionadaFinal);
        
        System.out.println("SiglaCidade selecionadas: " + siglaCidade);
        service = new ValorMedioService();
        List<RegistroValorMedio> resultado = service.getValorMedio(siglaCidade, dataInicialSqlDate, dataFinalSqlDate);
        System.out.println("Resultado: " + resultado.toString());
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
}