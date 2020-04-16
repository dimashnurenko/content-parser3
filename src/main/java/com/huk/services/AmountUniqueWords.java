package com.huk.services;

import com.huk.SongStatisticEntity;
import com.huk.Word;

import java.util.Map;
//приходит мапа где псчитаные слова, получаем значениея, вызываем СТРИАМ, делаем фильтр
//считаем те слова которые использыются только 1 раз в тексте
public class AmountUniqueWords implements WordFunction {
    @Override
    public SongStatisticEntity apply(Map<String, Word> stringWordMap, SongStatisticEntity songStatisticEntity) {
        Integer number = (int) stringWordMap.values()
                           .stream()
                           .filter(word -> word.getAmount() == 1)
                           .count();
        songStatisticEntity.setAmountUniqueWords(number);
        return songStatisticEntity;
    }
}
