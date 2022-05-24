package com.example.class_04;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class Activity1 extends AppCompatActivity {
    private Button btn1;
    private static final String TAG = "Activity1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        btn1 = findViewById(R.id.btn1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btn1.setOnClickListener(view ->
        {
            Log.d(TAG,"btn1 click!!~~~~~!");
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop!~~~~~!");

    }
}