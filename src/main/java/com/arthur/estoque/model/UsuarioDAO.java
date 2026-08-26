package com.arthur.estoque.model;

import com.arthur.estoque.util.ConnectionDB;
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

    public Set<Usuario> getBdUsuarios(){
        return bdUsuarios;

    }

    //------------------Cadastrar Usuario ------------------
    public boolean cadastrarUsuario(Usuario usuario){
        String sqlInsert = "INSERT INTO usuarios (email, senha) VALUES (?,?)";
        try (Connection con = ConnectionDB.abrirConexao();PreparedStatement psmt = con.prepareStatement(sqlInsert)){

            String hashSenha = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());

            psmt.setString(1, usuario.getEmail());
            psmt.setString(2, hashSenha);
            psmt.executeUpdate();
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
            System.err.println("Erro na conexão do [BANCO DE DADOS]\" + e.getMessage()");
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

}
