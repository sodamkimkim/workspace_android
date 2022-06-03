package com.example.movie_1.interfaces;


import android.webkit.WebView;

// 뒤로가기 눌렀을 때 이벤트 처리를 해야한다.
public interface OnHistoryGoBack {
    void onHistory(WebView webView);
}
