package com.arthur.estoque.controller;

import com.arthur.estoque.service.RecuperacaoSenhaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CodigoController {

    @FXML private Label codigoRecebido;
    @FXML private TextField codigoInformado;
    @FXML private Label codigoIncorreto;

    private RecuperacaoSenhaService service;

    public void codigoConfimacao(RecuperacaoSenhaService service, String codigoGerado){
        this.service = service;
        codigoRecebido.setText(codigoGerado);
    }

    @FXML protected void aoCodigoCorreto(ActionEvent event) throws IOException {

        String codigoDigitado = codigoInformado.getText();
        if (!service.validarCodigo(codigoDigitado)){
            codigoIncorreto.setVisible(true);
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/arthur/estoque/novaSenha.fxml"));
        Parent root = fxmlLoader.load();

        NovaSenhaController controller = fxmlLoader.getController();
        controller.NovaSenha(service);

        Scene scene = new Scene(root);
        Stage stage = (Stage) codigoRecebido.getScene().getWindow();
        stage.setTitle("Nova Senha");
        stage.setScene(scene);
        stage.show();


    }
}
