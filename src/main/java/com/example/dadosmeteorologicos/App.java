package com.example.dadosmeteorologicos;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;

import com.example.dadosmeteorologicos.db.IniciaBanco;


 //JavaFX App

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        try {
            IniciaBanco banco = new IniciaBanco();
            banco.criarDataBase();
            banco.iniciarBanco();
            banco.fecharConexao();
        } catch (Exception e) {
             Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro com a conexão do banco de dados.\nEntre em contato com os administradores");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            });
        }

        scene = new Scene(loadFXML("Main"), 900, 650);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/example/dadosmeteorologicos/view/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}