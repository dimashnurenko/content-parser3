package com.huk.services;

import com.huk.SongInfo;
import com.huk.SongStatisticEntity;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//управляет компонентами
public class TextAnalyzerService {

    private final WebClient webClient;
    private final SongExtractor songExtractor;
    private final SongTextSaver songTextSaver;
    private final SongAnalyzer songAnalyzer;
    private final SongStatisticDao songStatisticDao;

    public TextAnalyzerService(WebClient webClient, SongExtractor songExtractor,
                               SongTextSaver songTextSaver, SongAnalyzer songAnalyzer,
                               SongStatisticDao songStatisticDao) {
        this.webClient = webClient;
        this.songExtractor = songExtractor;
        this.songTextSaver = songTextSaver;
        this.songAnalyzer = songAnalyzer;
        this.songStatisticDao = songStatisticDao;
    }

    public void startAnalyzer(String url) {
        Document page = webClient.getPage(url);
        List<SongInfo> songInfo = songExtractor.extractSong(page);
        songTextSaver.saveFile(songInfo);
        List<SongStatisticEntity> songStatisticEntity = songAnalyzer.analyzeSong(songInfo);
        songStatisticDao.createAll(songStatisticEntity);
    }
}
