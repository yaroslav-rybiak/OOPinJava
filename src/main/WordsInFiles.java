import edu.duke.DirectoryResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class WordsInFiles {

    private HashMap<String, ArrayList<String>> fileMatcher;

    private WordsInFiles() {
        fileMatcher = new HashMap<>();
    }

    private void addWordsFromFile(File f) throws Exception {
        Scanner s = new Scanner(f);
        ArrayList<String> wordsFromFile = new ArrayList<>();
        while (s.hasNext()){
            wordsFromFile.add(s.next());
        }
        s.close();
        for(String word: wordsFromFile) {
            if(!fileMatcher.containsKey(word)) {
                fileMatcher.put(word, new ArrayList<>());
                fileMatcher.get(word).add(f.getName());
            } else {
                if(!fileMatcher.get(word).contains(f.getName())) {
                    fileMatcher.get(word).add(f.getName());
                }
            }
        }
    }

    private void buildWordFileMap() throws Exception {
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }

    public static void main(String[] args) throws Exception {
        WordsInFiles wif = new WordsInFiles();
        wif.buildWordFileMap();

        for(Entry<String, ArrayList<String>> e: wif.fileMatcher.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());

        }
    }

}
