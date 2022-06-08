package com.example.todayslunch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.todayslunch.adapter.CategoryAdapter;
import com.example.todayslunch.interfaces.OnCategoryItemClickListener;
import com.example.todayslunch.models.Category;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CategoryAdapter categoryAdapter = new CategoryAdapter(Category.getCatagoryData(), this, new OnCategoryItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Log.d("TAG","넘겨받은 position : " + position);
                Intent intent = new Intent(getApplication(), DetailActivity.class);
                // 원래 intent는 object로 주고받지 못함.
                //serializable : 직렬화.
                // 직렬화 처리 --> object단위를 byte타입으로 변환시켜줌.
                // 역직렬화 --> byte -> object
//                intent.putExtra("obj",Category.getCatagoryData().get(position));
                startActivity(intent);
            }
        });

        ArrayList<Category> listMenuData = Category.getCatagoryData();
        RecyclerView recyclerView = findViewById(R.id.recyclerView1);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.hasFixedSize();
}
}