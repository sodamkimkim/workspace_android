package com.example.bottomnavi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.bottomnavi.utils.FragmentType;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        addBottomNavigationListener();
        replaceFragment(FragmentType.Home);
    }

    private void replaceFragment(FragmentType type) {
        //프래그먼트 3개 생성
        Fragment fragment;
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (type == FragmentType.Home) {
            fragment = new FragmentHome();

        } else if (type == FragmentType.TV) {
            fragment = new FragmentTV();
        } else {
            fragment = new FragmentCAL();
        }
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();

        // 프래그먼트 매니저

        // 프래그먼트 트랜젝션 필요

    }

    private void addBottomNavigationListener() {

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.page_home:
                    Log.d("TAG", "111");
                    replaceFragment(FragmentType.Home);
                    //플래그먼트 교체
                    break;
                case R.id.page_home2:
                    Log.d("TAG", "222");
                    replaceFragment(FragmentType.TV);
                    //플래그먼트 교체
                    break;
                case R.id.page_home3:
                    Log.d("TAG", "333");
                    replaceFragment(FragmentType.CAL);
                    //플래그먼트 교체
                    break;
            }
            return true;
        });
    }
}