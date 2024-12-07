package com.bibliotech.bibliotech.controllers;

import com.bibliotech.bibliotech.services.GeneroService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class GeneroController implements Initializable {

    private final GeneroService generoService;

    @FXML
    private ListView<String> listViewGeneros;

    @Autowired
    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> generos = FXCollections.observableArrayList();
        generoService.listGeneros().forEach(genero -> generos.add(genero.getGenero()));

        listViewGeneros.setItems(generos);
    }
}
