package com.huk.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebClient {
    public Document getPage(String url) throws IOException {

            return Jsoup.connect(url).get();
        }
    }

