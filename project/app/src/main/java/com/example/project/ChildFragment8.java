package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class ChildFragment8 extends Fragment {

    public ChildFragment8() {
        // Required empty public constructor
    }

    public static ChildFragment8 newInstance() {
        ChildFragment8 fragment = new ChildFragment8();
               return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child8, container, false);
    }
}