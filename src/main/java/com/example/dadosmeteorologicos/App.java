package com.example.dadosmeteorologicos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import com.example.dadosmeteorologicos.db.IniciaBanco;


 //JavaFX App

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        IniciaBanco banco = new IniciaBanco();
        banco.iniciarBanco();
        banco.fecharConexao();

        scene = new Scene(loadFXML("Main"), 900, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

     public static void main(String[] args) {
        launch();
        
    }

}