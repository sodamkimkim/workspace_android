package com.example.viewpager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.viewpager2.adapter.ImageSliderAdapter;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 sliderImageViewPager;
    private ArrayList<String> images = new ArrayList<>();
    private LinearLayout layoutIndicatorsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        images.add("https://cdn.pixabay.com/photo/2017/11/14/13/06/kitty-2948404_960_720.jpg");
        images.add("https://cdn.pixabay.com/photo/2018/07/13/10/20/kittens-3535404_960_720.jpg");
        images.add("https://cdn.pixabay.com/photo/2016/11/14/09/14/cat-1822979_960_720.jpg");
        images.add("https://cdn.pixabay.com/photo/2014/04/13/20/49/cat-323262_960_720.jpg");
        images.add("https://cdn.pixabay.com/photo/2017/11/09/21/41/cat-2934720_960_720.jpg");
        images.add("https://cdn.pixabay.com/photo/2017/09/25/13/14/dog-2785077_960_720.jpg");

        layoutIndicatorsContainer = findViewById(R.id.layoutIndicators);
        sliderImageViewPager = findViewById(R.id.sliderViewPager2);
        // view - default(1)
        sliderImageViewPager.setOffscreenPageLimit(2);
        sliderImageViewPager.setAdapter(new ImageSliderAdapter(this, images));
        sliderImageViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d("TAG", "position :" + position);
                setCurrentIndicator(position);
            }
        });
        setupIndicators(images.size());
    } // end of onCreate

    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        // LayoutParams
        // 뷰가 어떻게 배치될 지 정의하는 속성
        // margin이나 wrap크기 등
        // xml파일에서 layout_같은거 붙는 속성들을 말한다.
        // layoutParams 객체를 통해서 다룰 수 있다.

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(16, 8, 16, 8);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.bg_indecator_inactive));
            // imageView obj
            indicators[i].setLayoutParams(params);
            layoutIndicatorsContainer.addView(indicators[i]);
        } // end of setupIndicators
    }
        private void setCurrentIndicator ( int position){
            int childCount = layoutIndicatorsContainer.getChildCount();
            for(int i = 0; i<childCount;i++){
                ImageView imageView = (ImageView) layoutIndicatorsContainer.getChildAt(i);
                if(i==position){
                    imageView.setImageDrawable(ContextCompat.getDrawable(this,
                            R.drawable.bg_indecator_active
                            ));
                }else{
                    imageView.setImageDrawable(ContextCompat.getDrawable(this,
                            R.drawable.bg_indecator_inactive
                    ));
                }
            }
        }
}