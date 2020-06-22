package com.huk.config;

import com.huk.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@ComponentScan("com.huk.services")
public class AppConfiguration {

    @Bean
    public WordFunction amountAllWords() {
        return new AmountAllWords();
    }

    @Bean
    public WordFunction amountPopularWord() {
        return new AmountPopularWord();
    }

    @Bean
    public WordFunction amountSameWords() {
        return new AmountSameWords();
    }

    @Bean
    public WordFunction amountUniqueWords() {
        return new AmountUniqueWords();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
