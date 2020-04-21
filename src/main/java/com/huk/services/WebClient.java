package com.huk.services;

import com.huk.exception.TextAnalyzerServiceException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebClient {
    public Document getPage(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (Exception e) {
            throw new TextAnalyzerServiceException("Error...", e);
        }
    }
}


