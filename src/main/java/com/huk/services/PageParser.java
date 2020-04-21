package com.huk.services;

import com.huk.SongInfo;
import org.jsoup.nodes.Document;

public interface PageParser {

    SongInfo parsWebPage(Document page);
}
