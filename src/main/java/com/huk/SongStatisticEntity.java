package com.huk;

import com.huk.enums.Language;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "song_statistic")
public class SongStatisticEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    private Date date;
    @Column(name = "timestamp")
    private Long timestamp;
    @Column(name = "content_url")
    private String contentUrl;
    @Column(name = "content")
    private String content;
    @Column(name = "amount_same_words")
    private Integer amountSameWords;
    @Column(name = "total_words_amount")
    private Integer totalWordsAmount;
    @Column(name = "most_popular_words")
    private String mostPopularWords;
    @Column(name = "amount_unique_words")
    private Integer amountUniqueWords;
    @Enumerated(EnumType.STRING)
    private Language language;

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
