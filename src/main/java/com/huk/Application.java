package com.huk;

import com.huk.SongInfo;
import com.huk.services.*;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.io.IOException;


@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {

        final String url = "https://teksty-pesenok.ru/jason-gray/tekst-pesni-a-way-to-see-in-the-dark/751911/";

        ConfigurableApplicationContext context = SpringApplication.run(Application.class,args);

        context.getBean(TextAnalyzerService.class).startAnalyzer(url);
    }
}

