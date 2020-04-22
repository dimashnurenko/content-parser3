package com.huk.services;

import com.huk.SongInfo;
import com.huk.exception.TextAnalyzerServiceException;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SongExtractor {

    private final List<PageParser> pageParserList;

    public SongExtractor(List<PageParser> pageParserList) {
        this.pageParserList = pageParserList;
    }

    public List<SongInfo> extractSong(Document webPage) {
        for (PageParser pageParser : pageParserList) {
            if (!webPage.location().contains(pageParser.getTargetUrl()))
                continue;
            return  pageParser.parsWebPage(webPage);
        }
        throw new TextAnalyzerServiceException("Web site not supported...");
    }
}
