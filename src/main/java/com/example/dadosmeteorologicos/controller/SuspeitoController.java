package com.example.dadosmeteorologicos.controller;


import com.example.dadosmeteorologicos.Services.SuspeitoService;
import com.example.dadosmeteorologicos.model.Registro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

public class SuspeitoController {
    @FXML
    private TableView<Registro> tabelaSuspeitos;
    @FXML
    private TableColumn<Registro, String> colunaCidade, colunaEstacao;
    @FXML
    private TableColumn<Registro, LocalDate> colunaData;
    @FXML
    private TableColumn<Registro, LocalTime> colunaHora;
    @FXML
    private TableColumn<Registro, String> colunaTipo;
    @FXML
    private TableColumn<Registro, Double> colunaValor;
    @FXML
    private ObservableList<Registro> suspeitos = FXCollections.observableArrayList();

    private static SuspeitoService suspeitoService = new SuspeitoService();

    @FXML
    public void initialize() {
        // Inicialize as colunas da tabela
        colunaCidade.setCellValueFactory(new PropertyValueFactory<>("siglaCidade"));
        colunaEstacao.setCellValueFactory(new PropertyValueFactory<>("estacao"));
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        // Carregue os dados suspeitos do banco de dados
        loadSuspeitos();
    }

    private void loadSuspeitos() {
        List<Registro> registroSuspeito = suspeitoService.buscaRegistrosSuspeitos();
        for (Registro registro : registroSuspeito) {
            System.out.println(registro.toString());
        }
        // Atribur dados puxados do banco que estão na lista registro à tabela
        ObservableList<Registro> suspeitos = FXCollections.observableArrayList(registroSuspeito);
        tabelaSuspeitos.setItems(suspeitos);
    }

    @FXML
    public void handleEdit() {
        // Obtenha o registro selecionado na tabela
        Registro registroSelecionado = tabelaSuspeitos.getSelectionModel().getSelectedItem();
        if (registroSelecionado != null) {
            // Implemente a lógica de edição aqui

            // Exemplo: abrir uma janela de edição com os dados do registro selecionado
        } else {
            // Caso nenhum registro esteja selecionado, exiba uma mensagem de erro ou aviso
        }
    }

    @FXML
    public void handleDelete() {
        // Implemente a lógica de exclusão aqui
    }
}