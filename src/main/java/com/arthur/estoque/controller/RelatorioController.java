package com.arthur.estoque.controller;

import com.arthur.estoque.util.GerenciadorTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class RelatorioController {

    @FXML
    protected void aoSair(ActionEvent event) throws IOException {
        GerenciadorTela.getInstancia().TrocarTela(event,"menu.fxml","Sistema Estoque - Menu");

    }
}
