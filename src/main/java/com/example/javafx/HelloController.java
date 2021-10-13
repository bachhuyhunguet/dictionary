package com.example.javafx;

import com.example.solution.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable{

    @FXML
    private BorderPane mainPane;

    public Dictionary dictionary = new Dictionary();
    public DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();

    public void start() throws FileNotFoundException {
        dictionaryManagement.InsertFromFile(dictionary);
    }


     public void ButtonNote(ActionEvent event) {
        System.out.println("you cliked me!");
        FxmlLoader fxmlLoader = new FxmlLoader();
        Pane view = fxmlLoader.getPane("Note.fxml");
        mainPane.setCenter(view);
    }

    public void ButtonSearch(ActionEvent event) {
        System.out.println("you cliked me!");
        FxmlLoader fxmlLoader = new FxmlLoader();
        Pane view = fxmlLoader.getPane("ButtonSearch.fxml");
        mainPane.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


}