package com.example.tablayout_pager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

import adapter.MyPagerAdapter;

/**
 * @ pager
 * - 종이넘기듯이 화면을 넘겨주는 역할을 하는 오브젝트
 * - 데이터와 화면을 잘 연결해주기 위해서 adapter가 필요하다.
 * @ TabLayout까지 연계해서 생각
 * - tab을 담당하는 역할
 * - 보통 같이 작성을 하지만, 따로 따로 만들어도 상관이 없다.
 * @ Listener
 * - pager와 TabLayout을 연결해 주기 위해 필요하다.
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    public static final int TAB_COUNT = 3;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MyPagerAdapter myPagerAdapter;
    private String tabName[] = {"ONE","TWO","THREE"};

    private void initData() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), TAB_COUNT);
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.addTab(tabLayout.newTab().setText(tabName[0]));
        tabLayout.addTab(tabLayout.newTab().setText(tabName[1]));
        tabLayout.addTab(tabLayout.newTab().setText(tabName[2]));
    }

    private void addEventListner() {
        //뷰 페이저와 tab연동
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d(TAG, "[onTabSelected]position : " + position);
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d(TAG, "[onTabUnselected]position : " + position);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d(TAG, "[onTabReselected]position : " + position);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 버튼 눌렀을 때 콜백받아야 하니까 안에 매개변수로 인터페이스가 정의되어있을 덧..
        //pager쓸 때는 어댑터가 필요하다.
        initData();
        addEventListner();
    }
}

