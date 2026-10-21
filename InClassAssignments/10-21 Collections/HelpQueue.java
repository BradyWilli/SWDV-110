import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class HelpQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a command");
        String command = scanner.nextLine();
        command = command.toLowerCase();

        String ticketName = "";
        
        Deque<String> tickets = new ArrayDeque<>();
            tickets.offer("Make java coffee");
            //tickets.poll(); // "Ana" leaves first
            //tickets.peek(); // "Kai" – next up
            
        Deque<String> resolvedTickets = new ArrayDeque<>();
            //undo.push(ticket);
        while (true) {
            switch (command) {
                case "new":
                    System.out.println("enter ticket name");
                    ticketName = scanner.nextLine();
                    tickets.offer(ticketName);
                    break;
                case "next":
                    resolvedTickets.push(tickets.poll());
                    tickets.poll();
                    if (tickets.isEmpty()) {
                        System.out.println("No tickets left! YIPEEEE");
                    }
                    break;
                case "peek":
                    tickets.peek();
                    if (tickets.isEmpty()) {
                        System.out.println("No tickets left! YIPEEEE");
                    }
                    break;
                case "count":
                    System.out.println("You have " + tickets.size() + " tickets left");
                    break;
                case "undo":
                    tickets.offer(resolvedTickets.pop());
                    resolvedTickets.pop();
                    break;
                default:
                    System.out.println("bad entry");
                    break;
            }
        }
    }
}
