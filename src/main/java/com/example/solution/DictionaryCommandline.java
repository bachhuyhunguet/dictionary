package com.example.solution;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Scanner;

public class DictionaryCommandline  {
    public static int spaceword = 30;
    public String showAllWordsWordEnglish(Dictionary dictionar) {
        String line = "";
        for(int i = 0; i < dictionar.list_word.size(); i++) {
            String english = dictionar.list_word.get(i).getWord_target();
            line += english + "\n";
        }
        return line;
    }

    public void dictionarySearcher(Dictionary dictionary, ListView <String> listView, String input) {
        for(int i = 0; i < dictionary.list_word.size(); i++) {
            String english = dictionary.get(i).getWord_target();
            if(english.length() >= input.length()) {
                if(english.substring(0,input.length()).equals(input)) {
                    if (!DictionaryManagement.check_in_listview(listView,dictionary.get(i).getWord_target())) {
                        listView.getItems().addAll(dictionary.get(i).getWord_target());
                        //System.out.println(dictionary.get(i).getWord_target());
                    }
                }
            }
        }
    }

    public void Delete(Dictionary dictionary, String delete) {
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).getWord_explain().equals(delete) || dictionary.get(i).getWord_target().equals(delete)) {
                dictionary.remove(i);
                break;
            }
        }
    }

    private boolean checkInDictionary(Dictionary dictionary, Word word) {
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).getWord_target().equals(word.getWord_target()) || dictionary.get(i).getWord_explain().equals(word.getWord_explain())) return true;
        }
        return false;
    }

    public void Add(Dictionary dictionary, Word word) {
        if (!checkInDictionary(dictionary,word))
        dictionary.add(word);
    }
}