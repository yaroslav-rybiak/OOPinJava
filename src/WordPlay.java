public class WordPlay {

    private static boolean isVowel(char c) {
        return "aieou".indexOf(Character.toLowerCase(c)) != -1;
    }

    private static String replaceVowels(String phrase) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            if (isVowel(phrase.charAt(i))) {
                sb.setCharAt(i, '*');
            }
        }
        return sb.toString();
    }

    private static String emphasize(String phrase, char c) {
        StringBuilder sb = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toLowerCase(phrase.charAt(i)) == Character.toLowerCase(c)) {
                if (i % 2 == 0) {
                    sb.setCharAt(i, '*');
                } else {
                    sb.setCharAt(i, '+');
                }
            }

        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(emphasize("dna ctgaaactga", 'a').equals("dn* ctg+*+ctg+"));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a').equals("M+ry Bell+ +br*c*d*br+"));
    }
}
