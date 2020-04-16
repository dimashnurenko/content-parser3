package com.huk.services;

import com.huk.SongStatisticEntity;
import com.huk.Word;

import java.util.Map;
//фильтруем те слова которые используются не больше 1го раза в тексте
public class AmountSameWords implements WordFunction{
    @Override
    public SongStatisticEntity apply(Map<String, Word> stringWordMap, SongStatisticEntity songStatisticEntity) {
        Integer number = (int) stringWordMap.values()
                           .stream()
                           .filter(word -> word.getAmount() > 1)
                           .count();
        songStatisticEntity.setAmountSameWords(number);
        return songStatisticEntity;
    }
}
