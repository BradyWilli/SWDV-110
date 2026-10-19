
import java.util.Scanner;

public class NameAndEmailParser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name");
        
        String name = scanner.nextLine();
        int space = name.indexOf(" ");
        
        String firstName = name.substring(0, space);
        String middleName = name.substring(space, name.lastIndexOf(" ") + 1 );
        String lastName = name.substring(name.lastIndexOf(" ") + 1);

        String firstInit = name.substring(0, 1);
        //String midInit = name.substring(space, 1);
        //String lastInit = name.substring(name.lastIndexOf(" ") + 1);

        System.out.println(firstName + "\n" + middleName + "\n"+ lastName);
        System.out.println(firstInit); //+ "\n" + midInit + "\n" + lastInit);
    }
    
}
