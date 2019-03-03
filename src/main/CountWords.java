import java.util.HashMap;
import java.util.Map;

class CountWords {

    static Map<String, Integer> countWords(String text) {
        String[] words = text.toLowerCase().split("\\W+");
        Map<String, Integer> counter = new HashMap<>();
        for (String word : words) {
            if (counter.containsKey(word)) {
                counter.put(word, counter.get(word) + 1);
            } else {
                counter.put(word, 1);
            }
        }
        return counter;
    }
}
