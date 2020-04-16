package com.huk.services;

import com.huk.SongInfo;
import com.huk.SongStatisticEntity;
import com.huk.Word;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SongAnalyzer {

    private List<WordFunction> wordFunctionList;

    public SongAnalyzer(List<WordFunction> wordFunctionList) {
        this.wordFunctionList = wordFunctionList;
    }

    public SongStatisticEntity analyzeSong(SongInfo songInfo) {
        SongStatisticEntity songStatisticEntity = new SongStatisticEntity();
        Map<String, Word> wordMap = getWords(songInfo);
        for (WordFunction wordFunction : wordFunctionList) {
            wordFunction.apply(wordMap, songStatisticEntity);
        }
        songStatisticEntity.setDate(Calendar.getInstance().getTime());
        songStatisticEntity.setTimestamp(System.currentTimeMillis());
        songStatisticEntity.setContentUrl(songInfo.getUrl());
        songStatisticEntity.setContent(String.join("\n", songInfo.getLines()));
        return songStatisticEntity;
    }

    private Map<String, Word> getWords(SongInfo songInfo) {// брать строчки с песни и раскладывать слова
        Map<String, Word> stringWordMap = new HashMap<>();
        for (String line : songInfo.getLines()) {
            line = line.toLowerCase(); // делаем маленькими буквами
            for (String word : line.split("[!?:\\-.,\\s]+")) {// из стоски выдергиваем слово
                if (word.equals("")) continue; //отсеиваем чтобы пустышки не считало словами
                if (stringWordMap.get(word) == null) {
                    stringWordMap.put(word, new Word(word));
                } else stringWordMap.get(word).count();
            }
        }
        return stringWordMap;
    }
}
