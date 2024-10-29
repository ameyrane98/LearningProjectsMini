package primeService.server;

import primeService.socket.PrimeServerSocket;
import primeService.util.Debug;
import primeService.util.LogLevel;

public class ServerDriver {

    public static void runServer(String portNumber) {

        // create a new Debug Instance
        Debug debug = Debug.getInstance();
        debug.setLogLevel(LogLevel.INFO);

        int port = Integer.parseInt(portNumber);
        AllPrimeQueries allPrimeQueries = new AllPrimeQueries();
        PrimeServerSocket primeServerSocket = new PrimeServerSocket(port, allPrimeQueries);

        // Start the server socket in a separate thread
        Thread serverThread = new Thread(() -> primeServerSocket.startServer());
        serverThread.start();

        // Start the server menu in the main thread
        ServerMenu serverMenu = new ServerMenu(allPrimeQueries);
        serverMenu.showMenu();

        try {
            serverThread.join(); // Optional: Wait for the server thread to finish
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            debug.log("Server thread was interrupted: " + e.getMessage(), LogLevel.ERROR);
        }
    }
}
