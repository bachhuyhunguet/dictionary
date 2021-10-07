package com.example.solution;
import java.io.FileNotFoundException;
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

    public String dictionarySearcher(Dictionary dictionary, String input) {

        Dictionary temp = new Dictionary();
        for(int i = 0; i < dictionary.list_word.size(); i++) {
            String english = dictionary.get(i).getWord_target();
            if(english.length() >= input.length()) {
                if(english.substring(0,input.length()).equals(input)) {
                    temp.add(dictionary.get(i));
                }
            }
        }
        return this.showAllWordsWordEnglish(temp);
    }

    public void dictionaryDelete(Dictionary dictionary, String delete) {
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).getWord_explain().equals(delete) || dictionary.get(i).getWord_target().equals(delete)) {
                dictionary.remove(i);
                break;
            }
        }
    }

    private boolean checkInDictionary(Dictionary dictionary, Word word) {
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.get(i).equals(word)) return true;
        }
        return false;
    }

    public void Add(Dictionary dictionary, Word word) {
        if (checkInDictionary(dictionary,word))
        dictionary.add(word);
    }
}