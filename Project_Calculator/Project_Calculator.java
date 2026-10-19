
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Project_Calculator {
        public static void main(String[] args) {
        System.out.println("Please enter a number");
        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.nextLine();

        double numberInput1 = Double.parseDouble(input1);

        System.out.println(numberInput1);

        System.out.println("Please enter a second number");
        String input2 = scanner.nextLine();
        scanner.close();

        double numberInput2 = Double.parseDouble(input2);
        double add = 0;
        double subtract = 0;
        double multiply = 0;
        double divide = 0;
        try {
            add = numberInput1 + numberInput2;
            subtract = numberInput1 - numberInput2;
            multiply = numberInput1 * numberInput2;
            divide = numberInput1 / numberInput2;
        } catch (Exception e) {
            System.out.println(e);
        }

        
        ArrayList<Double> math = new ArrayList<Double>();
        math.add(add);
        math.add(subtract);
        math.add(multiply);
        math.add(divide);
        
        for (int i = 0; i < math.size(); i++){
            System.out.println(math.get(i));
        }

        String calculatorOut = "CalculatorOutput.txt";
        try {
            PrintWriter out = new PrintWriter(
                new BufferedWriter(
                new FileWriter(calculatorOut)));
            out.println(math);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
