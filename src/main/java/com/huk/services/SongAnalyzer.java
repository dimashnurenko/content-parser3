package com.huk.services;

import com.huk.SongInfo;
import com.huk.entities.SongStatisticEntity;
import com.huk.Word;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SongAnalyzer {

    private List<WordFunction> wordFunctionList;

    public SongAnalyzer(List<WordFunction> wordFunctionList) {
        this.wordFunctionList = wordFunctionList;
    }

    public List<SongStatisticEntity> analyzeSong(List<SongInfo> songInfo) {
        List<SongStatisticEntity> songStatisticEntities = new ArrayList<>();//будем хранить сонг статистик ентити
        for (SongInfo info : songInfo) {
            SongStatisticEntity songStatisticEntity = new SongStatisticEntity();
            Map<String, Word> wordMap = getWords(info);
            for (WordFunction wordFunction : wordFunctionList) {
                wordFunction.apply(wordMap, songStatisticEntity);
            }
            songStatisticEntity.setDate(Calendar.getInstance().getTime());
            songStatisticEntity.setTimestamp(System.currentTimeMillis());
            songStatisticEntity.setContentUrl(info.getUrl());
            songStatisticEntity.setContent(String.join("\n", info.getLines()));
            songStatisticEntities.add(songStatisticEntity);
        }
        return songStatisticEntities;
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
