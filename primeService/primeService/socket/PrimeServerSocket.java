package primeService.socket;

import primeService.server.AllPrimeQueries;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import primeService.util.Debug;
import primeService.util.LogLevel;

public class PrimeServerSocket {
    private final int port;
    private final AllPrimeQueries allPrimeQueries;

    public PrimeServerSocket(int port, AllPrimeQueries allPrimeQueries) {
        this.port = port;
        this.allPrimeQueries = allPrimeQueries;
    }

    public void startServer() {

        // create a new Debug Instance
        Debug debug = Debug.getInstance();
        debug.setLogLevel(LogLevel.INFO);
        try {
            // Bind to the wildcard address to accept connections on any network interface
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                debug.log("Server started on all interfaces at port " + port, LogLevel.INFO);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    PrimeServerWorker worker = new PrimeServerWorker(clientSocket, allPrimeQueries);
                    Thread workerThread = new Thread(worker);
                    workerThread.start();
                }
            }
        } catch (IOException e) {
            debug.log("Could not listen on port " + port, LogLevel.ERROR);
            debug.log(e.getMessage(), LogLevel.INFO);
        }
    }
}