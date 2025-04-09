package edu.guilford.ctis7.Backend;

import javafx.application.Application;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class LargeTest {

    public static void main(String[] args) {
        // Create an instance of ApplicationInstance with a list of file paths

        // Create a list of file paths (for testing purposes)
        ArrayList<String> filePaths = new ArrayList<>();

        String parentDir = "/Users/ayaam/Downloads/archive/fiction/fiction";

        File[] files = new File(parentDir).listFiles();

        for(File file : files ) {
            filePaths.add(file.getAbsolutePath());
        }

        System.out.println(filePaths);
        System.out.println(files.length + " files were found.");

        ApplicationInstance instance = ApplicationInstance.getInstance();
        instance.setFilePaths(filePaths);
        instance.createAllWordsFromTexts();
        ArrayList<String> words = instance.getAllWordsFromTexts();

        System.out.println(words.size() + " words found.");

        instance.createTwoGram(); //can dumbly multithread this probably
        Map<String, Map<String, Integer>> map;
        map = instance.getTwoGram();
        System.out.println(map.size() + " two-grams found.");

        System.out.println(instance.generatePhrase(10, "the"));
//        filePaths.add("src/main/resources/test1.txt");
//        filePaths.add("src/main/resources/test2.txt");
//
//        // Set the file paths in the ApplicationInstance
//        appInstance.setSourceFilePaths(filePaths);
//
//        // Create all words from texts
//        appInstance.createAllWordsFromTexts();
//
//        // Create the words map
//        appInstance.createWordsMap();
//
//        // Create word frequencies
//        appInstance.createFrequencies();
//
//        // Generate a phrase
//        String generatedPhrase = appInstance.generatePhrase(5, "the");
//
//        // Print the generated phrase
//        System.out.println(generatedPhrase);
    }
}
