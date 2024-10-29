package primeService.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PrimeClientWorker {
    private final Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String clientName;

    public PrimeClientWorker(Socket socket) {
        this.socket = socket;
    }

    public void setup() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void sendPrimeQuery(int number) {
        String query = "<primeQuery><clientName>" + clientName + "</clientName><isPrime>" + number
                + "</isPrime></primeQuery>";
        out.println(query);
    }

    public String receiveResponse() throws IOException {
        return in.readLine();
    }

    public void close() throws IOException {
        out.close();
        in.close();
    }
}