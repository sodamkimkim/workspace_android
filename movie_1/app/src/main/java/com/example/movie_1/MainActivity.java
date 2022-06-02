package com.example.movie_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.movie_1.databinding.ActivityMainBinding;
import com.example.movie_1.utils.FragmentType;
//https://yts.mx/api/v2/list_movies.json?limit=10&page=2&sort_by=rating
public class MainActivity extends AppCompatActivity {
    // 뷰 바인딩 생성 방법
    //1. 안드로이드가 만들어준 객체 선언
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 2. 객체 초기화
        // inflate--> 메모리에 올리다
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        // 3. 연결
        setContentView(binding.getRoot());
        replaceFragment(FragmentType.MOVIE);
        addBottomNavigationListener();
    }

    private void replaceFragment(FragmentType type) {
        Fragment fragment;
        // androidx거 임포트
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (type == FragmentType.MOVIE) {
            //Fragment생성
            fragment = MovieFragment.newInstance(); // MOVIE TAG

        } else {
            // 생성
            fragment = InfoFragment.newInstance(); // INFO TAG
        }
        transaction.replace(binding.mainContainer.getId(), fragment, type.toString());
        transaction.commit();
    }

    private void addBottomNavigationListener() {
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.page1:
                    replaceFragment(FragmentType.MOVIE);
                    break;
                case R.id.page2:
                    replaceFragment(FragmentType.INFO);
                    break;
            }
            return true;
        });
    }
}