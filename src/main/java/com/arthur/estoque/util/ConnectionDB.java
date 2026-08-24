package com.arthur.estoque.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String URL_DB = "jdbc:mysql://localhost:3307/stock_desktop";
    private static final String USARIO_DB = "root";
    private static final String SENHA_DB = "senac";

    private static Connection conexao;

    private ConnectionDB(){};

    public static Connection abrirConexao() throws SQLException {
        if ( conexao == null || conexao.isClosed()){
            conexao = DriverManager.getConnection(URL_DB,USARIO_DB,SENHA_DB);
        }
        return conexao;
    }

    public static void fecharConexao() throws SQLException {
        if(conexao != null && conexao.isClosed()){
            conexao.close();
        }
    }
}
