package edu.guilford.ctis7.Backend;

import java.util.ArrayList;
import java.util.TreeMap;

public class WordGenerator {


    /***
     * Recursively generates phrase of given length using getWord internally
     * @param map
     * @param phraseLength
     * @param phraseStart
     * @return String
     * @see #getWord(TreeMap, String)
     */
    public static String generatePhrase(TreeMap<String, ArrayList<Word>> map, int phraseLength, String phraseStart) {
        StringBuilder phrase = new StringBuilder();

        if (phraseLength == 0) {
            return getWord(map, phraseStart).getWord();
        } else {
            phraseLength--;
            phrase.append(phraseStart).append(" ");
            phrase.append(generatePhrase(map, phraseLength, getWord(map, phraseStart).getWord()));
        }


        return phrase.toString();
    }

    public static Word getWord(TreeMap<String, ArrayList<Word>> map, String key) {
        if (!map.containsKey(key) || map.get(key).isEmpty()) {
            return null;
        }
        int totalFrequency = 0;
        for (Word w : map.get(key)) {
            totalFrequency += w.getFrequency();
        }

        int random = (int) (Math.random() * totalFrequency) + 1;
        for (Word w : map.get(key)) {
            random -= w.getFrequency();
            if (random <= 0) {
                return w;
            }
        }
        return null; // fallback
    }}
