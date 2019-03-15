package old;

import edu.duke.FileResource;
import edu.duke.URLResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GladLib {
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "resources/gladlib/data";
    private ArrayList<String> usedWords;
    private HashMap<String, ArrayList<String>> myMap;
    private Random myRandom;

    public GladLib() {
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }

    public GladLib(String source) {
        initializeFromSource(source);
        myRandom = new Random();
    }

    public static void main(String[] args) {
        GladLib gl = new GladLib();
        gl.makeStory();
    }

    private void initializeFromSource(String source) {
        ArrayList<String> adjectiveList = readIt(source + "/adjective.txt");
        ArrayList<String> nounList = readIt(source + "/noun.txt");
        ArrayList<String> colorList = readIt(source + "/color.txt");
        ArrayList<String> countryList = readIt(source + "/country.txt");
        ArrayList<String> nameList = readIt(source + "/name.txt");
        ArrayList<String> animalList = readIt(source + "/animal.txt");
        ArrayList<String> timeList = readIt(source + "/timeframe.txt");
        ArrayList<String> verbList = readIt(source + "/verb.txt");
        ArrayList<String> fruitList = readIt(source + "/fruit.txt");
        myMap = new HashMap<>();
        myMap.put("adjective", adjectiveList);
        myMap.put("noun", nounList);
        myMap.put("color", colorList);
        myMap.put("country", countryList);
        myMap.put("name", nameList);
        myMap.put("animal", animalList);
        myMap.put("timeframe", timeList);
        myMap.put("verb", verbList);
        myMap.put("fruit", fruitList);
        usedWords = new ArrayList<>();
    }

    private String randomFrom(ArrayList<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if (label.equals("number")) return "" + myRandom.nextInt(50) + 5;
        else if (myMap.containsKey(label)) return randomFrom(myMap.get(label));
        else return "**UNKNOWN**";
    }

    private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">", first);
        if (first == -1 || last == -1) {
            return w;
        }
        String prefix = w.substring(0, first);
        String suffix = w.substring(last + 1);
        String sub;
        do {
            sub = getSubstitute(w.substring(first + 1, last));
        } while (usedWords.contains(sub));
        usedWords.add(sub);
        return prefix + sub + suffix;
    }

    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for (String w : s.split("\\s+")) {
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w + " ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source) {
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String word : resource.words()) {
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source) {
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        } else {
            FileResource resource = new FileResource(source);
            for (String line : resource.lines()) {
                list.add(line);
            }
        }
        return list;
    }

    void totalWordsInMap() {
        int count = 0;
        for (Map.Entry<String, ArrayList<String>> e: myMap.entrySet()) {
           count += e.getValue().size();
        }
        System.out.println("Total words: " + count);
    }

    public void makeStory() {
        System.out.println("\n");
        String story = fromTemplate(dataSourceDirectory + "/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nwords replaced: " + usedWords.size());
        totalWordsInMap();
    }
}
