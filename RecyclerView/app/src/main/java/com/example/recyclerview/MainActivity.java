package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import adapter.FoodAdapter;
import interfaces.OnFoodItemClickListener;
import models.Food;

/*
* RecyclerView
* -- 장점
* - listView의 개선판.
* - viewHolder를 포함(findViewById를 줄이기 위해 객체화시켜 사용)
* - 디자인 변경에 유연
* - layout-Manager, Adapter-Class필요
*
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FoodAdapter foodAdapter = new FoodAdapter(Food.getSampleData(), this, new OnFoodItemClickListener() {
            @Override
            public void onItemClilcked(View view, int position) {
                Log.d("TAG","넘겨받은 position : " + position);
                Intent intent = new Intent(getApplication(), DetailActivity.class);
                // 원래 intent는 object로 주고받지 못함.
                //serializable : 직렬화.
                // 직렬화 처리 --> object단위를 byte타입으로 변환시켜줌.
                // 역직렬화 --> byte -> object
                intent.putExtra("obj",Food.getSampleData().get(position));
                startActivity(intent);
            }
        });

    // 통신으로 json받는 부분
    ArrayList<Food> listSample = Food.getSampleData();
    // 리싸이클러뷰를 사용하기 위해서
    // 1. Adapter 클래스를 직접 생성해야 한다.

    RecyclerView recyclerView = findViewById(R.id.recyclerView);
    recyclerView.setAdapter(foodAdapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
    // 2. LayoutManager를 생성해서 Adapter클래스와 연결해야 한다.
    }
}