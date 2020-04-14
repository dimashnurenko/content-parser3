package com.huk;

import com.huk.services.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        final String url = "https://altwall.net/texts.php?show=oxxxymiron&number=177385";

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        //context.getBean(LyricsProcessor.class).process(url);
    }
}
