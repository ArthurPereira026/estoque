package com.arthur.estoque.controller;

import com.arthur.estoque.model.Usuario;
import com.arthur.estoque.model.UsuarioDAO;
import com.arthur.estoque.util.GerenciadorTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class LoginController {
    @FXML
    private TextField usuario;

    @FXML
    private PasswordField senha;

    @FXML
    private TextFlow erroDados;

   private final UsuarioDAO bdUsuario = UsuarioDAO.getInstance();



    @FXML
    protected void aoApertarBotao(ActionEvent event) throws IOException {

        String usuarioDigitado = usuario.getText().toLowerCase();
        String senhaDigitada = senha.getText();

        Optional<Usuario> usuarioEncontrado = bdUsuario.buscarPorEmail(usuarioDigitado);

        if (usuarioEncontrado.isPresent() && usuarioEncontrado.get().getSenha().equals(senhaDigitada)){
            GerenciadorTela.getInstancia().TrocarTela(event, "menu.fxml", "Sistema Estoque - Login");
        } else {
            erroDados.setVisible(true);
        }
    }

    @FXML
    protected  void aoEsquecerSenha(){
        System.out.println(" Você esqueceu! Já não é problema meu.");
    }

    @FXML
    protected void aoCadastrar(MouseEvent event) throws IOException {

        GerenciadorTela.getInstancia().TrocarTela(event,"cadastro.fxml", "Sistema Estoque - Novo Usuário");
    }
}
