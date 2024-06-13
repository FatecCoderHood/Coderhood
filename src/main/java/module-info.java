module com.example.dadosmeteorologicos {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;

    requires java.sql;
    requires org.apache.commons.text;
    requires static lombok;
    requires com.opencsv;
    

    opens com.example.dadosmeteorologicos to javafx.fxml;
    opens com.example.dadosmeteorologicos.model to javafx.base;
    opens com.example.dadosmeteorologicos.controller to javafx.fxml;

    exports com.example.dadosmeteorologicos;
}