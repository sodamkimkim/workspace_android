package com.example.mysecretdiary001;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class DiaryActivity extends AppCompatActivity {

    EditText diaryEditText;
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        diaryEditText = findViewById(R.id.diaryEditText);

        // 사용자가 이전에 적었던 비밀 일기장을 불러와야 한다.
        SharedPreferences notePreferences = getSharedPreferences("diary", Context.MODE_PRIVATE);
        diaryEditText.setText(notePreferences.getString("detail", ""));

        // thread 기능 구현
        Runnable runnable = () -> {
            SharedPreferences preferences = getSharedPreferences("diary", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("detail", diaryEditText.getText().toString());
            editor.apply();
        };


        // 사용자가 한글자 한글자 입력할 때 마다 이벤트 리스너를 등록해서 변경 사항을 가지고 오기
        diaryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("TAG", "char : " + charSequence);
                Log.d("TAG", "char : " + i);
                Log.d("TAG", "char : " + i1);
                Log.d("TAG", "char : " + i2);
            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 한글자 한글자 입력이 들어올때마다 SharedPreferences 저장 할 에정


                //새로운 스레들을 만들어서 비동기 저장
                // 스레들 간 통신
                ////////////////
                // 여기에서 다른 스레드한테 일 해달라고 요청
                // a : 0.5초 딜레이
                // ab : 0.5초 딜레이 -- 삭제
                // abc : 0.5초 딜레이 -- 삭제
                // abcd : 0.5초 딜레이 2초
                handler.removeCallbacks(runnable);
//                handler.post(runnable);
                handler.postDelayed(runnable, 500);
                ////////////////


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}