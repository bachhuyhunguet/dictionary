package com.example.javafx;

import com.example.solution.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable{

    public HBox bgSearch;
    public HBox bgNote;
    public HBox bgGame;
    public HBox bgSetting;

    public AnchorPane content;
    public AnchorPane control;


    public TextField inputSearch;
    public AnchorPane submitSearch;

    public AnimationApp animationApp;
    public ImageView imageNotFound;
    public ArrayList<HBox> buttons = new ArrayList<HBox>();
    public ArrayList<AnchorPane> contents = new ArrayList<AnchorPane>();
    public Search search;
    public AnchorPane contentSearch;
    public AnchorPane contentNote;
    public AnchorPane contentSetting;
    public AnchorPane contentGame;

    private void init() {
        this.animationApp = new AnimationApp();
        this.buttons.add(this.bgSearch);
        this.buttons.add(this.bgNote);
        this.buttons.add(this.bgGame);
        this.buttons.add(this.bgSetting);
        this.contents.add(this.contentSearch);
        this.contents.add(this.contentNote);
        this.contents.add(this.contentGame);
        this.contents.add(this.contentSetting);
        this.animationApp.buttonClick(this.buttons, this.contents);
        this.animationApp.hoverAnimation(this.buttons);
        this.search = new Search(this.inputSearch, this.submitSearch, this.contentSearch,this.imageNotFound);
        this.search.searchSol();
    }

    public Dictionary dictionary = new Dictionary();
    public DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.init();
        Search test = new Search(this.inputSearch, this.submitSearch, this.contentSearch, this.imageNotFound);
        test.init();
        test.searchSol();
    }


}