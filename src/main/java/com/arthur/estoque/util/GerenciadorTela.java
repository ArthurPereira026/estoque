package com.arthur.estoque.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GerenciadorTela {

    private static GerenciadorTela instancia;

    private GerenciadorTela (){}

    public static GerenciadorTela getInstancia(){
        if (instancia == null){
            instancia = new GerenciadorTela();
        }
        return instancia;
    }

    public void TrocarTela(ActionEvent event, String caminhoFXML, String Titulo)throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/arthur/estoque/"+caminhoFXML));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(Titulo);
        stage.setScene(scene);
        stage.show();

    }

}
