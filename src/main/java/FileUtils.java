package main.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utilities for dealing with input files.
 */
public class FileUtils {

    private static final String FILE_PATH = "day";

    public static List<String> loadContent(int dayNumber) {
        String dayNumberString = String.format("%02d", dayNumber);
        String fileName = FILE_PATH + dayNumberString + ".txt";

        return readFile(fileName);
    }


    public static List<String> readFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + fileName));

            return reader.lines().collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
