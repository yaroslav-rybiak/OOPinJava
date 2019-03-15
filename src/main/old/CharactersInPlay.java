package old;

import edu.duke.FileResource;

import java.util.ArrayList;

public class CharactersInPlay {

    static private ArrayList<String> names = new ArrayList<>();
    static private ArrayList<Integer> counts = new ArrayList<>();

    private void update(String name) {
        if (!names.contains(name)) {
            names.add(name);
            counts.add(1);
        } else {
            counts.set(names.indexOf(name), counts.get(names.indexOf(name)) + 1);
        }
    }

    boolean hasName(String line) {
        if (!line.contains(".")) return false;
        int pointIndex = line.indexOf(".");
        return pointIndex != line.length() - 1;
    }

    String getName(String line) {
        if (hasName(line))
            return line.substring(0, line.indexOf("."));
        else return null;
    }

    void findAllCharacters() {
        FileResource fr = new FileResource("resources/macbethSmall.txt");
        for (String line : fr.lines()) {
            if (hasName(line)) {
                update(getName(line));
            }
        }
    }

    public void charactersWithNumParts(int min, int max) {
        ArrayList<String> minimaxNnames = new ArrayList<>();
        for (int i = 0; i < counts.size(); i++) {
            if (counts.get(i) >= min && counts.get(i) <= max) {
                minimaxNnames.add(names.get(i));
            }
        }
        for (String name : minimaxNnames) {
            System.out.println(name);
        }
    }
}
