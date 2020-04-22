package com.huk.services;

import com.huk.SongInfo;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@Component
public class TekstyPesenokWebPageParser implements PageParser {

    @Override
    public List<SongInfo> parsWebPage(Document webPage) {
        List<String> originalText = new ArrayList<>();
        List<String> translatedText = new ArrayList<>();
        List<String> titles = new ArrayList<>();

        Elements originalElements = extractInfo(webPage,
                                                page -> page.getElementsByClass("songTextTrans songText"));
        originalText.addAll(elementsToList(originalElements));
        Elements translatedElements = extractInfo(webPage,
                                                  page -> page.getElementsByClass("songTextTrans songTrans"));
        translatedText.addAll(elementsToList(translatedElements));
        Elements titlesElements = extractInfo(webPage,
                                              page -> page.getElementsByTag("h1"));
        titles.addAll(elementsToList(titlesElements));
        SongInfo originalSongInfo = new SongInfo(titles.get(0), originalText, webPage.location());
        SongInfo translatedSongInfo = new SongInfo(titles.get(1), translatedText, webPage.location());

        return Arrays.asList(originalSongInfo, translatedSongInfo);
    }

    //нужен для того чтобы определить какой парсер использовать
    @Override
    public String getTargetUrl() {
        return "teksty-pesenok.ru";
    }

    private Elements extractInfo(Document wepPage, Function<Document, Elements> function) {
        return function.apply(wepPage);//применить функцию
    }

    private List<String> elementsToList(Elements elements) {
        List<String> stringList = new ArrayList<>();
        for (Element element : elements) {
            stringList.add(element.text());
        }
        return stringList;
    }
}
