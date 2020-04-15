package com.huk;

import com.huk.SongInfo;
import com.huk.services.SongTextSaver;
import com.huk.services.WebClient;
import com.huk.services.WebPageParser;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.IOException;


@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {

        final String url = "https://altwall.net/texts.php?show=oxxxymiron&number=177385";

        ConfigurableApplicationContext context = SpringApplication.run(Application.class,args);
        Document page = context.getBean(WebClient.class).getPage(url);
        SongInfo songInfo = context.getBean(WebPageParser.class).parsWebPage(page);
        context.getBean(SongTextSaver.class).saveFile(songInfo);
    }
}

