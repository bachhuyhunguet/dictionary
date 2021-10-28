package com.example.javafx;

import com.example.solution.*;
import com.example.solution.Dictionary;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.transform.Translate;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

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

        //run search
        Search search= new Search(this.inputSearch, this.submitSearch, this.contentSearch, this.imageNotFound);
        search.init();
        search.searchSol();
        //run game
        Game game = new Game(this.contentGame);
        game.initGame();
    }

    public static Dictionary dictionaryEnglish = new Dictionary(); // dich tu tieng Anh sang tieng Viet
    public static Dictionary dictionaryVietnamese = new Dictionary(); // dich tu Tieng Viet sang Tieng Viet
    public static Dictionary dictionary = new Dictionary();
    public static DictionaryManagement dictionaryManagement = new DictionaryManagement();
    public static DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
    public static int IndexViewAllItem;

    private final double VX = 877;
    private final double VY = 54;

    private final double AX = 739;
    private final double AY = 54;

    public static List<String> list = new ArrayList<>();

    private int count = 0;


    @FXML
    private AnchorPane contentDelete;

    @FXML
    private AnchorPane contentAdd;

    @FXML
    private Button buttonAdd;

    @FXML
    private TextField inputEnglish;

    @FXML
    private TextField inputViet;

    @FXML
    private TextField inputWord;

    @FXML
    private ListView<String> viewAll;

    @FXML
    private ListView<String> suggest;

    @FXML
    private TextField input; // dung cho search offline

    @FXML
    private TextArea output; // dung cho search offline

    @FXML
    private ImageView nodata; // dung khi khong tim duoc tu

    @FXML
    private ImageView America;

    @FXML
    private ImageView VietNam;

    @FXML
    private ImageView America1;

    @FXML
    private ImageView VietNam1;

    @FXML
    void add(ActionEvent event) throws IOException {
        String target = inputEnglish.getText();
        String explain = inputViet.getText();
        String announce = "Từ này đã có trong danh sách yêu thích";

        Word new_word = new Word(target, explain);
        if (target.equals("") || explain.equals("")) {
            announce = "Chưa nhập từ vào";
            new_word = null;
        }
        if (dictionaryCommandline.Add(dictionary, new_word)) {
            dictionaryManagement.dictionaryExportToFile(dictionary);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
        if (dictionaryCommandline.Delete(dictionary, word)) {
            dictionaryManagement.dictionaryExportToFile(dictionary);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
    public void showAll(ActionEvent event) {
        viewAll.setVisible(true);
        list = dictionaryCommandline.showAllWordsWord(dictionary);
        viewAll.getItems().clear();
        viewAll.getItems().addAll(list);
    }

    @FXML // show ra item trong listView
    void itemShow(MouseEvent event) throws IOException {
        if (!viewAll.getSelectionModel().getSelectedItems().equals("")) {
            int n = viewAll.getSelectionModel().getSelectedIndex();
            IndexViewAllItem = n;
            System.out.println(n);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Bạn có muốn sửa không");
            alert.setContentText("lựa chọn");
            ButtonType buttonTypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);

            alert.getButtonTypes().setAll(buttonTypeYes,buttonTypeNo);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonTypeYes) {
                // Một cửa sổ mới (Stage)
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(
                        RepairController.class.getResource("repair.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Sửa từ");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node)event.getSource()).getScene().getWindow() );
                stage.show();
            }
        }
    }


    // search offline

    @FXML
    void convert(ActionEvent event) {
        count++;
        input.setText("");
        output.setText("");
        if (count % 2 == 0) {
            buttonAdd.setVisible(true);
            VietNam.setVisible(true);
            America.setVisible(true);
            VietNam1.setVisible(false);
            America1.setVisible(false);
        }
        else {
            buttonAdd.setVisible(false);
            VietNam.setVisible(false);
            America.setVisible(false);
            VietNam1.setVisible(true);
            America1.setVisible(true);
        }
    }

    @FXML
    void Search(ActionEvent event) {
        String inputText =  input.getText(); // nhap tu vao
        Word result;
        if (count % 2 == 0) {
            result = dictionaryCommandline.dictionarySearcher(dictionaryEnglish, inputText);
        }
        else {
            result = dictionaryCommandline.dictionarySearcher(dictionaryVietnamese, inputText);
        }
        if (result != null) {
            nodata.setVisible(false);
            output.setText(result.getWord_explain());
            if (result.getWord_target().isEmpty()) {
                result = null;
                output.setText("");
                nodata.setVisible(true);
            }
        }
        else {
            nodata.setVisible(true);
        }
    }

    @FXML
    void inputKeyPressed(KeyEvent event) {
        suggest.setVisible(true);
        if (event == null) {
            suggest.setVisible(false);
        }
    }

    @FXML
    void addInOffline(ActionEvent event) throws IOException {
        String target = input.getText();
        String explain = output.getText();
        String announce = "Không thêm vào được";

        Word new_word = new Word(target,explain);
        if (target.equals("") && explain.equals("")) {
            new_word = null;
        }
        if (dictionaryCommandline.Add(dictionary, new_word)) {
            dictionaryManagement.dictionaryExportToFile(dictionary);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("thông báo");
            alert.setHeaderText(announce);
            System.out.println("Từ này đã có trong danh sách yêu thích");
            alert.show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String urlE = "E:\\dictionary\\javafx\\input\\English.txt";
            String urlD = "E:\\dictionary\\javafx\\input\\dictionaries.txt";
            String urlV = "E:\\dictionary\\javafx\\input\\Vietnamese.txt";
            dictionaryManagement.InsertFromFile(dictionary, urlD);
            dictionaryManagement.InsertFromFile(dictionaryEnglish, urlE);
            dictionaryManagement.InsertFromFile(dictionaryVietnamese, urlV);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        viewAll.setVisible(false);
        if (count % 2 == 0) buttonAdd.setVisible(true);
        this.init();
        Search test = new Search(this.inputSearch, this.submitSearch, this.contentSearch, this.imageNotFound);
        test.init();
        test.searchSol();
    }



}