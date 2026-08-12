package com.arthur.estoque.util;

public enum Constantes {

    REXEX_EMAIL ("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"),

    REXEX_SENHA ("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[.@#$%^&+=]).{8,}$");

    private final String valor;

    Constantes(String valor){
        this.valor = valor;
    }
    public String getValor(){
        return valor;
    }
}
