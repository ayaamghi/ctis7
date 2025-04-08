package edu.guilford.ctis7.Backend;

import java.util.*;
import java.util.stream.Collectors;

public class ApplicationInstance {

    private ArrayList<String> allWordsFromTexts;

    private ArrayList<String> sourceFilePaths;

    TreeMap<String, ArrayList<Word>> wordsMap;
    HashMap<String, Integer> wordFrequencies;
    Map<String, Map<String, Integer>> twoGramMap;
    public ApplicationInstance(ArrayList<String> filePaths) {

        this.sourceFilePaths = filePaths;
    }


//ran profiler, saw this had performance issues, got parallelStream code from gpt
    public void createAllWordsFromTexts() {
        allWordsFromTexts = sourceFilePaths.parallelStream()
                .map(TextReader::readFile)
                .flatMap(List::stream)
                .collect(Collectors.toCollection(ArrayList::new)); //TODO this line could be opimized further according to profiler
    }

    public ArrayList<String> getAllWordsFromTexts() {
        return allWordsFromTexts;
    }

    public void createTwoMap() {
        wordsMap = TextReader.createTwoGram(allWordsFromTexts);
        }

        public void createTwoGram() {
        twoGramMap = TextReader.createTwoGrams(allWordsFromTexts); //TODO this line could be opimized further according to profiler
        }

        public Map<String, Map<String, Integer>> getTwoGram() {
        return twoGramMap;
        }


    public TreeMap<String, ArrayList<Word>> getWordsMap() {
        return wordsMap;
    }

    public void createFrequencies() {
       wordFrequencies = TextReader.countWords(allWordsFromTexts);
    }

    public int getWordFrequency(String word) {
        return wordFrequencies.getOrDefault(word, 0);
    }

    public String generatePhrase(int phraseLength, String phraseStart) {
        return WordGenerator.generatePhrase(wordsMap, phraseLength, phraseStart);
    }








}
