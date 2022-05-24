package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    private static final String TAG = "TAG";
    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        txtView = findViewById(R.id.nameTextView);
        // 값을 받는 방법?
        // 값이 없다면 기본 값을 0으로 셋팅하는 defaultValue넣어줘야함
        // 방어적코드 작성 필요
        if (getIntent() != null) {
            String getData = getIntent().getStringExtra(Activity1.KEY_NAME);
            txtView.setText(getData);

        }
    }

}