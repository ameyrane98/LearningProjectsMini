package primeService.client;

import primeService.socket.PrimeClientWorker;
import primeService.util.Debug;
import primeService.util.LogLevel;

import java.io.IOException;
import java.util.Scanner;

public class ClientMenu {
    private final PrimeClientWorker clientWorker;
    private final Scanner scanner;

    public ClientMenu(PrimeClientWorker clientWorker) {
        this.clientWorker = clientWorker;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        
        // create a new Debug Instance
        Debug debug = Debug.getInstance();
        debug.setLogLevel(LogLevel.INFO);

        String clientName = "";
        while (true) {
            debug.log("[1] Set client name\n[2] Enter number to query for prime\n[3] What is the server response?\n[4] Quit", LogLevel.INFO);
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    debug.log("Enter Client Name: ", LogLevel.INFO);
                    clientName = scanner.next();
                    clientWorker.setClientName(clientName);
                    break;
                case 2:
                    debug.log("Enter a number: ", LogLevel.INFO);
                    int number = scanner.nextInt();
                    clientWorker.sendPrimeQuery(number);
                    break;
                case 3:
                    try {
                        String response = clientWorker.receiveResponse();
                        debug.log("Server response: " + response, LogLevel.INFO);
                    } catch (IOException e) {
                       debug.log("Error receiving response from server.", LogLevel.ERROR);
                    }
                    break;
                case 4:
                    return;
                default:
                    debug.log("Invalid choice. Please try again.", LogLevel.WARN);
            }
        }
    }
}
