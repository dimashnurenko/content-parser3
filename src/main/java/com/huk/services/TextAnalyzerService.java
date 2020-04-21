package com.huk.services;

import com.huk.SongInfo;
import com.huk.SongStatisticEntity;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component
//управляет компонентами
public class TextAnalyzerService {

    private final WebClient webClient;
    private final WebPageParser webPageParser;
    private final SongTextSaver songTextSaver;
    private final SongAnalyzer songAnalyzer;
    private final SongStatisticDao songStatisticDao;

    public TextAnalyzerService(WebClient webClient, WebPageParser webPageParser,
                               SongTextSaver songTextSaver, SongAnalyzer songAnalyzer,
                               SongStatisticDao songStatisticDao) {
        this.webClient = webClient;
        this.webPageParser = webPageParser;
        this.songTextSaver = songTextSaver;
        this.songAnalyzer = songAnalyzer;
        this.songStatisticDao = songStatisticDao;
    }

    public void startAnalyzer(String url) {
        Document page = webClient.getPage(url);
        SongInfo songInfo = webPageParser.parsWebPage(page);
        songTextSaver.saveFile(songInfo);
        SongStatisticEntity songStatisticEntity = songAnalyzer.analyzeSong(songInfo);
        songStatisticDao.create(songStatisticEntity);
    }
}
