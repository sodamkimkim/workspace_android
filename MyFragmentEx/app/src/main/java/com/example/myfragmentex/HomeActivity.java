package com.example.myfragmentex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class HomeActivity extends AppCompatActivity {
    private FragmentBanner fragmentBanner;
    private ImageButton btnHome;
    private ImageButton btnLife;
    private ImageButton btnLocation;
    private ImageButton btnChat;
    private ImageButton btnMy;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnHome = findViewById(R.id.btnHome);
        btnLife = findViewById(R.id.btnLife);
        btnLocation = findViewById(R.id.btnLocation);
        btnChat = findViewById(R.id.btnChat);
        btnMy = findViewById(R.id.btnMy);
        container = findViewById(R.id.container);



    }
}