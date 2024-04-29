// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.dadosmeteorologicos.controller;

import com.example.dadosmeteorologicos.Services.CidadeService;
import com.example.dadosmeteorologicos.model.Cidade;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CidadeController {
  
    @FXML
    private ResourceBundle resources;
    // tentei fazer o boton cidade
    @FXML
    private TextField TxtCidade;
    // tentei fazer o boton Sigla 
    @FXML
    private TextField txtSigla;

    @FXML
    void initialize() {
        assert TxtCidade != null : "fx:id=\"TxtCidade\" was not injected: check your FXML file 'Cidade.fxml'.";
        assert txtSigla != null : "fx:id=\"txtSigla\" was not injected: check your FXML file 'Cidade.fxml'.";
        cidadeService = new CidadeService();
        List<Cidade> cidades = cidadeService.getCidades();
        Iterator var3 = cidades.iterator();
        Iterator var4 = sigla.interator();
         // tentando criar ação para o boton deletar
        while (var3.hasNext()) {
          Cidade cidade = (Cidade) var3.next();
          Sigla sigla = (Sigla)  var4.next();
               
          TextField Cidade = new TextField(cidade.getNome());
          TextField Sigla = new TextField(cidade.getSigla());
            // ... (restante do código)         
          //Label nome = new Label(cidade.getNome() + " - ");
          
           Button btnDeletar = new Button("Deletar");
           btnDeletar.setOnAction((event) -> {
           System.out.println(" Deletar cidade: " + cidade.getId());
           });
           
    }
}

}


   

