package com.arthur.estoque.service;

import com.arthur.estoque.model.Usuario;
import com.arthur.estoque.model.UsuarioDAO;

import java.util.Optional;
import java.util.Random;

public class RecuperacaoSenhaService {

    private Usuario usuarioAlvo;
    private String codigoGerado;

    public String solicitarRecuperacao(String email, UsuarioDAO baseUsuario){

        Optional<Usuario> usuarioEncontrado = baseUsuario.buscarPorEmail(email);

        if (usuarioEncontrado.isEmpty()){
            return null;
        }
        this.codigoGerado = gerarCodigo();
        this.usuarioAlvo = usuarioEncontrado.get();

        return this.codigoGerado;
    }

    private String gerarCodigo(){
        int codigo = new Random().nextInt(900_000)+100-00;
        return String.valueOf(codigo);
    }

    public boolean validarCodigo (String codigoDigitado){
        return codigoDigitado != null && usuarioAlvo != null && codigoGerado.equals(codigoDigitado);
    }

    public boolean redefinirSenha (String novaSenha){
        if (usuarioAlvo == null){
            return false;
        }
        usuarioAlvo.setSenha(novaSenha);
        encerrarFluxo();
        return true;
    }

    public void encerrarFluxo(){
        this.usuarioAlvo = null;
        this.codigoGerado = null;
    }
    public Usuario getUsuarioAlvo(){
        return usuarioAlvo;
    }
}
