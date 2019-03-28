package algorythms;

public class LinearSearch {

   static Integer finder(String[] wordsAarray, String word) {
       for(int i = 0; i < wordsAarray.length; i++) {
           if(wordsAarray[i].equals(word)) {
               return i;
           } else {
               System.out.println("Found " + wordsAarray[i]);
           }
       }
       return null;
   }


    public static void main(String[] args) {
        String[] dicti = new String[5];
        dicti[0] = "aaa";
        dicti[1] = "bbb";
        dicti[2] = "ccc";
        dicti[3] = "ddd";
        dicti[4] = "eee";

        System.out.println(finder(dicti, "aaa"));
        System.out.println(finder(dicti, "ccc"));
        System.out.println(finder(dicti, "fff"));

    }
}
