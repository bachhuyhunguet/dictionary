package com.example.solution;
public class Word {
    public Word() {
        this.word_target = "m";
        this.word_explain = "m";
    }

    private
        String word_target;
        String word_explain;

    public void setWord_target(String target) {
        word_target = target;
    }
    public void setWord_explain(String explain) {
        word_explain = explain;
    }
    public String getWord_target() {
        return  word_target;
    }
    public String getWord_explain() {
        return  word_explain;
    }

}