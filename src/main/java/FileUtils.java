package main.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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


    /**
     * e.g. input file contains:
     * 16 17 18 21 23 24 27 24
     * 74 76 79 81 82 85 85
     * And we want a list of a list of these integers.
     */
    public static List<List<Integer>> readFileAsListOfListOfInts(int dayNumber) {

        List<List<Integer>> listOfList = new ArrayList<>();

        List<String> inputLines = FileUtils.loadContent(dayNumber);

        for (String line : inputLines) {

            // remove all spaces
            String[] parsedLine = line.split("\\s+");

            List<Integer> integers = new ArrayList<>();

            for (String stringInt : parsedLine) {
                integers.add(Integer.valueOf(stringInt));
            }
            listOfList.add(integers);
        }

        return listOfList;
    }


    /**
     * Reads a text file and returns it as a single string concatenated line by line.
     */
    public static String readFileAsSingleString(int dayNumber) {
        List<String> textFileAsListOfString = loadContent(dayNumber);

        String textFileAsString = "";

        for (String string: textFileAsListOfString) {
            textFileAsString += string;
        }

        return textFileAsString;
     }


     public static List<String> extractPatternFromFileUsingRegex(int dayNumber, String regex) {
        String inputString = readFileAsSingleString(dayNumber);

         Pattern pattern = Pattern.compile(regex);
         Matcher matcher = pattern.matcher(inputString);

         List<String> extractedPattern = new ArrayList<>();

         while (matcher.find()) {
             extractedPattern.add(matcher.group());
         }

         return extractedPattern;
     }
}