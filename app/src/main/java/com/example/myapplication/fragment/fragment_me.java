package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;

//我的主页//
public class fragment_me extends Fragment {
    TextView ceshi1;
    Button ceshi;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_me, container, false);
        ceshi=view.findViewById(R.id.ceshi);
        ceshi1=view.findViewById(R.id.ceshi1);
        ceshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ceshi1.setTextSize(50);
            }
        });
        return view;
    }
}