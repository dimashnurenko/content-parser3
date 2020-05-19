package com.huk.web;

import com.huk.SongStatisticEntity;
import com.huk.services.TextAnalyzerService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Спринг видит эту анатацию и Спринг создает его положит в контекст и этот обьект будет как сервлет
@RestController
//на каком Url будет принимать запросы /statistics
@RequestMapping(value = "/statistics")
public class ContentParserController {

    private TextAnalyzerService textAnalyzerService;

    public ContentParserController(TextAnalyzerService textAnalyzerService) {
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
