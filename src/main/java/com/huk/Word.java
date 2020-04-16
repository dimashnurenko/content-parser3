package com.huk;

public class Word implements Comparable<Word> { // за отдельное слово в песне

    private  String word;
    private  Integer amount = 0;

    public Word(String word) {
        this.word = word;
        amount++; // увеличится
    }

    public void count(){
        amount++;
    }

    public String getWord() {
        return word;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Word word) {
        return amount.compareTo(word.amount);//сравнение одного верда с дургим
    }
}
