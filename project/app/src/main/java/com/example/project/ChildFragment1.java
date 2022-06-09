package com.example.project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ChildFragment1 extends Fragment {

    public ChildFragment1() {
        // Required empty public constructor
    }

    public static ChildFragment1 newInstance() {
        ChildFragment1 fragment = new ChildFragment1();
               return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_child1, container, false);
    }
}