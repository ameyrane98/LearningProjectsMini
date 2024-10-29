package primeService.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class PrimeClientSocket {
    private final String host;
    private final int port;
    private Socket socket;

    public PrimeClientSocket(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws UnknownHostException, IOException {
        this.socket = new Socket(host, port);
    }

    public Socket getSocket() {
        return socket;
    }

    public void close() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}