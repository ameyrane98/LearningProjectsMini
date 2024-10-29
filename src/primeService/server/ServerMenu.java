package primeService.server;

import java.util.Map;
import java.util.Scanner;

public class ServerMenu {
    private final AllPrimeQueries allPrimeQueries;
    private final Scanner scanner;

    public ServerMenu(AllPrimeQueries allPrimeQueries) {
        this.allPrimeQueries = allPrimeQueries;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("[1] Client Name\n[2] All Client Queries\n[3] Quit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Client Name: ");
                    String clientName = scanner.next();
                    System.out.println(allPrimeQueries.getClientQueries(clientName));
                    break;
                case 2:
                    Map<String, String> queries = allPrimeQueries.getAllQueries();
                    queries.forEach((name, query) -> System.out.println(name + " " + query));
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
