package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class ChildFragment7 extends Fragment {

    public ChildFragment7() {
        // Required empty public constructor
    }

    public static ChildFragment7 newInstance() {
        ChildFragment7 fragment = new ChildFragment7();
               return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child7, container, false);
    }
}