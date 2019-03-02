public class BreakinCaesarCypher {

    private static void countEveryLetter(String phrase) {

        int[] counters = new int[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < phrase.length(); i++) {
            int index = alphabet.indexOf(Character.toLowerCase(phrase.charAt(i)));
            if(index != -1) {
                counters[index]++;
            }
        }

        for (int i = 0; i < counters.length; i++) {
            if(counters[i] != 0) {
                System.out.println(alphabet.charAt(i) + " " + counters[i]);
            }
        }
    }

    public static void main(String[] args) {
        countEveryLetter("Hello");
    }
}
