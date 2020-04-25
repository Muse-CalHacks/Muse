package com.example.muse;

import androidx.appcompat.app.AppCompatActivity;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;

import java.io.IOException;
import java.util.*;
//import

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static String deezer() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://deezerdevs-deezer.p.rapidapi.com/search?q=eminem")
                .get()
                .addHeader("x-rapidapi-host", "deezerdevs-deezer.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "f501cb6e2fmsh6da6dc9b2afed6ep16156ejsn8a3f63043162")
                .build();

        try(Response response = client.newCall(request).execute()){
            return response.body().string();
        } catch (IOException x){
            return "No response";
        }
    }

    public static void main(String[] args) {
        try {
            String ret = deezer();
            System.out.println(ret);
        } catch(IOException x){
            System.out.println("Error no response (psvm)");
        }
    }
}
