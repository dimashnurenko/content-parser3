package com.huk.services;

import com.huk.SongInfo;
import org.jsoup.nodes.Document;
import java.util.List;

public interface PageParser {

    List<SongInfo> parsWebPage(Document page);//потомучто на одно й странице несколько песен

    String getTargetUrl();
}
