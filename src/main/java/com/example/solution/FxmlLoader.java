package com.example.solution;

import com.example.javafx.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.io.FileNotFoundException;
import java.net.URL;

public class FxmlLoader {
    private Pane view;

    public Pane getPane(String fileName) {
        try {
            URL fileUrl = HelloApplication.class.getResource(fileName);
            if (fileUrl == null) {
                throw new FileNotFoundException("FXML file can't be found");
            }
            view = new FXMLLoader().load(fileUrl);
        } catch (Exception e) {
            System.out.println("No pane" + fileName);
        }
        return view;
    }
}
