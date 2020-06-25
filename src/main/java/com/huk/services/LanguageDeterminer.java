package com.huk.services;

import com.huk.Word;
import com.huk.entities.SongStatisticEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.huk.enums.Language.*;

@Component
public class LanguageDeterminer implements WordFunction {

    private final WordsScanner wordsScanner;

    public LanguageDeterminer(WordsScanner wordsScanner) {
        this.wordsScanner = wordsScanner;
    }

    @Override
    public SongStatisticEntity apply(Map<String, Word> stringWordMap, SongStatisticEntity songStatisticEntity) {
        if (wordsScanner.hasEnglishWords(stringWordMap)) {
            songStatisticEntity.setLanguage(ENGLISH);
        } else if (wordsScanner.hasRussianWords(stringWordMap)) {
            songStatisticEntity.setLanguage(RUSSIAN);
        } else {
            songStatisticEntity.setLanguage(OTHER);
        }
        return songStatisticEntity;
    }
}
