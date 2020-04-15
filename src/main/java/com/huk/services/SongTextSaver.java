package com.huk.services;

import com.huk.SongInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.*;

@Component
public class SongTextSaver {
    private String pathDirectory;

    public SongTextSaver(@Value("${local.file.path}") String pathDirectory) {
        this.pathDirectory = pathDirectory;
    }

    public void saveFile(SongInfo songInfo) {
        File file = new File(pathDirectory + songInfo.getName() + ".txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            for (String line : songInfo.getLines()) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

