package com.example.solution;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    public static String url = "E:\\dictionary\\javafx\\input\\dictionaries.txt";
    public static char wall = '_';

    public Dictionary InsertFromFile(Dictionary dictionary) throws FileNotFoundException {
        File input = new File(this.url);
        Scanner scanner = new Scanner(input);
        while (scanner.hasNextLine()) {
            String m = scanner.nextLine();
            Word new_word = new Word();
            int vt = 0; // cai này lưu vị trí dấu cách trước nghĩa tiếng việt
            for (int i = 0; i < m.length(); i++) {
                if (m.charAt(i) == this.wall) {
                    new_word.setWord_target(m.substring(0,i)); // gán từ tiếng anh cho Word_target
                    vt = i + 1;
                    break;
                }
            }

            int m_size = m.length();
            new_word.setWord_explain(m.substring(vt, m_size ));// gán nghĩa tiếng việt

            dictionary.add(new_word);
        }

        return dictionary;
    }

    public void dictionaryExportToFile(Dictionary dictionary) throws IOException {
        FileWriter writer = new FileWriter(this.url, false);
        for(int i = 0; i < dictionary.list_word.size(); i++) {
            String temp = dictionary.get(i).getWord_target() + this.wall + dictionary.get(i).getWord_explain() + '\n';
            writer.write(temp);
        }

        writer.close();
    }

    public String dictionaryLookup(Dictionary dictionary, String input) {
        String ouput = "";
        for (int i = 0; i < dictionary.size(); i++) {
            if (input.equals(dictionary.get(i).getWord_target())) {
                ouput += dictionary.get(i).getWord_explain();
                break;
            }
        }
        System.out.println(ouput);
        return ouput;
    }

    public static boolean check_in_listview(ListView <String> listView, String s) {
        ObservableList listOfItems = listView.getSelectionModel().getSelectedItems();

        for (Object item : listOfItems) {
            String test =  item.toString();
            System.out.println(test);
            if (s.equals((test))) {
                return true;
            }
        }
        return false;
    }
}