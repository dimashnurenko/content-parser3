package com.huk;

import java.util.List;

public class SongInfo {

    private String name;
    private List<String> lines;

    public SongInfo(String name, List<String> lines) {
        this.name = name;
        this.lines = lines;
    }

    public String getName() {
        return name;
    }

    public List<String> getLines() {
        return lines;
    }
}
