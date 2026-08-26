package com.arthur.estoque.model;

import com.arthur.estoque.util.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {


    private ObservableList<Produto> produtosList;

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

    public List<Produto> listarProdutos() throws SQLException {

        List<Produto> listaInterna = new ArrayList<>();
        String sqlSelect = "SELECT * FROM produtos";
        try(Connection con = ConnectionDB.abrirConexao();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sqlSelect);){

            while (rs.next()){
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setCategoria(rs.getString("categoria"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                listaInterna.add(produto);
            }

        }catch (SQLException e){
            System.err.println("[BANCO DE DADOS] Erro ao executar select de produtos!" + e.getMessage());
            e.printStackTrace();
        }
        return listaInterna;
    }

    public void remover(List<Produto> listProduto)  {
        String sqlDelete = "DELETE FROM produtos WHERE id = ?";
        try(Connection con = ConnectionDB.abrirConexao();PreparedStatement pstm = con.prepareStatement(sqlDelete)){
            for (Produto produto : listProduto){
                pstm.setInt(1, produto.getId());
                pstm.addBatch();
            }
            pstm.executeBatch();
        }catch (SQLException e){
            System.err.println("[BANCO DE DADOS] Erro de dados ao deletar produtos" + e.getMessage());
            e.printStackTrace();
        }
        produtosList.removeAll(listProduto);
    }

    public void updateProduto(Produto produto){
        String sqlUpdate = "UPDATE produtos SET nome=?, categoria=?, quantidade=?,preco=? WHERE id=?";
        try (Connection con = ConnectionDB.abrirConexao();PreparedStatement pstm = con.prepareStatement(sqlUpdate)){
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getCategoria());
            pstm.setInt(3, produto.getQuantidade());
            pstm.setDouble(4, produto.getPreco());
            pstm.setInt(5, produto.getId());
            pstm.executeUpdate();

        }catch (SQLException e){
            System.err.println("[BANCO DE DADOS ERRO] Erro ao tentar atualizar um produto!"+ e.getMessage());
        }
    }


    public double calcularValorTotalEstoque() throws SQLException {
      return  listarProdutos().stream().mapToDouble(Produto::getValorTotal).sum();
    }

    public long calcularQuantidadeEstoqueBaixo(int limite) throws SQLException {
        return listarProdutos().stream().filter(p -> p.getQuantidade() < limite).count();
    }


}

