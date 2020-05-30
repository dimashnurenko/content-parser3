package com.huk.services;

import com.huk.entities.SongStatisticEntity;
import com.huk.Word;

import java.util.Map;

public interface WordFunction {
    SongStatisticEntity apply(Map<String, Word> stringWordMap, SongStatisticEntity songStatisticEntity);

}
