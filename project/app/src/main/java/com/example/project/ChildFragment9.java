package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


public class ChildFragment9 extends Fragment {

    public ChildFragment9() {
        // Required empty public constructor
    }

    public static ChildFragment9 newInstance() {
        ChildFragment9 fragment = new ChildFragment9();
               return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child9, container, false);
    }
}