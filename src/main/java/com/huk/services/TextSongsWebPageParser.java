package com.huk.services;

import com.huk.SongInfo;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Component

public class TextSongsWebPageParser implements  PageParser {

    @Override
    public SongInfo parsWebPage(Document page) {
        return null;
    }
}
