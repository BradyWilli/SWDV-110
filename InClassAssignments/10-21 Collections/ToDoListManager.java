
import java.util.ArrayList;
import java.util.Scanner;



public class ToDoListManager {
    public static void main(String[] args) {

        ArrayList tasks = new ArrayList();
        Scanner scanner = new Scanner(System.in);
        boolean continueWithList = true;

        System.out.println("Add, Remove, List, Done, Quit");

        while (continueWithList = true) {
            String input = scanner.nextLine();
            switch(input){
                case "Add":
                    System.out.println("Enter a new task");
                    String addTask = scanner.nextLine();
                    tasks.add(addTask);
                    System.out.println(addTask + " added to list\nEnter next command");
                    input = scanner.nextLine();
                    break;
                case "Remove":
                    System.out.println("Enter a task to remove");
                    String removeTask = scanner.nextLine();
                    tasks.remove(removeTask);
                    System.out.println(removeTask + "removed\nEnter next command");
                    input = scanner.nextLine();
                    break;
                case "List":
                    System.out.println(tasks + "\nEnter next command");
                    input = scanner.nextLine();
                    break;
                case "Done":
                    //?????????
                    break;
                case "Quit":
                    continueWithList = false;
                    break;  
                default:
                    System.out.println("Unrecognized command. Please enter Add, Remove, List, Done or Quit");
                    input = scanner.nextLine();
            }

        }
        System.out.println("Goodbye");
        scanner.close();
    }
}
