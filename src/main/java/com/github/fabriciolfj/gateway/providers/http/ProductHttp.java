package com.github.fabriciolfj.gateway.providers.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProductHttp {

    public String request(final String url) throws URISyntaxException, IOException, InterruptedException {
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(new URI(url))
                .GET()
                .build();

        var result = client.send(request, HttpResponse.BodyHandlers.ofString());
        return result.body();
    }
}
