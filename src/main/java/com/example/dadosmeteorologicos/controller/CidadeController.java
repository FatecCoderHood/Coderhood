// Source code is decompiled from a .class file using FernFlower decompiler.
package com.example.dadosmeteorologicos.controller;

import com.example.dadosmeteorologicos.Services.CidadeService;
import com.example.dadosmeteorologicos.model.Cidade;
import java.util.List;
import javafx.fxml.FXML;



public class CidadeController {
  
    private static CidadeService cidadeService = new CidadeService();

    @FXML
    void initialize() {
        List<Cidade> cidadesDoBanco = cidadeService.getCidades();

        for (Cidade cidade : cidadesDoBanco) {
            System.out.println(cidade.toString());
              
            }
     }

}


   

