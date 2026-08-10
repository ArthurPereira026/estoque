package com.arthur.estoque.controller;

import com.arthur.estoque.service.RecuperacaoSenhaService;
import com.arthur.estoque.util.GerenciadorTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CodigoController {

    @FXML private Label codigoRecebido;
    @FXML private TextField codigoConfirmacao;
    @FXML private Label codigoIncorreto;

    private final RecuperacaoSenhaService service =  new RecuperacaoSenhaService();

    @FXML protected void aoCodigoCorreto(ActionEvent event) throws IOException {

        String codigoDigitado = codigoRecebido.getText();
        if (!service.validarCodigo(codigoDigitado)){
            codigoIncorreto.setVisible(true);
            return;
        }
        GerenciadorTela.getInstancia().TrocarTela(event, "novaSenha.fxml","Nova Senha");

    }
}
