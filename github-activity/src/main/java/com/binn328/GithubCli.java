package com.binn328;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GithubCli {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: GithubCli <username>");
            System.exit(-1);
        }

        // 1. HttpClient 생성
        HttpClient client = HttpClient.newHttpClient();

        // 2. HttpRequest 생성
        HttpRequest getRequest = HttpRequest.newBuilder()
                                    .uri(URI.create("https://api.github.com/users/" + args[0].trim() + "/events"))
                                    .GET()
                                    .header("Accept", "appication/json")
                                    .build();
        // 3. 요청 보내고 응답 받아오기
        try {
            HttpResponse<String> httpResponse = 
            client.send(getRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(httpResponse);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error occured in send request");
        }
        
    };
}

