import java.util.*;


public class Review {
    public static void main(String[] args){
        int max = 2147483647;
        int min = -2147483648;

        float decimal = 3.14465f;
        double bigDecimal = 25833.489d;

        String text = "epic words";
        char singleCharacter = 'W';

        boolean trueFalse = true;
        boolean falseTrue = false;

        if (trueFalse != falseTrue){
            System.out.println("The values are not equal!");
        }

        //chapter 9 file input output
        //import java.util
        
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        for (int number : numbers){
            System.out.println(number);
        }
    }
}
