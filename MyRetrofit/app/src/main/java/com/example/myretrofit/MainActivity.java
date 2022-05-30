package com.example.myretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


import com.example.myretrofit.repository.models.RetrofitService;
import com.example.myretrofit.repository.models.Todo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 모델 분리
// 비즈니스 로직을 처리하는 부분을 분리
public class MainActivity extends AppCompatActivity {
    TextView textView;
    Retrofit retrofit;
    RetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.temp);
        // retrofit 객체 초기화
        // baseUrl 사용시 반드시 마지막에 / 해줘야 한다.
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build(); // retrofit 데이터타입 return

        // 비지니스 로직
        // todo데이터를 들고 오는 것
        // post 데이터를 들고 오는 것.
        // users 데이터를 들고 오는 것.

        // 초기화된 레트로핏 object와 비즈니스로직인 interface를 연결
        service = retrofit.create(RetrofitService.class);
        // 나는 비동기로 호출할거야.
        service.getTodos().enqueue(new Callback<ArrayList<Todo>>() {
            @Override
            public void onResponse(Call<ArrayList<Todo>> call, Response<ArrayList<Todo>> response) {
                Log.d("TAG", response.isSuccessful() + " < --- ");
                Log.d("TAG", response.message() + " < --- ");
                if (response.isSuccessful()) {
                    // Gson 썼을 땐 타입변환코드 썼었는데 지금안쓰고있다.
                    // 반복코드 굉장히 줄여줌
                    // thread도 알아서 해줌
                    Todo todo = response.body().get(0);
                    //시작줄, 헤더 , 바디(data딸려온다.)
                    Log.d("TAG", "userId + " + todo.userId);
                    textView.setText(todo.completed + ""); // 혹시몰라 문자열 변환.?

                }

            }

            @Override
            public void onFailure(Call<ArrayList<Todo>> call, Throwable t) {
                Log.d("TAG", "실패 : " + t.getMessage());
            }
        });


    }
}