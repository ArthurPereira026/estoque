module com.arthur.estoque {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;


    opens com.arthur.estoque to javafx.fxml;
    opens com.arthur.estoque.controller to javafx.fxml
    opens com.arthur.estoque.model to javafx.base;

    exports com.arthur.estoque;
}