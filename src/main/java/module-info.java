module com.example.dadosmeteorologicos {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires java.sql;
    requires org.apache.commons.text;

    opens com.example.dadosmeteorologicos to javafx.fxml;
    opens com.example.dadosmeteorologicos.model to javafx.base;
    opens com.example.dadosmeteorologicos.controller to javafx.fxml;
    exports com.example.dadosmeteorologicos;
}