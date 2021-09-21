import java.util.List;
import java.util.Vector;

public class Dictionary {
    List<Word> list_word = new Vector<>();
    public void add(Word word) {
        list_word.add(word);
    }

    public Word get(int i) {
        return list_word.get(i);
    }
}