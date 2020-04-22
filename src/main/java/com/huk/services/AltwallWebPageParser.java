package com.huk.services;

import com.huk.SongInfo;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AltwallWebPageParser implements PageParser {

    public List<SongInfo> parsWebPage(Document page){
        Elements elements = page.getElementsByTag("article");
        String songHtml = elements.first().html();
        String[] lines = songHtml.substring(3, songHtml.length() - 4).split("<br>");
        String nameSong = page.getElementsByTag("h1").first().text();
        String url = page.location();
        SongInfo songInfo = new SongInfo(nameSong, Arrays.asList(lines), url);

        return Arrays.asList(songInfo);
    }

    @Override
    public String getTargetUrl() {
        return "altwall.net";
    }
}
