package com.example.solution;
public class Word {
    public Word() {
        this.word_target = "m";
        this.word_explain = "m";
    }

    public Word(String target, String explain) {
        this.word_target = target;
        this.word_explain = explain;
    }

    private
        String word_target;
        String word_explain;

    public void setWord_target(String target) {
        this.word_target = target;
    }
    public void setWord_explain(String explain) {
        this.word_explain = explain;
    }
    public String getWord_target() {
        return  word_target;
    }
    public String getWord_explain() {
        return  word_explain;
    }

}