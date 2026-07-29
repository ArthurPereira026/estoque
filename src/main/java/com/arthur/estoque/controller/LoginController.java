package com.arthur.estoque.controller;

import com.arthur.estoque.util.GerenciadorTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class LoginController {
    @FXML
    private TextField usuario;

    @FXML
    private PasswordField senha;

    @FXML
    private TextFlow erroDados;

    private Map<String,String>usuariosCadastrados = Map.of(
            "arthur@gmail.com","82648065",
            "administrador@gmail.com", "123",
            "funcionario1@gmail.com", "002"
    );




    @FXML
    protected void aoApertarBotao(ActionEvent event) throws IOException {

        String usuarioDigitado = usuario.getText().toLowerCase();
        String senhaDigitada = senha.getText();

        if ( usuariosCadastrados.containsKey(usuarioDigitado) && usuariosCadastrados.get(usuarioDigitado).equals(senhaDigitada)){
            GerenciadorTela.getInstancia().TrocarTela(event, "menu.fxml", "Sistema Estoque - Login");
        } else {
            erroDados.setVisible(true);
        }
    }

    @FXML
    protected  void aoEsquecerSenha(){
        System.out.println(" Você esqueceu! Já não é problema meu.");
    }

}