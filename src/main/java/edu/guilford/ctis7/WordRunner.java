package edu.guilford.ctis7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/***
 * Main class, creates array of words from text file, counts the words, and creates a hashmap of the words and their counts, and creates Word array as well
 */
public class WordRunner {
    public static void main(String[] args) {


        ArrayList<String> allWordsFromText = TextReader.readFile("src/main/resources/edu/guilford/ctis7/book-war-and-peace.txt");

        allWordsFromText.sort(String::compareToIgnoreCase);
        HashMap<String, Integer> wordCount = TextReader.countWords(allWordsFromText);

        ArrayList<Word> wordObjects = TextReader.createWordObjects(wordCount);
        wordObjects.sort(Word::compareTo);

        FileWriterUtility.writeWordsToFile("output/words.txt", allWordsFromText);
        FileWriterUtility.writeWordsToFile("output/wordCount.txt", wordObjects);


        allWordsFromText = TextReader.readFile("src/main/resources/edu/guilford/ctis7/finneganswake.txt");
        allWordsFromText.sort(String::compareToIgnoreCase);
        wordCount = TextReader.countWords(allWordsFromText);
        wordObjects = TextReader.createWordObjects(wordCount);
        wordObjects.sort(Word::compareTo);

        FileWriterUtility.writeWordsToFile("output/finneganswords.txt", allWordsFromText); //finnegans wake text source had weird breakages, so some words end up getting split in two (after becomes af ter sometimes when theres a line break in between the halves)
        FileWriterUtility.writeWordsToFile("output/finneganswordcount.txt", wordObjects);
        //ask for word to search for from scanner-- search from created hashmap not Word object counter bc that would be inefficient
        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a word to search for: ");
            String searchWord = scanner.nextLine();
            if(searchWord.equals("exit")) {
                break;
            }

            if(!wordCount.containsKey(searchWord)) {
                System.out.println("The word " + searchWord + " does not appear in the text.");
            }
            else {
                System.out.println("The word " + searchWord + " appears " + wordCount.get(searchWord) + " times.");
            }
        }
    }
}