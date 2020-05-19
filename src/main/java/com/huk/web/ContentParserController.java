package com.huk.web;

import com.huk.SongStatisticEntity;
import com.huk.services.SongStatisticDao;
import com.huk.services.TextAnalyzerService;
import com.sun.xml.bind.v2.TODO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Спринг видит эту анатацию и Спринг создает его положит в контекст и этот обьект будет как сервлет
@RestController
//на каком Url будет принимать запросы /statistics
@RequestMapping(value = "/statistics")
public class ContentParserController {

    private SongStatisticDao songStatisticDao;

    private TextAnalyzerService textAnalyzerService;

    public ContentParserController(SongStatisticDao songStatisticDao,
                                   TextAnalyzerService textAnalyzerService) {
        this.songStatisticDao = songStatisticDao;
        this.textAnalyzerService = textAnalyzerService;
    }

    @PutMapping
    public StatisticListDto calculateStatistics(@RequestBody CalculateStatisticsDto dto) {
        //@RequestBody при вызове этого метда должен вытянуть из JSOn обьекта вытащить информацию привратить в джава
        // Обьект и передать в метод
        StatisticListDto statisticListDto = new StatisticListDto();
        List<SongStatisticEntity> songStatisticEntities = textAnalyzerService.startAnalyzer(dto.getUrls());
        statisticListDto.setUrlsCount(dto.getUrls().size());//количество Url пришедших
        for (SongStatisticEntity songStatisticEntity : songStatisticEntities) {
            StatisticDto statisticDto = convertToDto(songStatisticEntity);
            statisticListDto.addStatisticDto(statisticDto);
        }
        return statisticListDto;
    }

    @GetMapping(value = "/{statistic_id}")
    public StatisticDto getStatistics(@PathVariable("statistic_id") Long id) {
        SongStatisticEntity byId = songStatisticDao.getById(id);
        StatisticDto statisticDto = convertToDto(byId);
        return statisticDto;
    }


    private StatisticDto convertToDto(SongStatisticEntity entity) {
        StatisticDto statisticDto = new StatisticDto();
        statisticDto.setId(entity.getId());
        statisticDto.setContentUrl(entity.getContentUrl());
        statisticDto.setLyrics(entity.getContent());
        statisticDto.setAmountSameWords(entity.getAmountSameWords());
        statisticDto.setTotalWordsAmount(entity.getTotalWordsAmount());
        statisticDto.setMostPopularWords(entity.getMostPopularWords());
        statisticDto.setAmountUniqueWords(entity.getAmountUniqueWords());
        statisticDto.setLanguage(entity.getLanguage());
        return statisticDto;
    }
}
