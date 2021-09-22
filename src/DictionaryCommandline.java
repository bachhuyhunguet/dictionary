import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryCommandline  {
    public static int spaceword = 30;
    public void showAllWords(Dictionary dictionar) {
        for(int i = 0; i < dictionar.list_word.size(); i++) {
            String line = Integer.toString(i+1) + '\t' + "| ";
            String english = dictionar.list_word.get(i).getWord_target();
            line += english;
            int space = this.spaceword;
            if(english.length() > spaceword) {
                space = english.length() + 3;
            }
            for(int j = 0; j < space - english.length(); j++) {
                line +=' ';
            }
            line += "| " + dictionar.list_word.get(i).getWord_explain();
            System.out.println(line);
        }
    }

    public void dictionaryBasic(Dictionary dictionary,  DictionaryManagement dictionaryManagement ) throws FileNotFoundException {
        dictionaryManagement.InsertFromFile(dictionary);
        this.showAllWords(dictionary);
    }

    public void dictionaryAdvanced(Dictionary dictionary,  DictionaryManagement dictionaryManagement) throws FileNotFoundException {
        dictionaryManagement.InsertFromFile(dictionary);
        this.showAllWords(dictionary);
        dictionaryManagement.dictionaryLookup(dictionary);
    }

    public void dictionarySearcher(Dictionary dictionary) {
        Scanner cin = new Scanner(System.in);
        String input;
        input = cin.nextLine();
        Dictionary temp = new Dictionary();
        for(int i = 0; i < dictionary.list_word.size(); i++) {
            String english = dictionary.get(i).getWord_target();
            if(english.length() >= input.length()) {
                if(english.substring(0,input.length()).equals(input)) {
                    temp.add(dictionary.get(i));
                }
            }
        }
        if(temp.list_word.size() == 0) {
            System.out.println("word not found");
            return;
        }
        this.showAllWords(temp);
    }
}