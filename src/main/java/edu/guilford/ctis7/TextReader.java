package edu.guilford.ctis7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/***
 * Loads in texts, counts frequencies
 */
public class TextReader {


    /***
     * Creates ArrayList of Strings, filters out words and pure numbers
     * @param filePath
     * @return
     */
    //has some weird results -- best--conscription turns into bestconscription, should modigy regex to fix this
    public static ArrayList<String> readFile(String filePath) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineWords = line.split("\\s+|--"); //asumes only way to split between words is either whitespace or --
                for (int i = 0; i < lineWords.length; i++) {
                    // Remove punctuation and convert to lowercase
                    lineWords[i] = lineWords[i].replaceAll("[^a-zA-Z]", "").toLowerCase();
                    // Filter out empty strings and pure numbers
                    if (lineWords[i].isEmpty() || lineWords[i].matches("\\d+")) {
                        //remove not by making null

                        lineWords[i] = null;

                    }
                }
                // Add non-null words to the list
                for (String word : lineWords) {
                    if (word != null) {
                        words.add(word);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }


    /***
      For a given ArrayList of Strings, return a hashmap of  <\String, Integer>
     * @param  words
     * @return HashMap<String, Integer> of word counts
     */
    public static HashMap<String, Integer> countWords(ArrayList<String> words) {
        HashMap<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        return wordCount;
    }

    /***
     * Creates an ArrayList of Word objects from a HashMap of word counts
     * @param wordCount
     * @return ArrayList<Word>
     */
    public static ArrayList<Word> createWordObjects(HashMap<String, Integer> wordCount) {
        ArrayList<Word> wordObjects = new ArrayList<>();
        for (String word : wordCount.keySet()) {
            wordObjects.add(new Word(word, wordCount.get(word)));
        }
        return wordObjects;
    }




}
