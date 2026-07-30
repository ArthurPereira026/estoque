package com.arthur.estoque.controller;

import com.arthur.estoque.model.EstoqueDAO;
import com.arthur.estoque.model.Produto;
import com.arthur.estoque.util.GerenciadorTela;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class EstoqueController {



    @FXML
    private TextField campoBusca;

    @FXML
    private TableView tabelaProdutos;

    @FXML
    private TableColumn colunaID;

    @FXML
    private TableColumn colunaNome;

    @FXML
    private TableColumn colunaCategoria;

    @FXML
    private TableColumn colunaQuantidade;

    @FXML
    private TableColumn colunaPreco;

    private final EstoqueDAO dadosEstoque = EstoqueDAO.getInstancia();
    private FilteredList<Produto> listaFiltrada;

    @FXML
    public void initialize(){

        colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));

        listaFiltrada = new FilteredList<>( dadosEstoque.listarProdutos(), p -> true);
        tabelaProdutos.setItems(listaFiltrada);

        campoBusca.textProperty().addListener( (obs, textAntigo, textoNovo )->{
            String filtro = textoNovo == null ? "" : textoNovo.toLowerCase();
            listaFiltrada.setPredicate( produto -> filtro.isEmpty() || produto.getNome().toLowerCase().contains(filtro) || produto.getCategoria().toLowerCase().contains(filtro) || String.valueOf(produto.getPreco()).contains(filtro));
        });



    }

    @FXML
    protected void adicionarProduto(){}

    @FXML
    protected void removerProduto(){}

    @FXML
    protected void editarProduto(){}

    @FXML
    protected void aoSair(ActionEvent event) throws IOException {
        GerenciadorTela.getInstancia().TrocarTela(event,"menu.fxml","Sistema Estoque - Menu");
    }
}
