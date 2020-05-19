package com.huk.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.huk.enums.Language;

public class StatisticDto {
    private Long id;
    @JsonProperty("content_url")
    private String contentUrl;
    private String lyrics;
    @JsonProperty("amount_same_words")
    private Integer amountSameWords;
    @JsonProperty("total_words_amount")
    private Integer totalWordsAmount;
    @JsonProperty("most_popular_words")
    private String mostPopularWords;
    @JsonProperty("amount_unique_words")
    private Integer amountUniqueWords;
    private Language language;

    public String getContentUrl() {
        return contentUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public Integer getAmountSameWords() {
        return amountSameWords;
    }

    public void setAmountSameWords(Integer amountSameWords) {
        this.amountSameWords = amountSameWords;
    }

    public Integer getTotalWordsAmount() {
        return totalWordsAmount;
    }

    public void setTotalWordsAmount(Integer totalWordsAmount) {
        this.totalWordsAmount = totalWordsAmount;
    }

    public String getMostPopularWords() {
        return mostPopularWords;
    }

    public void setMostPopularWords(String mostPopularWords) {
        this.mostPopularWords = mostPopularWords;
    }

    public Integer getAmountUniqueWords() {
        return amountUniqueWords;
    }

    public void setAmountUniqueWords(Integer amountUniqueWords) {
        this.amountUniqueWords = amountUniqueWords;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
