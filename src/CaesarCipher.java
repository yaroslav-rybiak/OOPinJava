public class CaesarCipher {

    private static String encrypt(String input, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String newAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (alphabet.indexOf(currentChar) != -1) {
                sb.setCharAt(i, newAlphabet.charAt(alphabet.indexOf(currentChar)));
            }
            if (alphabet.toLowerCase().indexOf(currentChar) != -1) {
                sb.setCharAt(i, newAlphabet.toLowerCase().charAt(alphabet.toLowerCase().indexOf(Character.toLowerCase(currentChar))));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("FIRST LEGION ATTACK EAST FLANK!", 23).equals("CFOPQ IBDFLK XQQXZH BXPQ CIXKH!"));
        System.out.println(encrypt("First Legion", 23).equals("Cfopq Ibdflk"));
        System.out.println(encrypt("First Legion", 17).equals("Wzijk Cvxzfe"));
    }
}
