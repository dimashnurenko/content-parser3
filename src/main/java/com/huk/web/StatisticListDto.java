package com.huk.web;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class StatisticListDto {

    private List<StatisticDto> content = new ArrayList<>();
    @JsonProperty("urls_count")
    private Integer urlsCount  = 0;

    public List<StatisticDto> getContent() {
        return content;
    }

    public void setContent(List<StatisticDto> content) {
        this.content = content;
    }

    public Integer getUrlsCount() {
        return urlsCount;
    }

    public void setUrlsCount(Integer urlsCount) {
        this.urlsCount = urlsCount;
    }
}
