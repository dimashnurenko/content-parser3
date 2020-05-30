package com.huk.services;

import com.huk.entities.SongStatisticEntity;
import com.huk.Word;

import java.util.Map;
//пришла мапа, берем каждое слово, вызываем ГетАмоунт и смотрим сколько раз слово повторяется и сумируем
public class AmountAllWords implements WordFunction {
    @Override
    public SongStatisticEntity apply(Map<String, Word> stringWordMap, SongStatisticEntity songStatisticEntity) {
        Integer number = stringWordMap.values()
                                                .stream()
                                                .mapToInt(Word::getAmount)
                                                .sum();
        songStatisticEntity.setTotalWordsAmount(number);
        return songStatisticEntity;
    }
}
