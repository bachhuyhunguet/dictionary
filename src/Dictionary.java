

import java.util.List;
import java.util.Vector;

public class Dictionary {
    List <Word> list_word = new Vector<>();

    public void add(Word word) {
        list_word.add(word);
    }

    public Word get(int i) {
        return list_word.get(i);
    }

    public void set(Word new_word, int i) {
        Word word = list_word.get(i);

        word.setWord_target(new_word.getWord_target());
        word.setWord_explain(new_word.getWord_explain());
    }

    public int size() {
        return list_word.size();
    }

    public void remove(int i) {
        list_word.remove(i);
    }

    public boolean CheckInDictionary(Word word) {
        for (int i = 0; i < list_word.size(); i++) {
            if (word.equals(list_word.get(i).getWord_target())) {
                return true;
            }
        }
        return false;
    }
}