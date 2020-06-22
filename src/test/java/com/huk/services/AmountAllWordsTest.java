package com.huk.services;

import com.huk.Word;
import com.huk.entities.SongStatisticEntity;
import com.huk.exception.TextAnalyzerServiceException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;


public class AmountAllWordsTest {

    private final WordFunction wordFunction = new AmountAllWords();

    @Test
    public void totalWordsAmountWillBeCalculated() {
        Map<String, Word> wordsMap = new HashMap<>();
        wordsMap.put("str1", new Word("str1"));
        wordsMap.put("str2", new Word("str2"));

        SongStatisticEntity statisticEntity = new SongStatisticEntity();

        wordFunction.apply(wordsMap, statisticEntity);

        Assertions.assertEquals(statisticEntity.getTotalWordsAmount(), 2);
    }

    @Test
    public void zeroWillBeReturnedWhenWordsMapIsEmpty() {
        Map<String, Word> wordsMap = new HashMap<>();

        SongStatisticEntity statisticEntity = new SongStatisticEntity();

        wordFunction.apply(wordsMap, statisticEntity);

        Assertions.assertEquals(statisticEntity.getTotalWordsAmount(), 0);
    }

    @Test(expected = TextAnalyzerServiceException.class)
    public void runtimeExceptionWillBeThrownWhenWordsMapIsNull() {
        SongStatisticEntity statisticEntity = new SongStatisticEntity();

        wordFunction.apply(null, statisticEntity);
    }

    @Test(expected = TextAnalyzerServiceException.class)
    public void runtimeExceptionWillBeThrownWhenSongStatisticEntityIsNull() {
        wordFunction.apply(new HashMap<>(), null);
    }
}
