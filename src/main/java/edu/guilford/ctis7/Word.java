package edu.guilford.ctis7;

/***
 * Contains word and frequency
 */
public class Word implements Comparable<Word> {

    private String word;
    private int frequency;

    public Word(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }


    @Override
    public int compareTo(Word o) {
        //compare frequency, then compare alphabetical
        if (this.frequency != o.frequency) {
            if (this.frequency > o.frequency) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return this.word.compareTo(o.word);
        }
    }
    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}
