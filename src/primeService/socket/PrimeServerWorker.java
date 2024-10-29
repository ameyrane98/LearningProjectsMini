package primeService.socket;

import primeService.server.AllPrimeQueries;
import primeService.util.CheckPrime;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import primeService.util.Debug;
import primeService.util.LogLevel;

public class PrimeServerWorker implements Runnable {
    private final Socket clientSocket;
    private final AllPrimeQueries allPrimeQueries;

    public PrimeServerWorker(Socket clientSocket, AllPrimeQueries allPrimeQueries) {
        this.clientSocket = clientSocket;
        this.allPrimeQueries = allPrimeQueries;
    }

    @Override
    public void run() {
        // create a new Debug Instance
        Debug debug = Debug.getInstance();
        debug.setLogLevel(LogLevel.INFO);
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {

                Document doc = parseXML(inputLine);
                Element rootElement = doc.getDocumentElement();

                String clientName = rootElement.getElementsByTagName("clientName").item(0).getTextContent();
                int queryNumber = Integer
                        .parseInt(rootElement.getElementsByTagName("isPrime").item(0).getTextContent());

                allPrimeQueries.addQuery(clientName, queryNumber);

                String isPrime = CheckPrime.isPrime(queryNumber);
                outputLine = "<primeQueryResponse><intValue>" + queryNumber +
                        "</intValue><isPrime>" + isPrime + "</isPrime></primeQueryResponse>";
                out.println(outputLine);
            }
        } catch (Exception e) {
            debug.log("Exception caught when trying to handle a client connection", LogLevel.ERROR);
            debug.log(e.getMessage(), LogLevel.INFO);
        }
    }

    private Document parseXML(String xml) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        ByteArrayInputStream input = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        return builder.parse(new InputSource(input));
    }

}