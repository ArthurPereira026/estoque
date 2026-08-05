package com.arthur.estoque.util;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class GerenciadorTela {

    private static GerenciadorTela instancia;

    private GerenciadorTela (){}

    public static GerenciadorTela getInstancia(){
        if (instancia == null){
            instancia = new GerenciadorTela();
        }
        return instancia;
    }

    public void TrocarTela(Event event, String caminhoFXML, String Titulo)throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/arthur/estoque/"+caminhoFXML));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(Titulo);
        stage.setScene(scene);
        stage.show();

    }

    public <T> T telaEdicao(ActionEvent event, String caminho, String titulo, Consumer<T>abrirEdicao) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/arthur/estoque/"+caminho));
        Parent novoRoot = fxmlLoader.load();
        T controller = fxmlLoader.getController();
        if (abrirEdicao != null) {
            abrirEdicao.accept(controller);
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = stage.getScene();
        scene.setRoot(novoRoot);
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.show();
        return controller;
    }



}
