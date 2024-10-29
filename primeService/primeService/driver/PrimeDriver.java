package primeService.driver;

import primeService.client.ClientDriver;
import primeService.server.ServerDriver;
import primeService.util.Debug;
import primeService.util.LogLevel;

import java.io.IOException;
import java.util.Scanner;

public class PrimeDriver {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // create a new Debug Instance
        Debug debug = Debug.getInstance();
        debug.setLogLevel(LogLevel.INFO);

        try {

            if (args.length != 1 || args[0].equals("${arg0}")) {
                debug.log("Expected 1 argument: <server || client>", LogLevel.ERROR);
                System.exit(0);
            }
            String run = args[0];

            if (run.equalsIgnoreCase("server")) {
                debug.log("Enter the port number for the server: ",  LogLevel.INFO);
                String port = scanner.nextLine();
                if (port != "") {
                    ServerDriver.runServer(port);
                }
            } else if (run.equalsIgnoreCase("client")) {
                debug.log("Enter the host for the client: ", LogLevel.INFO);
                String host = scanner.nextLine();
                debug.log("Enter the port number for the client: ", LogLevel.INFO);
                String port = scanner.nextLine();
                ClientDriver.runClient(host, port);
            } else {
                debug.log("Invalid argument: " + run, LogLevel.ERROR);
                debug.log("Please use 'server' or 'client'",  LogLevel.INFO);
                System.exit(0);
            }

        } catch (Exception e) {
           debug.log("An error occurred: " + e.getMessage(),  LogLevel.ERROR);
            System.exit(0);
        } finally {
            scanner.close();
        }
    }
}