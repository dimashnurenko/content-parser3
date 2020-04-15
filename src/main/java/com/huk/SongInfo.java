package com.huk;

import java.util.List;

public class SongInfo {

    private String name;
    private List<String> lines;
    private String url;

    public SongInfo(String name, List<String> lines, String url) {
        this.name = name;
        this.lines = lines;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public List<String> getLines() {
        return lines;
    }

    public String getUrl() {
        return url;
    }
}

