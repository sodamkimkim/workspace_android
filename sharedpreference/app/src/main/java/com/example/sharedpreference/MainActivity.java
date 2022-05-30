package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnSave;
    private Button btnLoad;
    private Button btnDelete;
    private Button btnDeleteAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        addEventListener();

        // SharedPreference
        // 생성 방법
        SharedPreferences sp1 = getSharedPreferences("sp1", MODE_PRIVATE);
        // 안드로이드는 하나의 앱이 다른앱으로 데이터를 보낸다거나 할 수 있다.
        // 공유하기 같은거. URL전송하기
        // 혹시라도 다른앱이 우리앱의 데이터를 탈취할 수 도 있다.
        //MODE_PRIVATE은 '여기서만 쓸거야' 라는 뜻

        // MODE
        // - MODE_PRIVATE: 생성한 어플리케이션에서만 사용 가능
        // - MODE_WORLD_READABLE : 다른 앱에서 사용가능, 읽기만 가능
        // - MODE_WORLD_WRITABLE : 다른 앱에서 사용가능, 읽기 쓰기 전부 가능

        // 데이터 입력해보기
        // 데이터를 넣기 위해 수정모드로 먼저 만들어야 한다.
        SharedPreferences.Editor editor = sp1.edit();
        editor.putString("name", "홍길동");
        // 여기까지는 아직 저장안됨. .edit() 이후 .commit()까지 해줘야 한다.
        editor.apply();
        //editor.commit();
        //editor.apply();

        // <apply()와 commit()의 차이점>
        // apply() --> thread 를 block시키지 않는다.
        //          -- 비동기방식으로 움직임.
        //          -- 저장의 성공여부 true, false값을 주지 않는다.
        // commit() --> thread가 block된다.
        //          -- 동기방식으로 움직임.
        //          -- Commit은,, 저장을 성공하면 boolean타입인 true값을 반환한다.

    }

    private void initData() {
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);
        btnDelete = findViewById(R.id.btnDelete);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);
    }

    private void addEventListener() {
        btnSave.setOnClickListener(view -> {
            // name, age, isMarried 데이터를 저장하는 기능을 만들어 주세요.
            // 1. sharedPreference 객체를 불러온다.(이름, 모드)
            // 2. 수정모드로 만들어 줘야 한다.
            // 3. 저장할 데이터를 입력한다.
            // 4. 비동기 방식으로 데이터를 완전히 저장한다.
            // name(String), age(int), isMarried(Boolean) 데이터를 저장하는 기능을 만들어 주세요.
            SharedPreferences preferences = getSharedPreferences("sp1", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", "이순신");
            editor.putInt("age", 25);
            editor.putBoolean("isMarried", true);
            editor.apply();

        });
        btnLoad.setOnClickListener(view -> {
            SharedPreferences preferences = getSharedPreferences("sp1", MODE_PRIVATE);
            // 데이터 읽어 보기
            String name = preferences.getString("name", "");
            int age = preferences.getInt("age", 0);
            Boolean isMarried = preferences.getBoolean("isMarried", false);

            Toast.makeText(this, "age : " + age, Toast.LENGTH_SHORT).show();
            Log.d("TAG", "name 값 확인 : " + name);
            Log.d("TAG", "age 값 확인 : " + age);
            Log.d("TAG", "isMarried 값 확인 : " + isMarried);

        });
        btnDelete.setOnClickListener(view -> {
            //  메서드 체이닝 방식으로 코드 작성해 보기
            // age라는 값만 삭제해보자.
            getShared("sp1").edit().remove("age").apply();
            // 잘 삭제가 되었는지 확인하기.
            // 코드상으로 버튼을 눌렀다 명령하기.
            btnLoad.callOnClick();


        });
        btnDeleteAll.setOnClickListener(view -> {
            getShared("sp1").edit().clear().apply();
            // 코드상으로 버튼 누르기
            btnLoad.callOnClick();
        });
    }

    private SharedPreferences getShared(String name) {
        return getSharedPreferences(name, MODE_PRIVATE);
    }
}