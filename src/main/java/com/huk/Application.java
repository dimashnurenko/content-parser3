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

        final String url = "https://altwall.net/texts.php?show=oxxxymiron&number=177385";

        ConfigurableApplicationContext context = SpringApplication.run(Application.class,args);
        Document page = context.getBean(WebClient.class).getPage(url);
        SongInfo songInfo = context.getBean(WebPageParser.class).parsWebPage(page);
        context.getBean(SongTextSaver.class).saveFile(songInfo);
        SongStatisticEntity songStatisticEntity = context.getBean(SongAnalyzer.class).analyzeSong(songInfo);
        context.getBean(SongStatisticDao.class).create(songStatisticEntity);

    }
}

