package com.arthur.estoque.model;

import com.arthur.estoque.util.ConnectionDB;
import com.mysql.cj.jdbc.result.UpdatableResultSet;
import javafx.fxml.FXML;
import javafx.scene.text.TextFlow;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UsuarioDAO {


    private Set<Usuario> bdUsuarios = new HashSet<>();

    public UsuarioDAO(){

        bdUsuarios = new HashSet<>();

    }

    //------------------Cadastrar Usuario ------------------
    public boolean cadastrarUsuario(Usuario usuario){
        String sqlInsert = "INSERT INTO usuarios (email, senha) VALUES (?,?)";
        try (Connection con = ConnectionDB.abrirConexao();PreparedStatement psmt = con.prepareStatement(sqlInsert)){


            String hashSenha = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());

            psmt.setString(1, usuario.getEmail());
            psmt.setString(2, hashSenha);
            psmt.execute();
            return true;

        } catch (SQLException e) {
            System.err.println("Erro na conexão do [BANCO DE DADOS]" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //-----------------Buscar Por Email-----------------
    public Optional<Usuario> buscarPorEmail(String email){
        String sqlSelect = "SELECT * FROM usuarios WHERE email =?";
        try (Connection con = ConnectionDB.abrirConexao();PreparedStatement pstm = con.prepareStatement(sqlSelect)){
            pstm.setString(1,email);

            try (ResultSet rs = pstm.executeQuery()){
                if (rs.next()){
                    Usuario usuario = new Usuario();
                    usuario.setId (rs.getInt("id"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setSenha(rs.getString("senha"));
                    return Optional.of(usuario);
                }

            }
        }catch (SQLException e){
            System.err.println("Erro na conexão do [BANCO DE DADOS]" + e.getMessage());
            e.printStackTrace();

        }
        return Optional.empty();
    }

    //---------------Validar Login--------------
    public boolean validarLogin(String email, String senhaDigitada){
        Optional<Usuario> usuarioEncontrado = buscarPorEmail(email);
        if(usuarioEncontrado.isEmpty()){
            return false;
        }
        String hashSalvo = usuarioEncontrado.get().getSenha();
        return BCrypt.checkpw(senhaDigitada, hashSalvo);
    }

    public void atualizarSenha(String email, String novaSenha){
        String sqlUpdate = "UPDATE usuarios SET senha=? WHERE email=?";

        String hashSenha = BCrypt.hashpw(novaSenha, BCrypt.gensalt());

        try (Connection con = ConnectionDB.abrirConexao();PreparedStatement pstm = con.prepareStatement(sqlUpdate)){
            pstm.setString(1, hashSenha);
            pstm.setString(2,email);
            pstm.executeUpdate();
        } catch (SQLException e){
            System.err.println("[BANCO DE DADOS] erro na atualização de dados");
            e.printStackTrace();
        }

    }

}
