package com.huk.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huk.entities.SongStatisticEntity;
import com.huk.services.dao.SongStatisticDao;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.StatusResultMatchers;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContentParserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Captor
    private ArgumentCaptor<List<SongStatisticEntity>> listSongStatisticEntityCaptor;

    @MockBean
    private SongStatisticDao songStatisticDao;

    @WithMockUser
    @Test
    public void statisticsEntityDataWillBeCalculated() throws Exception {
        CalculateStatisticsDto dto  = new CalculateStatisticsDto();
        dto.setUrls(Collections.singletonList("https://altwall.net/texts.php?show=janeair&number=138"));

        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonDto = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/statistics")
                                .contentType(APPLICATION_JSON)
                                .content(jsonDto))
               .andExpect(status().is2xxSuccessful());

        verify(songStatisticDao).createAll(listSongStatisticEntityCaptor.capture());

        final List<SongStatisticEntity> statisticEntities = listSongStatisticEntityCaptor.getValue();
        final SongStatisticEntity statisticEntity = statisticEntities.get(0);

        Assertions.assertEquals(164, statisticEntity.getAmountUniqueWords());
    }
}