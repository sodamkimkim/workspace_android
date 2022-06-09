package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class ChildFragment5 extends Fragment {

    public ChildFragment5() {
        // Required empty public constructor
    }

    public static ChildFragment5 newInstance() {
        ChildFragment5 fragment = new ChildFragment5();
               return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child5, container, false);
    }
}