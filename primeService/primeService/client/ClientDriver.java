package primeService.client;

import primeService.socket.PrimeClientSocket;
import primeService.socket.PrimeClientWorker;
import primeService.util.Debug;
import primeService.util.LogLevel;

public class ClientDriver {
    public static void runClient(String host, String portNumber) {
        
        // create a new Debug Instance
        Debug debug = Debug.getInstance();
        debug.setLogLevel(LogLevel.INFO);

        int port = Integer.parseInt(portNumber);

        try {
            PrimeClientSocket clientSocket = new PrimeClientSocket(host, port);
            clientSocket.connect();

            PrimeClientWorker clientWorker = new PrimeClientWorker(clientSocket.getSocket());
            clientWorker.setup();

            ClientMenu clientMenu = new ClientMenu(clientWorker);
            clientMenu.showMenu();

            clientWorker.close();
            clientSocket.close();
        } catch (Exception e) {
            debug.log("Error: " + e.getMessage(), LogLevel.ERROR);
        }
    }
}
