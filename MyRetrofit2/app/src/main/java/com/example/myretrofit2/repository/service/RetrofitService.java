package com.example.myretrofit2.repository.service;

import com.example.myretrofit2.repository.models.Post;
import com.example.myretrofit2.repository.models.Todo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {
    //https://jsonplaceholder.typicode.com/
    @GET("todos")
    Call<ArrayList<Todo>> getTodos();

    //https://jsonplaceholder.typicode.com/todos/22
    @GET("todos/{id}") // path - variable방식으로 mapping
    Call<Todo> getTodo(@Path("id") int id);

    //https://jsonplaceholder.typicode.com/posts

    /**
     *   {
     *     "userId": 1,
     *     "id": 1,
     *     "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
     *     "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
     *   }
     */
    // 모바일 -> 서버에 내 정보를 데이터 베이스에 입력 요청
    @FormUrlEncoded
    @POST("posts")
    Call<Post> createPost(
            @Field("userID") int userID,
            @Field("id") int id,
            @Field("title") String title,
            @Field("body") String body

    );
}
