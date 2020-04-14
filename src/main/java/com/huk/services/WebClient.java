package com.huk.services;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;
import javax.swing.text.Document;
import java.io.IOException;

@Component
public class WebClient {
    public Document getPage(String url) throws IOException{

        return (Document) Jsoup.connect(url).get();
    }
}
