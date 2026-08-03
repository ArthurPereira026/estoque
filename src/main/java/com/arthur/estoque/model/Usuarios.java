package com.arthur.estoque.model;

import javafx.scene.control.ButtonType;

public class Usuarios {

    private String email;
    private String senha;

    public Usuarios(){}

    public Usuarios(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
