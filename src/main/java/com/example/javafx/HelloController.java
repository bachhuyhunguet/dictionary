package com.example.javafx;

import com.example.solution.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable{

    public Dictionary dictionary = new Dictionary();
    public DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField add_word_target;

    @FXML
    private TextField add_word_explain;

    @FXML
    private TextField DeleteWord;

    @FXML
    public void ButtonAdd(ActionEvent event) throws IOException {
        String target = add_word_target.getText();
        String explain = add_word_explain.getText();
        Word add_word = new Word(target, explain);
        System.out.println(add_word.getWord_target() + ":" + add_word.getWord_explain());
        dictionaryCommandline.Add(dictionary,add_word);
        for (int i = 0; i < dictionary.size(); i++) {
            System.out.println(dictionary.get(i).getWord_target() + "_" + dictionary.get(i).getWord_explain() );
        }
        System.out.println();
        dictionaryManagement.dictionaryExportToFile(dictionary);
    }

    @FXML
    void ButtonDelete(ActionEvent event) throws IOException {
        String s = DeleteWord.getText();
        dictionaryCommandline.Delete(dictionary,s);
        dictionaryManagement.dictionaryExportToFile(dictionary);
    }

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
        try {
            start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}