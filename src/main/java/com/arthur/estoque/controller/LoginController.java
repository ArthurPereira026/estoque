package com.arthur.estoque.controller;

import com.arthur.estoque.model.Usuario;
import com.arthur.estoque.model.UsuarioDAO;
import com.arthur.estoque.util.GerenciadorTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class LoginController {

    @FXML private TextField usuario;
    @FXML private PasswordField senha;
    @FXML private TextFlow erroDados;
    @FXML private TextField senhaVisivel;
    @FXML private FontIcon iconeOlho;

    private boolean senhaVisivelAtiva = false;

   private final UsuarioDAO bdUsuario = new UsuarioDAO();

   @FXML
   public void initialize(){
       senhaVisivel.textProperty().bindBidirectional(senha.textProperty());
   }


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
    protected  void aoEsquecerSenha() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/arthur/estoque/enviaEmail.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sistema de Estoque - Esqueceu a senha");
        stage.show();
    }

    @FXML
    protected void aoVerSenha(){
       senhaVisivelAtiva = !senhaVisivelAtiva;

       senha.setVisible(!senhaVisivelAtiva);
       senha.setManaged(!senhaVisivelAtiva);

       senhaVisivel.setVisible(senhaVisivelAtiva);
       senhaVisivel.setManaged(senhaVisivelAtiva);

       iconeOlho.setIconLiteral(senhaVisivelAtiva ? "mdi2e-eye-outline" : "mdi2e-eye-off-outline");
    }

    @FXML
    protected void aoCadastrar(MouseEvent event) throws IOException {

        GerenciadorTela.getInstancia().TrocarTela(event,"cadastro.fxml", "Sistema Estoque - Novo Usuário");
    }
}
