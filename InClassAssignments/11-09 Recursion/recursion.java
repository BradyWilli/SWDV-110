
public class recursion {

    public static void main(String[] args) {
        // mystery(3);
        // System.out.println(reverse("Leave"));
        // System.out.println(reverse("Oh captain, my captain!"));
        // System.out.println(isPalindrome("Leave"));
        
        // String testString = "Oopsie poopsie";
        // if (isPalindrome(testString)) {
        //     System.out.println(testString + " is a Palindrome!");
        // }else{
        //     System.out.println(testString + " not a Palindrome!");
        // }

        System.out.println(power(2, 10));

    }

    public static void mystery(int n) {
        if (n == 0) {
            System.out.println("Liftoff!");
            return;
        }
        System.out.println(n);
        mystery(n - 1);
    }

    public static long factorial(int n) {
        if (n <= 1) {
            return 1; // base case
        }
        return n * factorial(n - 1); // recursive case
    }
    long f = factorial(4);

    
    public static String reverse(String stringToReverse) {
        if (stringToReverse.length() <= 1) {
            return stringToReverse; // base case
        }
        return reverse(stringToReverse.substring(1)) + stringToReverse.charAt(0); // smaller problem
    }

    public static boolean isPalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        return isPalindrome(s.substring(1, s.length() - 1));
    }
        // reverse("java") -> "avaj" isPalindrome("racecar") -> true

        public static int power(int x, int y){
            if(y==1){
                return x;
            }
            return x * (power(x, y-1));
        }
}
