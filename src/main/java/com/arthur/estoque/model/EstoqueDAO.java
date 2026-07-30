package com.arthur.estoque.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class EstoqueDAO {

    private static EstoqueDAO instancia;
    private final ObservableList<Produto> listaProdutos;
    private int idproduto = 1;


    private EstoqueDAO(){
        this.listaProdutos = FXCollections.observableArrayList();
    }



    public static EstoqueDAO getInstancia(){
        if (instancia == null){
            instancia = new EstoqueDAO();
        }
        return instancia;
    }

    public void adicionar(Produto produto){
        produto.setId(idproduto++);/*pode ser idproduto++ ou idproduto+1 os dois são a mesma coisa*/
        listaProdutos.add(produto);
    }

    public ObservableList<Produto> listarProdutos(){
        return listaProdutos;
    }

    public void remover(Produto produto){
        listaProdutos.remove(produto);
    }


}

