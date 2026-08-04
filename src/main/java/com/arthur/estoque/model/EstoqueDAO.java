package com.arthur.estoque.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EstoqueDAO {

    private static EstoqueDAO instancia;
    private final ObservableList<Produto> produtosList;
    private int idproduto = 1;


    private EstoqueDAO(){
        this.produtosList = FXCollections.observableArrayList();
    }



    public static EstoqueDAO getInstancia(){
        if (instancia == null){
            instancia = new EstoqueDAO();
        }
        return instancia;
    }

    public void adicionar(Produto produto){
        produto.setId(idproduto++);/*pode ser idproduto++ pra adicionar +1*/
        produtosList.add(produto);
    }

    public ObservableList<Produto> listarProdutos(){
        return produtosList;
    }

    public void remover(Produto produto){
        produtosList.remove(produto);
    }

    public double calcularValorTotalEstoque(){
        double valorTotalEstoque = produtosList.stream().mapToDouble(Produto::getValorTotal).sum();
        return valorTotalEstoque;
    }

    public long calcularQuantidadeEstoqueBaixo(int limite){
        return produtosList.stream().filter(p -> p.getQuantidade() < limite).count();
    }


}

