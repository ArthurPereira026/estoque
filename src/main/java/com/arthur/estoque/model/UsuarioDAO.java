package com.arthur.estoque.model;

import com.arthur.estoque.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UsuarioDAO {


    private Set<Usuario> bdUsuarios = new HashSet<>();

    public UsuarioDAO(){

        bdUsuarios = new HashSet<>();

    }

    public Set<Usuario> getBdUsuarios(){
        return bdUsuarios;

    }

    public Optional<Usuario> buscarPorEmail(String email){
        return bdUsuarios.stream().filter( usuario -> usuario.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    public void cadastrarUsuario(Usuario usuario){
        String sqlInsert = "INSERT INTO usuarios (email, senha) VALUES (?,?)";
        try (Connection con = ConnectionDB.abrirConexao();PreparedStatement psmt = con.prepareStatement(sqlInsert)){
            psmt.setString(1, usuario.getEmail());
            psmt.setString(2, usuario.getSenha());
            psmt.execute();

        } catch (SQLException e) {
            System.err.println("Erro na conexão do [BANCO DE DADOS]" + e.getMessage());
            e.printStackTrace();
        }

    }
}
