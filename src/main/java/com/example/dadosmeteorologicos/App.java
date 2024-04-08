package com.example.dadosmeteorologicos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.dadosmeteorologicos.db.IniciaBanco;


 //JavaFX App

public class App extends Application {

    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    private static Scene scene;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws IOException {
        IniciaBanco banco = new IniciaBanco();
        banco.iniciarBanco();
        banco.fecharConexao();
        LOGGER.info("Banco de dados inicializado");

        scene = new Scene(loadFXML("Main"), 900, 600);
        stage.setScene(scene);
        stage.show();
        LOGGER.info("Aplicativo iniciado");
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

     public static void main(String[] args) {
        try {
            // Cria um manipulador de arquivo que grava o log em um arquivo chamado "app.log"
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setLevel(Level.ALL);

            // Adiciona o manipulador de arquivo ao logger
            LOGGER.addHandler(fileHandler);

            launch();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao criar o manipulador de arquivo de log", e);
        }
    }

}