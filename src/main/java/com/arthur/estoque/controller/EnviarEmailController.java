package com.arthur.estoque.controller;

import com.arthur.estoque.model.UsuarioDAO;
import com.arthur.estoque.service.RecuperacaoSenhaService;
import com.arthur.estoque.util.GerenciadorTela;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        GerenciadorTela.getInstancia().TrocarTela(event, "codigoConfirmacao.fxml", codigo);





    }
}
