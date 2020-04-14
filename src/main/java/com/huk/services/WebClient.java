package com.huk.services;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import javax.swing.text.Document;
import java.io.IOException;

@Component
public class WebClient {
    public Document getPage(String url) throws IOException {
//        try {
            return (Document) Jsoup.connect(url).get();
//        } catch (IOException e) {
//throw  new WebClientExeption("Cannot fetch Web page ", e);
//        }
    }
}
