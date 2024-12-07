package com.bibliotech.bibliotech.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class BibliotechController implements Initializable {

    @FXML
    public Label labelExemplo;

    public void change() {
        labelExemplo.setText("Seja bem-vindo!");
    }

    @Bean
    String title() {
        return "Acervo Bibliotech";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        change();
    }

    @Autowired
    private GeneroController generoController;

    public void showGeneros() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GeneroView.fxml"));
            loader.setController(generoController);

            AnchorPane root = loader.load();
            Stage generoStage = new Stage();
            generoStage.setTitle("Gêneros");
            generoStage.setScene(new Scene(root));
            generoStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
