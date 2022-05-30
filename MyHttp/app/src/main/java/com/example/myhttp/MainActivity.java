package com.example.myhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.myhttp.repositiory.models.Todo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        try {
//            httpGetText();
//        }catch (NetworkOnMainThreadException e){
//            Toast.makeText(this, "에러가 발생", Toast.LENGTH_SHORT).show();
//        }
        //networkonmainThreadexception -> 통신을 위해서는 thread를 따로 써야해

        new Thread(() -> {
            httpGetTest();

        }).start();
    }

    //https://jsonplaceholder.typicode.com/
    private void httpGetTest() {
        //https://jsonplaceholder.typicode.com/todos/1
        String urlString = "https://jsonplaceholder.typicode.com/todos";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // 안드로이드에서 http메시지를 get방식으로 만들어서 보낸다.
            // 참고
            // connection.setRequestProperty("Content-Type","application/json; charset+UTF-8");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 통신 성공
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        connection.getInputStream(),
                        "UTF-8"
                ));
                // 1. 한 라인(한줄)
                // reader.ready();


//                String line = null;
//                StringBuffer sb = new StringBuffer();
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line + "\n");
//                }
//                Log.d("TAG",sb.toString());

                // Dto 만들어야 해
                // 1. 문자열 --> 배열타입으로 변환하는 방법
                // 2. 문자열 --> ArrayList타입으로 변환하는 방법

                // 문자열 --> 하나의 object로 변환하는 방법
//                Todo todo1 = new Gson().fromJson(reader, Todo.class);
//                Log.d("TAG", todo1.title);

                // 문자열 --> 하나의 배열로 변환하는 방법
//                Todo[] todos = new Gson().fromJson(reader,Todo[].class); // JsonSyntaxException
//                Log.d("TAG",todos.toString());

                // 문자열 --> ArrayList 타입으로 변환하는 방법
                //ArrayList 타입으로 변환하는 방법
                //1.
                Type todoListType = new TypeToken<ArrayList<Todo>>() {
                }.getType();
                //2.
                ArrayList<Todo> todoList = new Gson().fromJson(reader, todoListType);
                for(Todo t : todoList){
                    Log.d("TAG",t.userId + ": userId");
                    Log.d("TAG",t.title + ": title");
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}