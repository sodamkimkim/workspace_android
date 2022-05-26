package com.example.myaddviewexcats;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Cat> sampleData = Cat.getCats();
        LinearLayout container = findViewById(R.id.catContainer);

        LayoutInflater inflater = LayoutInflater.from(this);
        sampleData.forEach(cat -> {
            View itemView = inflater.inflate(R.layout.item_cat, container, false);
            ImageView catImageView = itemView.findViewById(R.id.catImageView);
            TextView catNameTextView = itemView.findViewById(R.id.catNameTextView);
            TextView catFamilyTextView = itemView.findViewById(R.id.catFamilyTextView);
            TextView catPhone = itemView.findViewById(R.id.phoneTextView);

            catNameTextView.setText(cat.catName);
            catFamilyTextView.setText(cat.familyName);
            catPhone.setText(cat.phoneNum);

            Glide.with(this).load(cat.imageUrl)
                    .circleCrop()
                    .into(catImageView);
            container.addView(itemView);
        });
    }
}