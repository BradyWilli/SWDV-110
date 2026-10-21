

public class Strings {
    public static void main(String[] args) {
    String a = "java";
    String b = "java";
    String c = new String("java");

    System.out.println(a == b);
    System.out.println(a == c);
    System.out.println(a.equals(c));

    a.toUpperCase();
    System.out.println(a);

    String greeting = "Hello, Java";
    System.out.println(greeting);
    greeting.length(); // 11 – number of characters
    greeting.charAt(0); // 'H' – first index is 0
    greeting.charAt(7); // 'J'
    greeting.charAt(11); // StringIndexOutOfBoundsException
    System.out.println((char) greeting.length());
    System.out.printf("%s \n", greeting.charAt(0));

    char septimus = greeting.charAt(6);
    System.out.printf("%s \n", septimus);

    char eleventh = greeting.charAt(greeting.length() -1);
    System.out.printf("%s \n", eleventh);

    String empty = " ";
    boolean isEmptyOne = empty.equals("");
    boolean isEmptTwo = empty.isEmpty(); //there are corner cases where these would be different
        GetSubstring();
    }

    private static  void GetSubstring(){
        String emailAdress = "ada.lovelace@someotherdomain.com";

        int atMarkIndex = emailAdress.indexOf('@');
        String username = emailAdress.substring(0, atMarkIndex);
        System.out.println("Username: "+ username);

        String secondString = emailAdress.substring(emailAdress.indexOf('.'));
        String topLevelDomain = secondString.substring(secondString.indexOf('.'));

        System.out.println(topLevelDomain);
    }
}
