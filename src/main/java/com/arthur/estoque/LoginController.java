package com.arthur.estoque;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField senha;

    @FXML
    private TextFlow erroDados;

    private final String usuarioCadastrado = "arthur@gmail.com";
    private final String senhaCadastrada = "82648065";


    @FXML
    protected void aoApertarBotao(ActionEvent event) throws IOException {
        if (usuarioCadastrado.equalsIgnoreCase(usuario.getText()) && senhaCadastrada.equals(senha.getText())){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Home Page");
            stage.show();

        }else {
            erroDados.setVisible(true);


        }
    }

    @FXML
    protected void aoEsquecerSenha(){
        System.out.println("Que pena");

    }
}
