module com.arthur.estoque {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.arthur.estoque to javafx.fxml;
    exports com.arthur.estoque;
}