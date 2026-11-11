
import java.util.Scanner;

public class BankAccount {

    public class BankAccountClass {

        public String name() {return "";}
        public double deposit() {return 8.5;}
    }

    public class SavingsAccount extends BankAccountClass {

        // @Override 
        // public double deposit() {return 9.5;}

        public double addInterest() {
            return deposit() + 8.9;
        }

    }

    public void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name please");
        String name = scanner.nextLine();
        System.out.println("Deposit amount");
        String depoString = scanner.nextLine();
        double deposit = Double.parseDouble(depoString);
        SavingsAccount userAccount = new SavingsAccount();

    }

    public class InsufficientFundsException
            extends Exception {

        public InsufficientFundsException(String msg) {
            super(msg);
        }
    }

    public void withdraw(double amt)
            throws InsufficientFundsException {
        // if (amt > balance){

        // }
    }

}
