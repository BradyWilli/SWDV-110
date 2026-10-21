
import java.util.ArrayList;
import java.util.Scanner;

public class LoginChecker {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username");
        String username = scanner.nextLine();

        System.out.println("Enter password");
        String password = scanner.nextLine();
        //int numberOfAttempts = 0;
        ArrayList<String> attempts = new ArrayList<>();
        while(!username.equals("admin") && !password.equals("Secret123")){
            attempts.add(username);
            System.out.println("Access Denied \n\nEnter Username");
            username = scanner.nextLine();

            System.out.println("Enter password");
            password = scanner.nextLine();

            //numberOfAttempts += 1;
        }
        System.out.println("Welcome\n");

        for (int i = 0; i < attempts.size(); i++){
            attempts.sort(null);
            System.out.println(attempts.get(i));
        }
        
        }
}
