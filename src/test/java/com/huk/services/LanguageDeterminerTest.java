package com.huk.services;

import com.huk.Word;
import com.huk.entities.SongStatisticEntity;
import com.huk.enums.Language;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

@RunWith(MockitoJUnitRunner.class)
public class LanguageDeterminerTest {

    @Mock
    private WordsScanner wordsScanner;

    @InjectMocks
    private LanguageDeterminer determiner;

    @Test
    public void englishLanguageWillBeDetermined() {
        final HashMap<String, Word> stringWordMap = new HashMap<>();
        Mockito.when(wordsScanner.hasEnglishWords(stringWordMap)).thenReturn(true);

        final SongStatisticEntity songStatisticEntity = new SongStatisticEntity();
        determiner.apply(stringWordMap, songStatisticEntity);

        Mockito.verify(wordsScanner).hasEnglishWords(stringWordMap);
        Assertions.assertEquals(songStatisticEntity.getLanguage(), Language.ENGLISH);
    }

    @Test
    public void russianLanguageWillBeDetermined() {
        final HashMap<String, Word> stringWordMap = new HashMap<>();
        Mockito.when(wordsScanner.hasRussianWords(stringWordMap)).thenReturn(true);
        Mockito.when(wordsScanner.hasEnglishWords(stringWordMap)).thenReturn(false);

        final SongStatisticEntity songStatisticEntity = new SongStatisticEntity();
        determiner.apply(stringWordMap, songStatisticEntity);

        Mockito.verify(wordsScanner).hasEnglishWords(stringWordMap);
        Mockito.verify(wordsScanner).hasRussianWords(stringWordMap);
        Assertions.assertEquals(songStatisticEntity.getLanguage(), Language.RUSSIAN);
    }

    @Test
    public void otherLanguageWillBeDetermined() {
        final HashMap<String, Word> stringWordMap = new HashMap<>();
        Mockito.when(wordsScanner.hasRussianWords(stringWordMap)).thenReturn(false);
        Mockito.when(wordsScanner.hasEnglishWords(stringWordMap)).thenReturn(false);

        final SongStatisticEntity songStatisticEntity = new SongStatisticEntity();
        determiner.apply(stringWordMap, songStatisticEntity);

        Mockito.verify(wordsScanner).hasEnglishWords(stringWordMap);
        Mockito.verify(wordsScanner).hasRussianWords(stringWordMap);
        Assertions.assertEquals(songStatisticEntity.getLanguage(), Language.OTHER);
    }
}
