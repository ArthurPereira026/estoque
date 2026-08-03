package com.arthur.estoque.controller;

import com.arthur.estoque.model.EstoqueDAO;
import com.arthur.estoque.model.Produto;
import com.arthur.estoque.util.GerenciadorTela;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    protected void adicionarProduto(ActionEvent event) throws IOException {
        GerenciadorTela.getInstancia().TrocarTela(event, "produtos.fxml","Sistema Estoque - Produto");
    }

    @FXML
    protected void editarProduto(ActionEvent event)throws IOException{
        Produto produtoSelecionado =  (Produto) tabelaProdutos.getSelectionModel().getSelectedItem();
        if (produtoSelecionado  == null){
            mostrarAlerta("Selecione um produto para editar!!");
            return;
        }
        GerenciadorTela.getInstancia().telaEdicao(event, "produtos.fxml", "Sistema Estoque - Editar Produtos", (ProdutoController controller) -> controller.preencherParaEdicao(produtoSelecionado));

    }

    public void mostrarAlerta ( String mensagem){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensagem);
        alert.setHeaderText(null);
        alert.showAndWait();


    }


    @FXML
    protected void removerProduto(){

        Produto produtoSelecionado =  (Produto) tabelaProdutos.getSelectionModel().getSelectedItem();
        if (produtoSelecionado == null){
            mostrarAlerta("Selecione um produto do estoque para poder remover");
            return;
        }
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION, "Remover o produto " +produtoSelecionado.getNome()+ " do estoque?? Esse ação ira excluir o produto  permanentemente do estoque atual!!");
        confirmacao.setHeaderText(null);
        ButtonType btnSim = new ButtonType("yes");
        ButtonType btnNao = new ButtonType("Não");
        confirmacao.getButtonTypes().setAll( btnSim, btnNao);
        confirmacao.showAndWait().ifPresent(botao ->{
            if (botao ==  btnSim){
                dadosEstoque.remover(produtoSelecionado);
            }
        });


    }

    @FXML
    protected void aoSair(ActionEvent event) throws IOException {
        GerenciadorTela.getInstancia().TrocarTela(event,"menu.fxml","Sistema Estoque - Menu");
    }
}
