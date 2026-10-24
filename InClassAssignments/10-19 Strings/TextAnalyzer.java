import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class TextAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a sentence");
        String sentence = scanner.nextLine();

        int words = sentence.split("\\s").length, letters =0, digits = 0, vowels = 0;
        String longest = Arrays.stream(sentence.split(" "))
                .max(Comparator.comparingInt(String::length))
                .orElse(null);

        for(int i = 0; i < sentence.length(); i++){
            char ch = sentence.charAt(i);
            if (Character.isLetter(ch)){
                letters++;
                char lower = Character.toLowerCase(ch);
                if("aeiou".indexOf(lower) != -1){
                    vowels++;
                }
            }else if(Character.isDigit(ch)){
                digits++;
            }
        }
        String outputString = """
                words: %d
                letters: %d
                digits: %d
                vowels: %d
                """.formatted(words, letters, digits, vowels);
        System.out.println(outputString);
        System.out.println(longest);
    }
}
