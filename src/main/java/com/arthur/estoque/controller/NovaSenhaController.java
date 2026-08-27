package com.arthur.estoque.controller;

import com.arthur.estoque.service.RecuperacaoSenhaService;
import com.arthur.estoque.util.Constantes;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class NovaSenhaController {

    @FXML private TextField novaSenha;
    @FXML private TextField confirmaNovaSenha;
    @FXML private Label senhaIncorreta;

    private  RecuperacaoSenhaService service;

    public void NovaSenha(RecuperacaoSenhaService service){
        this.service = service;
    }

    @FXML protected void aoSenhaValida(){



        String senhaNovaText = novaSenha.getText();
        String confirmacao = confirmaNovaSenha.getText();

        if (senhaNovaText.isBlank()|| !senhaNovaText.matches(Constantes.REXEX_SENHA.getValor())){
            senhaIncorreta.setText("As senhas n podem ficar em branco");
            return;
        }


        if (!senhaNovaText.equals(confirmacao)){
            senhaIncorreta.setText("A senha necessita de no minimo uma letra minuscula e maiuscula, numero e caracter especial");
            senhaIncorreta.setVisible(true);
            return;

        }

        boolean senhaIgual = service.verificarSenhaAntiga(senhaNovaText);
        if (senhaIgual){
            senhaIncorreta.setText("A Senha não pode ser igual a antiga");
            senhaIncorreta.setVisible(true);
            return;
        }

        service.redefinirSenha(senhaNovaText);

        mostrarAlerta("Senha alterada com sucesso");
        ((Stage) novaSenha.getScene().getWindow()).close();



    }
    public void mostrarAlerta (String mensagem){

        Alert alert = new Alert(Alert.AlertType.INFORMATION,mensagem);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

}
