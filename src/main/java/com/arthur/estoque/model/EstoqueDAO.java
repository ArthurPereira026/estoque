package com.arthur.estoque.model;

import com.arthur.estoque.util.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EstoqueDAO {


    private final ObservableList<Produto> produtosList;

    public EstoqueDAO(){
        this.produtosList = FXCollections.observableArrayList();
    }

    public void adicionar(Produto produto){
        String sqlInsert = " INSERT INTO produtos(nome, categoria, quantidade, preco) VALUES(?,?,?,?) ";
        try(Connection con = ConnectionDB.abrirConexao(); PreparedStatement pstm = con.prepareStatement(sqlInsert)){

            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getCategoria());
            pstm.setInt(3, produto.getQuantidade());
            pstm.setDouble(4, produto.getPreco());
            pstm.execute();


        } catch (SQLException ex){
            System.err.println("Erro na conexão do Banco de Dados" + ex.getMessage());
            ex.printStackTrace();
        }

    }

    public ObservableList<Produto> listarProdutos(){
        return produtosList;
    }

    public void remover(List<Produto> listProduto){
        produtosList.removeAll(listProduto);
    }

    public double calcularValorTotalEstoque(){
        double valorTotalEstoque = produtosList.stream().mapToDouble(Produto::getValorTotal).sum();
        return valorTotalEstoque;
    }

    public long calcularQuantidadeEstoqueBaixo(int limite){
        return produtosList.stream().filter(p -> p.getQuantidade() < limite).count();
    }


}

