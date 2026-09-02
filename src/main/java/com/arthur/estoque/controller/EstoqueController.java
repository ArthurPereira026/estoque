package com.arthur.estoque.controller;

import com.arthur.estoque.model.EstoqueDAO;
import com.arthur.estoque.model.Produto;
import com.arthur.estoque.util.GerenciadorTela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EstoqueController {

    @FXML private TextField campoBusca;
    @FXML private TableView tabelaProdutos;

    @FXML private TableColumn colunaNome;
    @FXML private TableColumn colunaCategoria;
    @FXML private TableColumn colunaQuantidade;
    @FXML private TableColumn colunaPreco;

    private final EstoqueDAO dadosEstoque = new EstoqueDAO();
    private FilteredList<Produto> listaFiltrada;
    private final ObservableList<Produto> listaCompleta = FXCollections.observableArrayList();


    @FXML
    public void initialize() throws SQLException {

        tabelaProdutos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));


        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));


        listaFiltrada = new FilteredList<>( listaCompleta, p -> true);
        tabelaProdutos.setItems(listaFiltrada);
        atualizarTabela();

        listaCompleta.setAll(dadosEstoque.listarProdutos());

        campoBusca.textProperty().addListener( (obs, textAntigo, textoNovo )->{
            String filtro = textoNovo == null ? "" : textoNovo.toLowerCase();
            listaFiltrada.setPredicate( produto -> filtro.isEmpty() || produto.getNome().toLowerCase().contains(filtro) || produto.getCategoria().toLowerCase().contains(filtro) || String.valueOf(produto.getPreco()).contains(filtro));
        });

    }

    private void atualizarTabela()  {
        try {
            listaCompleta.setAll(dadosEstoque.listarProdutos());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        ObservableList produtoSelecionado =   tabelaProdutos.getSelectionModel().getSelectedItems();
        if (produtoSelecionado.isEmpty()){
            mostrarAlerta("Selecione um produto do estoque para poder remover");
            return;
        }

        List<Produto> listaProduto = new ArrayList<>(produtoSelecionado);
        String produtosExcluidos = " ";
        for (Produto p : listaProduto){
            produtosExcluidos += p.getId() +" " + p.getNome()+"\n";
        }



        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION, "Remover o produto \n" +produtosExcluidos + " \ndo estoque?? Esse ação ira excluir o produto  permanentemente do estoque atual!!");
        confirmacao.setHeaderText(null);
        ButtonType btnSim = new ButtonType("yes");
        ButtonType btnNao = new ButtonType("Não");
        confirmacao.getButtonTypes().setAll( btnSim, btnNao);
        confirmacao.showAndWait().ifPresent(botao ->{
            if (botao ==  btnSim){
                dadosEstoque.remover(produtoSelecionado);
                atualizarTabela();
            }
        });


    }

    @FXML
    protected void aoSair(ActionEvent event) throws IOException {
        GerenciadorTela.getInstancia().TrocarTela(event,"menu.fxml","Sistema Estoque - Menu");
    }
}
