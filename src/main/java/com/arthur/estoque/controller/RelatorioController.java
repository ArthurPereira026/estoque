package com.arthur.estoque.controller;

import com.arthur.estoque.model.EstoqueDAO;
import com.arthur.estoque.util.GerenciadorTela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class RelatorioController {


    @FXML
    private Label lblTotalProdutos;

    @FXML
    private Label lblValorTotal;

    @FXML
    private Label lblEstoqueBaixo;

    private final EstoqueDAO dadosEstoque = new EstoqueDAO();

    @FXML
    public void initializer(){

        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));

        int totalProdutos = dadosEstoque.listarProdutos().size();
        lblTotalProdutos.setText(String.valueOf(totalProdutos));

        double valorTotalEstoque = dadosEstoque.calcularValorTotalEstoque();
        lblValorTotal.setText( formatoMoeda.format(valorTotalEstoque));

        long estoqueBaixo = dadosEstoque.calcularQuantidadeEstoqueBaixo(10);
        lblEstoqueBaixo.setText(String.valueOf(estoqueBaixo));

    }

    @FXML
    protected void aoSair(ActionEvent event) throws IOException {
        GerenciadorTela.getInstancia().TrocarTela(event,"menu.fxml","Sistema Estoque - Menu");

    }
}
/*
* Conversão de tipos
* Inteiro ou Double-> String = String.valueOf(1) -> saída = "1"
* String ou Double -> Inteiro = Interger.parseInt("1") -> saída = 1
* Inteiro ou String -> Double = Double.parseDouble("8.5") -> saída = 8.5
* String para boolean -> Boolean.parseBoolean ("true") -> saída true
* */