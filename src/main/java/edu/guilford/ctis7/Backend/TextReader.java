package edu.guilford.ctis7.Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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

    // Instead of TreeMap<String, ArrayList<Word>>,
// use a Map<String, Map<String, Integer>>
// (where the inner map is <"word", frequency>).
    public static Map<String, Map<String, Integer>> createTwoGrams(List<String> words) {
        Map<String, Map<String, Integer>> twoGram = new HashMap<>();

        for (int i = 0; i < words.size() - 1; i++) {
            String firstWord = words.get(i);
            String secondWord = words.get(i + 1);

            // Create inner map if needed
            twoGram.putIfAbsent(firstWord, new HashMap<>());

            // Get the inner frequency map
            Map<String, Integer> frequencyMap = twoGram.get(firstWord);

            // Increment frequency
            //TODO majority of time is at this line
            frequencyMap.put(secondWord, frequencyMap.getOrDefault(secondWord, 0) + 1);
        }

        return twoGram;
    }

    /***
     * Creates a TreeMap of two-grams from an ArrayList of String words
     * @param words
     * @return
     */
    public static TreeMap<String, ArrayList<Word>> createTwoGram(ArrayList<String> words) {


        TreeMap<String, ArrayList<Word>> twoGram = new TreeMap<>();
        for( int i = 0; i < words.size() - 1; i++) {

            String firstWord = words.get(i);
            Word secondWord = new Word(words.get(i + 1), 0);


            if(!twoGram.containsKey(firstWord)) {
                twoGram.put(firstWord, twoGram.getOrDefault(firstWord, new ArrayList<>()));
            }
            //TODO contains() is slow, should be replaced with a hashmap
            if(!twoGram.get(firstWord).contains(secondWord)) {
                twoGram.get(firstWord).add(secondWord);
            }
            //if the word already exists, increment the frequency
            //TODO this is slow, should be replaced with a hashmap
            twoGram.get(firstWord).get(twoGram.get(firstWord).indexOf(secondWord)).setFrequency(twoGram.get(firstWord).get(twoGram.get(firstWord).indexOf(secondWord)).getFrequency() + 1);

        }
        return twoGram;

    }




}
