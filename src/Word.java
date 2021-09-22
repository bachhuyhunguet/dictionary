public class Word {
    Word() {
        this.word_target = "m";
        this.word_explain = "m";
    }

    private
        String word_target;
        String word_explain;

    public
    void setWord_target(String target) {
        word_target = target;
    }
    void setWord_explain(String explain) {
        word_explain = explain;
    }
    String getWord_target() {
        return  word_target;
    }
    String getWord_explain() {
        return  word_explain;
    }

}