import java.io.FileNotFoundException;
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
        dictionary = dictionaryManagement.InsertFromFile(dictionary);
        this.showAllWords(dictionary);
    }

    public void dictionaryAdvanced(Dictionary dictionary,  DictionaryManagement dictionaryManagement) throws FileNotFoundException {
        dictionaryManagement.InsertFromFile(dictionary);
        this.showAllWords(dictionary);
        dictionaryManagement.dictionaryLookup(dictionary);
    }

}