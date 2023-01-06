module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.controleur;
    opens com.example.demo.controleur to javafx.fxml;
    exports com.example.demo.modele.ressources;
    opens com.example.demo.modele.ressources to javafx.fxml;
}