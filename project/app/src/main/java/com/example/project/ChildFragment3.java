package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class ChildFragment3 extends Fragment {

    public ChildFragment3() {
        // Required empty public constructor
    }

    public static ChildFragment3 newInstance() {
        ChildFragment3 fragment = new ChildFragment3();
               return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child3, container, false);
    }
}