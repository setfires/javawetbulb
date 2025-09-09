package dev.masquerade;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    private static final String NWS_URL = "https://forecast.weather.gov/MapClick.php?textField1=41.24&textField2=-112.01";

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        FetchNWSData();
        System.exit(0);
    }

    private static void FetchNWSData() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(NWS_URL))
                .GET()
                .setHeader("User-Agent", "Mozilla/5.0")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.err.println("Invalid HTTP response");
        } else {
            Temperature temp = new Temperature(response.body());
            System.out.println(temp.getF() + "," + temp.getFetchDate());
        }
    }
}