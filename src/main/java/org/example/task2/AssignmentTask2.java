package org.example.task2;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class AssignmentTask2 {
    private static final String API_URL = "http://api.mathjs.org/v4/"; // URL for the public API
    private static final int NUMBER_OF_CLIENTS = 10; // Number of HTTP clients to use for making requests.

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        List<HttpClient> clients = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            clients.add(HttpClient.newHttpClient()); // Create a list of HTTP clients for making concurrent requests.
        }

        // Create an ExecutorService with a fixed thread pool size of 500 for concurrent execution.
        ExecutorService executorService = Executors.newFixedThreadPool(500);

        // Create a queue to store expressions to be evaluated.
        LinkedList<String> expressionQueue = new LinkedList<>();

        // Add expressions to the queue for evaluation.
        expressionQueue.add("2*4*4");
        expressionQueue.add("5/(7-5)");
        expressionQueue.add("sqrt(5^2-4^2)");
        expressionQueue.add("sqrt(-3^2-4^2)");

        // Create a list to store Future objects representing the results of expression evaluations.
        List<Future<CompletableFuture<HttpResponse<String>>>> futures = new ArrayList<>();

        while (!expressionQueue.isEmpty()) {
            String expression = expressionQueue.poll();

            // If the queue is empty, exit the loop.
            if (expression == null) {
                break;
            }

            Future<CompletableFuture<HttpResponse<String>>> future = executorService.submit(() -> {
                HttpClient client = clients.get((int) (Math.random() * NUMBER_OF_CLIENTS)); // Select a random HTTP client.
                String encodedExpression = URLEncoder.encode(expression, StandardCharsets.UTF_8);
                HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(API_URL +"?expr="+ encodedExpression)).build();
                return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            });
            futures.add(future);
        }

        // Retrieve and print the results of expression evaluations.
        for (Future<CompletableFuture<HttpResponse<String>>> future : futures) {
            System.out.println(future.get().get().body());
        }

        executorService.shutdown();
    }
}
