package com.arthur.estoque.controller;

import com.arthur.estoque.util.GerenciadorTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ProdutoController {

    @FXML
    protected void salvar(){}

    @FXML
    protected void cancelar(ActionEvent event) throws IOException {

        GerenciadorTela.getInstancia().TrocarTela(event, "menu.fxml", "Sistema de Estoque - Menu");
    }
}

