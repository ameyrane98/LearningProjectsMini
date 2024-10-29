package primeService.server;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class AllPrimeQueries {
    private final Map<String, StringJoiner> clientQueries = new HashMap<>();

    public synchronized void addQuery(String clientName, int query) {
        clientQueries.computeIfAbsent(clientName, k -> new StringJoiner(" "))
                     .add(String.valueOf(query));
    }

    public synchronized String getClientQueries(String clientName) {
        return clientQueries.getOrDefault(clientName, new StringJoiner(" ")).toString();
    }

    public synchronized Map<String, String> getAllQueries() {
        Map<String, String> result = new HashMap<>();
        clientQueries.forEach((key, value) -> result.put(key, value.toString()));
        return result;
    }
}