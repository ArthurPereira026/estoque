package com.arthur.estoque.controller;

import com.arthur.estoque.util.GerenciadorTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MenuController {


    @FXML
    protected void aoVisualizarEstoque(ActionEvent event) throws IOException {

        GerenciadorTela.getInstancia().TrocarTela(event, "estoque.fxml", "Sistema Estoque - Estoque");
    }

    @FXML
    protected void aoCadastrarProduto(ActionEvent event) throws IOException {
        GerenciadorTela.getInstancia().TrocarTela(event, "produtos.fxml", "Sistema Estoque - Produto");
    }

    @FXML
    protected void aoRelatorio(ActionEvent event) throws IOException {
        GerenciadorTela.getInstancia().TrocarTela(event, "relatorio.fxml", "Sistema Estoque - Relatório");
    }

    @FXML
    protected void aoSair(ActionEvent event) throws IOException {
      GerenciadorTela.getInstancia().TrocarTela(event, "login.fxml", "Sistema Estoque - Menu");
    }
}