package com.example.javafx;

import com.example.solution.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    private List<String> list = new ArrayList<>();


    @FXML
    private AnchorPane contentDelete;

    @FXML
    private AnchorPane contentAdd;

    @FXML
    private TextField inputEnglish;

    @FXML
    private TextField inputViet;

    @FXML
    private TextField inputWord;

    @FXML
    private ListView<String> viewAll;

    @FXML
    void add(ActionEvent event) throws IOException {
        String target = inputEnglish.getText();
        String explain = inputViet.getText();
        String announce = "Từ này đã có trong danh sách yêu thích";
        if (target.equals("") || explain.equals("")) {
            announce = "Chưa nhập từ vào";
        }
        Word new_word = new Word(target,explain);
        if (!dictionaryCommandline.checkInDictionary(dictionary,new_word)) {
            dictionaryCommandline.Add(dictionary, new_word);
            dictionaryManagement.dictionaryExportToFile(dictionary);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("thông báo");
            alert.setHeaderText(announce);
            System.out.println("Từ này đã có trong danh sách yêu thích");
            alert.show();
        }
    }

    @FXML
    void delete(ActionEvent event) throws IOException {
        String word = inputWord.getText();
        String announce = "Từ này không có trong danh sách yêu thích";
        if (word.equals("")) {
            announce = "Chưa nhập từ cần xóa";
        }
        if (dictionaryCommandline.wordInDictionary(dictionary,word)) {
            dictionaryCommandline.Delete(dictionary, word);
            dictionaryManagement.dictionaryExportToFile(dictionary);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("thông báo");
            alert.setHeaderText(announce);
            System.out.println("Từ này đã có trong danh sách yêu thích");
            alert.show();
        }
    }

    @FXML
    void anchorAdd(ActionEvent event) {
        contentDelete.setVisible(false);
        contentAdd.setVisible(true);
    }

    @FXML
    void anchorDelete(ActionEvent event) {
        contentDelete.setVisible(true);
        contentAdd.setVisible(false);
    }

    @FXML
    void showAll(ActionEvent event) throws FileNotFoundException {
        viewAll.setVisible(true);
        list = dictionaryCommandline.showAllWordsWord(dictionary);
        viewAll.getItems().clear();
        viewAll.getItems().addAll(list);
    }

    @FXML // show ra item trong listView
    void itemShow(MouseEvent event) {
        if (!viewAll.getSelectionModel().getSelectedItems().equals("")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Bạn có muốn sửa không");
            alert.setContentText("lựa chọn");
            ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonTypeYes) {
                System.out.println("lua chon thay doi");

                Dialog<Pair<String, String>> dialog = new Dialog<>();
                dialog.setTitle("Sửa");

                ButtonType addButtonType = new ButtonType("Sửa", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(20, 150, 10, 10));

                String word = viewAll.getSelectionModel().getSelectedItems().toString();
                System.out.println(word);
                String E = dictionaryManagement.setEnglish(word);
                String V = dictionaryManagement.setVietNames(word);

                TextField English = new TextField();
                English.setText(E);
                TextField VietNames = new TextField();
                VietNames.setText(V);

                grid.add(new Label("Tiếng Anh: "), 0,0);
                grid.add(English, 1, 0);
                grid.add(new Label("Tiếng Việt"), 0,1);
                grid.add(VietNames, 1, 1);

                Node addButton = dialog.getDialogPane().lookupButton(addButtonType);
                addButton.setDisable(true);

                English.textProperty().addListener((observableValue, s, t1) -> {
                    addButton.setDisable(t1.trim().isEmpty());
                });

                dialog.getDialogPane().setContent(grid);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == addButtonType) {
                        return new Pair<>(English.getText(), VietNames.getText());
                    }
                    return null;
                });

                Optional<Pair<String, String>> result_add = dialog.showAndWait();
                result_add.ifPresent(fix -> {
                    Word old_word = new Word();
                    old_word.setWord_target(E);
                    old_word.setWord_explain(V);
                    System.out.println(E + "" + V);
                    dictionary.remove(old_word);
                    for (Word i : dictionary.list_word) {
                        System.out.print(i.getWord_target() + " " + i.getWord_explain() + ",  ");
                    }
                    System.out.println();
                    Word new_word = new Word();
                    new_word.setWord_target(fix.getKey());
                    new_word.setWord_explain(fix.getValue());
                    System.out.println(fix.getKey() + fix.getValue());
                    dictionaryCommandline.Add(dictionary, new_word);
                    for (Word i : dictionary.list_word) {
                        System.out.print(i.getWord_target() + " " + i.getWord_explain() + ",  ");
                    }
                    try {
                        dictionaryManagement.dictionaryExportToFile(dictionary);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                //dialog.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            dictionaryManagement.InsertFromFile(dictionary);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        contentDelete.setVisible(false);
        contentAdd.setVisible(false);
        viewAll.setVisible(false);



        this.init();
        Search test = new Search(this.inputSearch, this.submitSearch, this.contentSearch, this.imageNotFound);
        test.init();
        test.searchSol();
    }



}