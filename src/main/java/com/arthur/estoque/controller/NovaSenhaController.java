package com.arthur.estoque.controller;

import com.arthur.estoque.service.RecuperacaoSenhaService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NovaSenhaController {

    @FXML private TextField novaSenha;
    @FXML private TextField confirmaNovaSenha;
    @FXML private Label senhaIncorreta;

    private final RecuperacaoSenhaService service = new RecuperacaoSenhaService();

    @FXML protected void aoSenhaValida(){

        String senhaNovaText = novaSenha.getText();
        String confirmacao = confirmaNovaSenha.getText();

        if (senhaNovaText.isBlank()){
            senhaIncorreta.setText("As senhas n podem ficar em branco");
            return;
        }
        if (!senhaNovaText.equals(confirmacao)){
            senhaIncorreta.setVisible(true);
            return;

        }
        service.redefinirSenha(senhaNovaText);
        ((Stage) novaSenha.getScene().getWindow()).close();
    }

}
