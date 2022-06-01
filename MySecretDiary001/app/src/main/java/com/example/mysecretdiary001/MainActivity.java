package com.example.mysecretdiary001;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NumberPicker numberPicker1;
    private NumberPicker numberPicker2;
    private NumberPicker numberPicker3;

    private Button openButton;
    private Button changePasswordButton;
    private SharedPreferences passwordPreferences;
    // 프로그램 흐름 (두 가지 모드)
    private boolean changePasswordMode = false;
    private final static String KEY_PWD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        addEventListener();

    }

    private void initData() {
        numberPicker1 = findViewById(R.id.numberPicker1);
        numberPicker2 = findViewById(R.id.numberPicker2);
        numberPicker3 = findViewById(R.id.numberPicker3);
        numberPicker1.setMaxValue(9);
        numberPicker1.setMinValue(0);
        numberPicker2.setMaxValue(9);
        numberPicker2.setMinValue(0);
        numberPicker3.setMaxValue(9);
        numberPicker3.setMinValue(0);
        openButton = findViewById(R.id.openButton);
        changePasswordButton = findViewById(R.id.changePasswordButton);

        passwordPreferences = getSharedPreferences(KEY_PWD, Context.MODE_PRIVATE);

    }

    private void addEventListener() {
        // 비밀 다이어리 오픈 버튼 눌렀을 때 동작 처리
        openButton.setOnClickListener(view -> {

            if (changePasswordMode) {
                // 작은 버튼 눌러서 색상이 변경 되었을 때 진행을 시키면 안된다.
                Toast.makeText(this, "비밀번호 변경 중입니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            // 저장되어 있는 패스워드 데이터를 불러와서 확인 해야 한다.
            // 패스워드 저장하는 방식 - 로컬 DB (SQL Lite), SharedPreference
            // 영속성이 있는 곳에 저장해야 함
            // 램에 올리면 재부팅 시 데이터 전부 나가리
//            passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE);

            String passwordFromUser = getUserNumber();

            // 값을(비밀번호) 세팅 하지 않았을 경우 초기값 세팅으로 처리
            // 000 <---> 000 : ok
            // 333 <---> 333 : ok
            // 333 <---> 123: X
            if (passwordPreferences.getString(KEY_PWD, "000").equals(passwordFromUser)) {
                // 패스워드 확인이 성공
                Intent intent = new Intent(this, DiaryActivity.class);
                startActivity(intent);
            } else {
                // 사용자한테 비밀번호가 틀렸다고 알림
                showErrorAlertDialog();
            }


        });

        // 비밀번호 변경 버튼 눌렀을 경우 동작 처리
        changePasswordButton.setOnClickListener(view -> {
//            passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE);
            String passwordFromUser = getUserNumber();

            // 비밀번호를 변경 중인 경우, 처음 변경하겠다고 누르는 경우
            if(changePasswordMode) {
                // 비밀번호 변경하는 경우 ---> 저장 처리
                SharedPreferences.Editor editor = passwordPreferences.edit();
                editor.putString("password",getUserNumber());
                editor.apply();

                changePasswordMode = false;
                changePasswordButton.setBackgroundColor(Color.BLACK);
            } else {
                // 비밀번호 변경 모드 활성화 시키기
                // 단, 현재 numberPicker 숫자가 내가 정한 비밀번호와 일치해야
                // 변경 모드로 만들어 줘야 한다.
                if(passwordPreferences.getString(KEY_PWD, "000").equals(passwordFromUser)) {
                    changePasswordMode = true;
                    Toast.makeText(this, "변경할 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show();
                    changePasswordButton.setBackgroundColor(Color.RED);
                } else {
                    showErrorAlertDialog();
                }
            }

        });


    }

    private void showErrorAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("실패")
                .setMessage("비밀번호가 잘못되었습니다")
                .setPositiveButton("닫기", (dialogInterface, i) -> {
                    // 동작 정의 안함
                    Log.d("TAG", "1111");
                });
        builder.show();
    }


    private String getUserNumber() {
        String passwordFromUser = "" +
                numberPicker1.getValue() +
                numberPicker2.getValue() +
                numberPicker3.getValue();
        return passwordFromUser;
    }


}