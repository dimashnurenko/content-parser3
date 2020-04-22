package com.huk.services;

import com.huk.SongInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
public class SongTextSaver {
    private String pathDirectory;

    public SongTextSaver(@Value("${local.file.path}") String pathDirectory) {
        this.pathDirectory = pathDirectory;
    }

    public void saveFile(List<SongInfo> songInfo) {
        for (SongInfo info : songInfo) {
            File file = new File(pathDirectory + info.getName() + ".txt");
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                for (String line : info.getLines()) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

