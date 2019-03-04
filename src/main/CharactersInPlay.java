import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {

    static private ArrayList<String> names = new ArrayList<>();
    static private ArrayList<Integer> counts = new ArrayList<>();

    static void update(String name) {
        if(!names.contains(name)) {
            names.add(name);
            counts.add(1);
        } else {
            counts.set(names.indexOf(name), counts.get(names.indexOf(name)) + 1);
        }
    }

    static boolean hasName(String line) {
        if(!line.contains(".")) return false;
        int pointIndex = line.indexOf(".");
        if(pointIndex == line.length() - 1) return false;

        return true;
    }

    static String getName(String line) {
        return line.substring(0, line.indexOf("."));
    }

    static void findAllCharacters() {
        FileResource fr = new FileResource("resources/macbethSmall.txt");
        for (String line : fr.lines()) {
            if (hasName(line)) {
                update(getName(line));
            }
        }
    }

    public static void charactersWithNumParts(int min, int max) {
        ArrayList<String> minimaxNnames = new ArrayList<>();
        for (int i = 0; i < counts.size(); i ++) {
            if(counts.get(i) >= min && counts.get(i) <= max) {
                minimaxNnames.add(names.get(i));
            }
        }
        for (String name: minimaxNnames) {
            System.out.println(name);
        }
    }

    public static void main(String[] args) {
        findAllCharacters();
//        for(int i = 0; i < names.size(); i ++) {
//            if (counts.get(i) > 100)
//            System.out.println(names.get(i) + " " + counts.get(i));
//        }
        charactersWithNumParts(10, 15);
    }
}
