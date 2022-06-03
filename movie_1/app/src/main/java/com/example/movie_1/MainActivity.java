package com.example.movie_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.example.movie_1.databinding.ActivityMainBinding;
import com.example.movie_1.interfaces.OnChangeToolbarType;
import com.example.movie_1.interfaces.OnPassWebview;
import com.example.movie_1.utils.Define;
import com.example.movie_1.utils.FragmentType;

//https://yts.mx/api/v2/list_movies.json?limit=10&page=2&sort_by=rating
public class MainActivity extends AppCompatActivity implements OnChangeToolbarType, OnPassWebview {
    // 뷰 바인딩 생성 방법
    //1. 안드로이드가 만들어준 객체 선언
    ActivityMainBinding binding;
    WebView webView; // <-- InfoFragment에서 생성하는 WebBiew 객체 주소를 전달받을 예정

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
            fragment = MovieFragment.getInstance(this); // MOVIE TAG

        } else {
            // 생성
            fragment = InfoFragment.getInstance(this); // INFO TAG
            if(fragment != null){
            InfoFragment infoFragment = (InfoFragment) fragment;
            infoFragment.setOnPassWebview(this);// 주소연결
            }

        }
        // 문자열로 이름지어서 구분해 놓는 녀석 is Tag. --> replace마지막 매개변수
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

    @Override
    public void onBackPressed() {
        // info fragment라면,
        // 한번은 movie fragment로 갔다가
        // movie fragment라면
        // 원래대로 종료
        // mainContainer에 올라와있는 녀석이
        // 현재 movie fragment인지, info fragment인지 구분할 수 있다면 기능을 완성할 수 있다.!
        //"INFO"가 있으면 tag를 가지고와
        String fragmentTag = getSupportFragmentManager().findFragmentByTag(FragmentType.INFO.toString()).getTag();
        if (fragmentTag.equals(FragmentType.INFO.toString())) {
            if(webView.canGoBack()){
                webView.goBack();
            }else{
                View view = binding.bottomNavigation.findViewById(R.id.page1);
                view.callOnClick();

            }
//            View view = binding.bottomNavigation.findViewById(R.id.page1);
//            view.callOnClick();

        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void onSetupType(String title) {
        // fragment에서 onSetupType() 호출하면 여기로 코드가 돌아온다.
        Log.d("TAG", "title" + title);
        if(title.equals(Define.PAGE_TITLE_MOVIE)){
            binding.topAppbar.setTitle(title);
            binding.topAppbar.setVisibility(View.VISIBLE);
        }else if(title.equals(Define.PAGE_TITLE_INFO)){
            binding.topAppbar.setVisibility(View.GONE);
        }

    }

    @Override
    public void onPassWebViewObj(WebView webView) {
        this.webView = webView;
    }
}