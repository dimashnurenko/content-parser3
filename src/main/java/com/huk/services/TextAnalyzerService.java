package com.huk.services;

import com.huk.SongInfo;
import com.huk.entities.SongStatisticEntity;
import com.huk.services.dao.SongStatisticDao;
import org.jsoup.nodes.Document;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
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

    public List<SongStatisticEntity> startAnalyzer(List<String> urls) {
        //SecurityContextHolder.getContext().getAuthentication().getPrincipal();//это будет User
        List<SongStatisticEntity> entities = new ArrayList<>();
        for (String url : urls) {
            Document page = webClient.getPage(url);
            List<SongInfo> songInfo = songExtractor.extractSong(page);
            songTextSaver.saveFile(songInfo);
            List<SongStatisticEntity> songStatisticEntity = songAnalyzer.analyzeSong(songInfo);
            entities.addAll(songStatisticDao.createAll(songStatisticEntity));
        }
        return entities;
    }
}
