package com.example.myretrofit.repository.models;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {
    //https://jsonplaceholder.typicode.com/todos
    // 리스트 통째로 들고온다.
    @GET("todos")
    Call<ArrayList<Todo>> getTodos();

}
