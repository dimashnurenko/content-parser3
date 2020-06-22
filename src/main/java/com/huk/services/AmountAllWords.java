package com.huk.services;

import com.huk.Word;
import com.huk.entities.SongStatisticEntity;
import com.huk.exception.TextAnalyzerServiceException;

import java.util.Map;

//пришла мапа, берем каждое слово, вызываем ГетАмоунт и смотрим сколько раз слово повторяется и сумируем
public class AmountAllWords implements WordFunction {
    @Override
    public SongStatisticEntity apply(Map<String, Word> stringWordMap,
                                     SongStatisticEntity songStatisticEntity) {
        if (songStatisticEntity == null) {
            throw new TextAnalyzerServiceException("SongStatisticEntity can't be null.");
        }
        if (stringWordMap == null) {
            throw new TextAnalyzerServiceException("Words map can't be null.");
        }
        Integer number = stringWordMap.values()
                                      .stream()
                                      .mapToInt(Word::getAmount)
                                      .sum();
        songStatisticEntity.setTotalWordsAmount(number);
        return songStatisticEntity;
    }
}
