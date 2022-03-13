package web;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {
    public static void main(String[] args) throws IOException {
        // prework build the JAVA objects that will hold the JSON data

        // step  1 go to the internet and get the JSON data
        // make a url for the API
        URL pokeUrl = new URL("https://pokeapi.co/api/v2/pokemon/ditto");

        // making a connection to the API
        HttpURLConnection pokeHttpURLConnection = (HttpURLConnection) pokeUrl.openConnection();

        // specify the method for the connection
        pokeHttpURLConnection.setRequestMethod("GET");

        // Read the response from the API and save in a string variable
        InputStreamReader pokeInputStreamReader = new InputStreamReader(pokeHttpURLConnection.getInputStream());
        BufferedReader pokeBufferedReader = new BufferedReader(pokeInputStreamReader);
        String pokeData = pokeBufferedReader.readLine();
        System.out.println(pokeData);

        // step 2 same as last week parse the json data into objects using GSON
        // we need to convert the data to Java objects using GSON
        Gson gson = new Gson();
        Pokemon ditto = gson.fromJson(pokeData, Pokemon.class);

        System.out.println(ditto);

        // save the response from the API to a file
        File dittoFile = new File("./ditto.json");
        try (FileWriter dittoFileWriter = new FileWriter(dittoFile)) {
            gson.toJson(ditto, dittoFileWriter);
        }
    }
}
