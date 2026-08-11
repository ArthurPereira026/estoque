package com.arthur.estoque.controller;

import com.arthur.estoque.model.UsuarioDAO;
import com.arthur.estoque.service.RecuperacaoSenhaService;
import com.arthur.estoque.util.GerenciadorTela;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class EnviarEmailController {

    @FXML private TextField emailRecuperacao;
    @FXML private Label emailNaoCadastrado;

    private final RecuperacaoSenhaService service = new RecuperacaoSenhaService();
    private final UsuarioDAO baseUsuario = UsuarioDAO.getInstance();

    @FXML protected void aoValidarEmail(ActionEvent event) throws IOException {

        String email = emailRecuperacao.getText().trim();

        String codigo = service.solicitarRecuperacao(email,baseUsuario);
        if (codigo == null){
            emailNaoCadastrado.setVisible(true);
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/arthur/estoque/codigoConfirmacao.fxml"));
        Parent root = fxmlLoader.load();

        CodigoController controller = fxmlLoader.getController();
        controller.codigoConfimacao(service,codigo);

        Scene scene = new Scene(root);
        Stage stage = (Stage) emailRecuperacao.getScene().getWindow();
        stage.setTitle("Código confirmação");
        stage.setScene(scene);
        stage.show();




    }
}
