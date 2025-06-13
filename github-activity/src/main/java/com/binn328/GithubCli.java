package com.binn328;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
                                    .header("Accept", "application/json")
                                    .build();
        // 3. 요청 보내고 응답 받아오기
        try {
            HttpResponse<String> httpResponse = 
            client.send(getRequest, HttpResponse.BodyHandlers.ofString());
            
            switch (httpResponse.statusCode()) {
                case 404 -> System.out.println("Can't find username: " + args[0]);
                case 200 -> {
                    JsonArray jsonArray = JsonParser.parseString(httpResponse.body()).getAsJsonArray();    
                    for (JsonElement element: jsonArray) {
                        JsonObject obj = element.getAsJsonObject();
                        String type = obj.get("type").getAsString();
                        switch (type) {
                            case "PushEvent" -> {
                                // commit 경로 가져오기
                                JsonObject repo = obj.get("repo").getAsJsonObject();
                                String repoName = repo.get("name").getAsString();

                                // commit 갯수 가져오기
                                JsonObject payload = obj.get("payload").getAsJsonObject();
                                JsonArray commits = payload.get("commits").getAsJsonArray();
                                int commitCount = commits.size();

                                System.out.println("- Pushed " + commitCount + " commits to " + repoName);
                            }
                            case "IssuesEvent" -> {
                                // commit 경로 가져오기
                                JsonObject repo = obj.get("repo").getAsJsonObject();
                                String targetRepo = repo.get("name").getAsString();

                                System.out.println("- Opened a new issue in " + targetRepo);
                            }
                            case "WatchEvent" -> {
                                JsonObject repo = obj.get("repo").getAsJsonObject();
                                String targetRepo = repo.get("name").getAsString();

                                System.out.println("Starred " + targetRepo);
                            }
                            case "ForkEvent" -> {
                                JsonObject repo = obj.get("repo").getAsJsonObject();
                                String targetRepo = repo.get("name").getAsString();

                                System.out.println("Forked " + targetRepo);
                            }
                            case "CreateEvent" -> {
                                JsonObject repo = obj.get("repo").getAsJsonObject();
                                String targetRepo = repo.get("name").getAsString();

                                JsonObject payload = obj.get("payload").getAsJsonObject();
                                String createType = payload.get("ref_type").getAsString();
                                System.out.println("Created " + createType + " in " + targetRepo);
                            }
                            default -> {
                                System.out.println(obj.getAsString());
                            }

                        }
                    }               
                }
                default -> {
                    System.out.println("Can't find username: " + args[0]);
                }
            }


            
        } catch (IOException | InterruptedException e) {
            System.out.println("Error occured in send request");
        }
        
    };
}

