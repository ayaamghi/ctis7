package edu.guilford.ctis7;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/***
 * A utility to write ArrayLists to files
 */
public class FileWriterUtility {


    //generics abuse

    /***
     * Write ArrayList containig any object to a file
     * @param filePath
     * @param wordObjects
     * @param <T>
     */
    public static <T> void writeWordsToFile(String filePath, ArrayList<T> wordObjects) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Object word : wordObjects) {
                fileWriter.write(word + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
