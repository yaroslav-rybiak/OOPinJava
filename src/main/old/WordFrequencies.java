package old;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word: fr.words()) {
            word = word.toLowerCase();
            if(!myWords.contains(word)) {
                myWords.add(word);
                myFreqs.add(1);
            } else {
                myFreqs.set(myWords.indexOf(word), myFreqs.get(myWords.indexOf(word)) + 1);
            }
        }

        for(String word: myWords) {
            System.out.println(word + " " + myFreqs.get(myWords.indexOf(word)));
        }
    }

    void findIndexOfMax() {
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > max) {
                max  = myFreqs.get(i);
                maxIndex = i;
            }
        }
        System.out.println("The word that occurs most often and its count are: '" + myWords.get(maxIndex) + "' " + max);
    }


    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.findUnique();
        System.out.println("Uniques words: " + wf.myWords.size());
        wf.findIndexOfMax();

    }

}
