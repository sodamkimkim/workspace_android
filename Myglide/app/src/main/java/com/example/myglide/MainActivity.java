package com.example.myglide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView1 = findViewById(R.id.imageView1);
        Glide.with(this)
                .load(R.drawable.temp1)
                .circleCrop()
                .into(imageView1);
//          glide 나 피카소 많이 쓰이는데 기능은 Glide가 많음
//        Glide.with(this)
//                .load("https://picsum.photos/id/237/200/300")
//                .circleCrop() // 그림 라운드 처리.
//                .into(imageView1);
        // 프로젝트에 있는 이미지들을 라운드 처리하는 라이브러리를 사용해서
        // circleCrop처리를 한다면 사용이 안된다.(자를 수 없다)
    }
}