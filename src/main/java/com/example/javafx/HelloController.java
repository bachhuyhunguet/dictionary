package com.example.javafx;

import com.example.solution.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable{

    @FXML
    public TextField input;

    @FXML
    public Text ouput;

    @FXML
    public Button search;

    public Dictionary dictionary = new Dictionary();
    public DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();

    public void start() throws FileNotFoundException {
        dictionaryManagement.InsertFromFile(dictionary);
    }

    public void Search(ActionEvent event) throws FileNotFoundException {
        String s = dictionaryManagement.dictionaryLookup(dictionary, input.getText());
        ouput.setText(s);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            start();
            input.textProperty().addListener((observableValue, s1, t1) -> {
                        if (!input.getText().trim().isEmpty()) {
                            ouput.setText(dictionaryCommandline.dictionarySearcher(dictionary,t1));
                            System.out.println(s1);
                            System.out.println(t1);
                        }
                        else {
                            ouput.setText("");
                        }
                    }
            );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}