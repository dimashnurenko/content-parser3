package com.huk.services;

import com.huk.SongInfo;
import com.huk.Word;
import com.huk.entities.SongStatisticEntity;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.verify;

public class SongAnalyzerTest {

    @Captor
    private ArgumentCaptor<Map<String, Word>> mapCaptor;
    @Captor
    private ArgumentCaptor<SongStatisticEntity> songStatisticEntityCaptor;

    @Mock
    private WordFunction wordFunction;

    @Test
    public void statisticsEntityDataWillBeSetWhenWordFunctionListIsNotEmpty() {
        final SongAnalyzer songAnalyzer = new SongAnalyzer(singletonList(wordFunction));

        final SongInfo songInfo = new SongInfo("name", singletonList("line"), "url");

        final List<SongStatisticEntity> entities = songAnalyzer.analyzeSong(singletonList(songInfo));


        verify(wordFunction).apply(anyMap(), songStatisticEntityCaptor.capture());

        final Map<String, Word> value = mapCaptor.getValue();
        Assertions.assertEquals(1, value.size());
        Assertions.assertEquals(new Word("s"), value.get("s"));
//        final SongStatisticEntity entity = songStatisticEntityCaptor.getValue();
    }

}