package com.example.toolbar;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    // 값을 돌려받을 때 사용하는 코드
    // 미리 만들어놔야 한다.
    // 괄호안에서 ctrol +p = > 매개변수..
    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                Log.d("TAG", "comback!! 돌아 왔다");
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // 정상동작한다.
                    Intent data = result.getData();
                    int returnValue = data.getIntExtra("result", 0);
                    textView.setText(returnValue + "");
                } else {
                    // 실패
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        textView = findViewById(R.id.tempTextView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //하나의 xml파일을 올려주는 객체를 만들어줌

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItem1:
                Log.d("TAG", "1번 클릭");
                // 명시적 인텐트
                Intent intent = new Intent(this, SubActivity.class);
                intent.putExtra("value1", 10);
                //startActivity(intent); // intent 전달
                // 값을 돌려받아야 할 때 쓰는 코드
                startActivityResult.launch(intent);
                break;
            case R.id.menuItem2:
                Log.d("TAG", "2번 클릭");
                break;
            case R.id.menuItem3:
                Log.d("TAG", "3번 클릭");
                break;
        }
        return true;
    }
}