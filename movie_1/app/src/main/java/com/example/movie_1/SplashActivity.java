package com.example.movie_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.os.Handler;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        activity보다 상위에 있는 개체인 window
        //액티비티 위에 상태바같은거 없애기
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        TextView textView = findViewById(R.id.splashTextView);
        // 특정 애니메이션 메모리에 올리기
        Animation slideAnimation = AnimationUtils.loadAnimation(this,R.anim.slide_in_bottom);
        textView.startAnimation(slideAnimation);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                //splashActivity --> MainActivity
                finish();
                //MainActivity
            }
        },1500);

    }
}