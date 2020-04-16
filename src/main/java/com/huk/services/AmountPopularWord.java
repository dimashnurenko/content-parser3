package com.huk.services;

import com.huk.SongStatisticEntity;
import com.huk.Word;

import java.util.Map;

public class AmountPopularWord implements WordFunction {
    @Override
    public SongStatisticEntity apply(Map<String, Word> stringWordMap, SongStatisticEntity songStatisticEntity) {
        Word word = stringWordMap.values()
                         .stream()
                         .max(Word::compareTo)
                         .get();
        songStatisticEntity.setMostPopularWords(word.getWord());
        return songStatisticEntity;
    }
}
