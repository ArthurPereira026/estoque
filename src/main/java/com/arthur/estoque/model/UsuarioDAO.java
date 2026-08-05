package com.arthur.estoque.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UsuarioDAO {

    private static UsuarioDAO instancia;

    private Set<Usuario> bdUsuarios = new HashSet<>();

    public UsuarioDAO(){

        bdUsuarios = new HashSet<>();

    }

    public static UsuarioDAO getInstance(){

        if ( instancia == null){
            instancia = new UsuarioDAO();
        }
        return instancia;

    }

    public Set<Usuario> getBdUsuarios(){
        return bdUsuarios;

    }

    public Optional<Usuario> buscarPorEmail(String email){
        return bdUsuarios.stream().filter( usuario -> usuario.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    public void cadastrarUsuario(Usuario usuario){

        bdUsuarios.add(usuario);

    }
}
