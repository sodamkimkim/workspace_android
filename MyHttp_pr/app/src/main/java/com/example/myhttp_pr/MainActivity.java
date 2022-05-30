package com.example.myhttp_pr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.myhttp_pr.models.Todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(()->{
            httpGetTest();
        }).start();
    }

    private void httpGetTest() {
        //https://jsonplaceholder.typicode.com/todos
        String urlString = "https://jsonplaceholder.typicode.com/todos";
        URL url = null;
        try {
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "UTF-8"));
            Type todoListType = new TypeToken<ArrayList<Todo>>() {
            }.getType();
            ArrayList<Todo> todoList = new Gson().fromJson(reader,todoListType);
            for(Todo t : todoList){
                Log.d("TAG",t.userId + " :userId");
                Log.d("TAG",t.title + " : title");
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}