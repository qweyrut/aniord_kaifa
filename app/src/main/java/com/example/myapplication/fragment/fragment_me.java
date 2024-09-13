package com.example.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Application1;
import com.example.myapplication.ModifyActivity;
import com.example.myapplication.R;
import com.example.myapplication.login;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

//我的主页//
public class fragment_me extends Fragment {
    EditText ceshi1;
    Button ceshi;
    public static Socket socket;
    static boolean issend=false;
Context context;
    ImageView modify;
    Button exit;
    String name;
    String password;

    TextView oldname;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_me, container, false);
        modify = view.findViewById(R.id.modify);
        exit = view.findViewById(R.id.btn_exit);
        oldname = (TextView)view.findViewById(R.id.oldname);
        name=Application1.sendname;
        password=Application1.sendpassword;
        oldname.setText(name);
        modify.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(), ModifyActivity.class);

            startActivity(intent);
        });
        exit.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(), login.class);

            startActivity(intent);
        });

        return view;
    }

}