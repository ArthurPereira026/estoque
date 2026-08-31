package com.arthur.estoque.controller;

import com.arthur.estoque.model.Usuario;
import com.arthur.estoque.model.UsuarioDAO;
import com.arthur.estoque.util.Constantes;
import com.arthur.estoque.util.GerenciadorTela;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.Optional;


public class CadastroController {

    @FXML private TextField usuarioCadastrar;
    @FXML private PasswordField senhaCadastrar;
    @FXML private PasswordField confirmaSenha;
    @FXML private TextFlow erroSenha;
    @FXML private Label emailInvalido;
    @FXML private Label senhaInvalida;
    @FXML private Text erroDados;

    private static UsuarioDAO dbUsuario = new UsuarioDAO();


    @FXML
    protected void aoConfirmarCadastro(ActionEvent event) throws IOException {
        String email = usuarioCadastrar.getText();


        usuarioCadastrar.setVisible(false);
        senhaCadastrar.setVisible(false);
        senhaInvalida.setVisible(false);
        erroSenha.setVisible(false);

        if (email.isBlank() || !email.matches(Constantes.REXEX_EMAIL.getValor())){
            emailInvalido.setVisible(true);
            return;
        }
        String senha = senhaCadastrar.getText();

        if (senha.isBlank() || !senha.matches(Constantes.REXEX_SENHA.getValor())){
            senhaInvalida.setText("A senha necessita no minimo de uma letra minuscula e maiuscula e algum caracterer especial ");
            senhaInvalida.setVisible(true);
            return;
        }

        String senhaConfirmacao = confirmaSenha.getText();
        if (!senhaConfirmacao.equals(senha)){
            erroSenha.setVisible(true);
            return;
        }

        Usuario novoUsuario = new Usuario(email,senha);
        var usuarioJaCadastrado = dbUsuario.buscarPorEmail(email);

        if (usuarioJaCadastrado.isPresent()){
            erroDados.setText("Esse usuario ja ta cadastrado");
            erroSenha.setVisible(true);
            return;
        }
        dbUsuario.cadastrarUsuario(novoUsuario);

        GerenciadorTela.getInstancia().TrocarTela(event, "login.fxml","Sistema Estoque - Login");

    }

    @FXML
    protected void aoAcessarLogin(Event event) throws IOException {
        GerenciadorTela.getInstancia().TrocarTela(event, "login.fxml","Sistema Estoque - Login");
    }




}
