package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class Activity1 extends AppCompatActivity {
    private static final String TAG = "Activity1";
    public static final String KEY_NAME = "key name";
    private EditText nameEditText;
    private Button okBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        initData();
        addEventListener();

    }
    private void initData(){
        nameEditText = findViewById(R.id.nameEditText);
        okBtn = findViewById(R.id.okBtn);
    }
    private void addEventListener(){
        okBtn.setOnClickListener(view ->{
            // 1. 현재 메모리에올라가있는 mainEditText 뷰 컴포넌트에 값을 가져 온다.(필요하다면 방어적 코드 작성 공백이거나 널값이면 들고오지마라)
            // 2. 화면 전환 로직(인텐트 사용)
            String name = nameEditText.getText().toString(); //리턴타입 Editable-> tostring 써서 변환
            Intent intent = new Intent(this, Activity2.class);
            // 명시적 intent방법 사용함.
            // context: this --> 내가 어떠한 정보를 가진 누군지 알려줘야함. 다시돌아올때 알아야 한다.
            // context개념..
            intent.putExtra(KEY_NAME,name);
            startActivity(intent);
        });
    }
}