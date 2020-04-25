package com.huk.services;

import com.huk.SongStatisticEntity;
import com.huk.Word;
import com.huk.enums.Language;
import org.springframework.stereotype.Component;
import java.util.Map;
import static com.huk.enums.Language.*;

@Component
public class LanguageDeterminer implements WordFunction {

    private final String[] englishKeyWords = {"the", "of", "and", "a", "to"};
    private final String[] russianKeyWords = {"и", "в", "не", "на", "я"};


    @Override
    public SongStatisticEntity apply(Map<String, Word> stringWordMap, SongStatisticEntity songStatisticEntity) {
        if (isEnglish(stringWordMap)) {
            songStatisticEntity.setLanguage(ENGLISH);
        } else if (isRussian(stringWordMap)) {
            songStatisticEntity.setLanguage(RUSSIAN);
        } else {
            songStatisticEntity.setLanguage(OTHER);
        }
        return songStatisticEntity;
    }

    private boolean lookForWords(Map<String, Word> stringWordMap, String[] keyWords) {
        //берем каждое слово и сравниваем с ключевыми словами
        int counter = 0;
        for (String word : stringWordMap.keySet()) { // сет всех уникальных слов
            for (String keyWord : keyWords) { // каждое слово сравниваем с набором наших ключевых слов
                if (word.equalsIgnoreCase(keyWord)) {
                    counter = counter + stringWordMap.get(word)
                                                     .getAmount(); // сколько раз слово было использовано в тексте
                }
            }
        }
        return counter > 10;
    }

    private boolean isEnglish(Map<String, Word> stringWordMap) {
        return lookForWords(stringWordMap, englishKeyWords);
    }

    private boolean isRussian(Map<String, Word> stringWordMap) {
        return lookForWords(stringWordMap, russianKeyWords);
    }
}
