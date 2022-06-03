package com.example.movie_1;

import android.graphics.Bitmap;
import android.icu.text.IDNA;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.movie_1.databinding.FragmentInfoBinding;
import com.example.movie_1.interfaces.OnChangeToolbarType;
import com.example.movie_1.interfaces.OnPassWebview;
import com.example.movie_1.utils.Define;

public class InfoFragment extends Fragment {
    private FragmentInfoBinding binding;
    private OnChangeToolbarType onChangeToolbarType;
    private static InfoFragment infoFragment;
private WebView webView;
private OnPassWebview onPassWebview;

    public void setOnPassWebview(OnPassWebview onPassWebview) {
        this.onPassWebview = onPassWebview;
    }

    private InfoFragment(OnChangeToolbarType onChangeToolbarType) {
        this.onChangeToolbarType = onChangeToolbarType;
        // Required empty public constructor
    }


    public static InfoFragment getInstance(OnChangeToolbarType onChangeToolbarType) {
        if (infoFragment == null) {
            infoFragment = new InfoFragment(onChangeToolbarType);
        }
        return infoFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//         inflater.inflate(R.layout.fragment_info, container, false);
        // 뷰 결합하기 위해 메모리 초기화
        binding = FragmentInfoBinding.inflate(inflater, container, false);
        onChangeToolbarType.onSetupType(Define.PAGE_TITLE_INFO);
        setupWebView();

        return binding.getRoot();
    }

    private void setupWebView() {
      webView = binding.mWebView;
        webView.loadUrl("https://yts.mx/");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                binding.progressIndicator.setVisibility(View.GONE);
            }
        });
        // 자바스크립트 허용코드 넣어줘야함
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        onPassWebview.onPassWebViewObj(webView);
    }

}