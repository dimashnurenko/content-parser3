package com.huk.web;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/statistics")
public class ContentParserController {

    @PutMapping
    public StatisticListDto calculateStatistics(@RequestBody CalculateStatisticsDto dto){
        System.out.println("Put...");
        // TODO: 14.05.2020  TextAnalyzerService  
    }

}
