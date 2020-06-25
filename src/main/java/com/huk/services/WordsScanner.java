package com.huk.services;

import com.huk.Word;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WordsScanner {

    private final String[] englishKeyWords = {"the", "of", "and", "a", "to"};
    private final String[] russianKeyWords = {"и", "в", "не", "на", "я"};

    public boolean hasEnglishWords(Map<String, Word> stringWordMap) {
        return lookForWords(stringWordMap, englishKeyWords);
    }

    public boolean hasRussianWords(Map<String, Word> stringWordMap) {
        return lookForWords(stringWordMap, russianKeyWords);
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
}
