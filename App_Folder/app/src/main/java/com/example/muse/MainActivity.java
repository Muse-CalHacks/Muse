package com.example.muse;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.*;
//import

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//import org.json.*;
import com.google.gson.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Used for Gson
     */
    public class Songs{
        //public String title;
        public String next;
        public ArrayList<SongData> data;

        public void setNext(String next){
            this.next = next;
        }

        public void setData(ArrayList<SongData> data){
            this.data = data;
        }
    }

    public class SongData{
        public String title;
        public void setTitle(String title){
            this.title = title;
        }
    }


    /**
     * Checks if connected to network (but can also not be connected to inernet)
     * @return true if connected to network
     */
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    /*
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
                return !ipAddr.equals("");

            } catch (Exception e) {
                return false;
        }
    }
    */


    /**
     * On button_search clicked, calls deezer(artist) and outputs it to textview_songs
     * @param v
     */
    public void searchClicked(View v){
        EditText artistText = findViewById(R.id.editText_artist);
        String artistName = artistText.getText().toString(); //editText_artist converted to string

        TextView songView = findViewById(R.id.textView_song);
        songView.setMovementMethod(new ScrollingMovementMethod()); //scrollable

        if(isNetworkConnected()){
            String info = deezer(artistName);
            //System.out.println(info);
            //songView.setText(info.substring(0,10));
            //songView.setText("hello");
        } else {
            songView.setText("Not connected to a network");
        }

    }

    /**
     * Currently returns the jsonbody (as a string) of a given artist name.
     * @param artist
     * @return if android is connected to the api successfully, returns jsonbody to string
     *         else returns "no response"
     * //@throws IOException
     */
    public static String deezer(String artist) { //throws IOException
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://deezerdevs-deezer.p.rapidapi.com/search?q=" + artist)
                .get()
                .addHeader("x-rapidapi-host", "deezerdevs-deezer.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "f501cb6e2fmsh6da6dc9b2afed6ep16156ejsn8a3f63043162")
                .build();

        //says min needs to be 19 for android api
        try{
            Response response = client.newCall(request).execute();
            //return response.body().string(); //returns the whole json string

            Gson gson = new Gson();

            //Songs[] songArray = gson.fromJson(response.body().string(), Songs[].class);
            Songs song1 = gson.fromJson(response.body().string(), Songs.class);

            //System.out.println("title: " + song1.title);
            System.out.println("next ting: " + song1.next);
//            for(Songs songTitle : songArray){
//                System.out.println(songTitle.title);
//            }
            //System.out.println("data: " + song1.data);

            System.out.println("Artist: " + artist);
            for(int i = 0; i < song1.data.size(); i++){

                System.out.println((i + 1) + ": " + song1.data.get(i).title);
            }

            return "Yawww Yeeet Data Fetch worked EPICiciC";
        } catch (IOException x){
            return "No response caught IOException rip";
        }
    }

    /**
     * For console testing
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Hola");

        //try {
            String ret = deezer("ksi");
            System.out.println(ret);
        //} catch(IOException x){
        //    System.out.println("Error no response (psvm)");
        //}
    }
}
