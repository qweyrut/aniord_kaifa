package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.adpter.cunchuadpter;
import com.example.myapplication.adpter.massageadpter;
import com.example.myapplication.duixiang.GuanxiList;
import com.example.myapplication.sqlite.ChatDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class interface_two extends Fragment {
    RecyclerView recyclerView;
    cunchuadpter cunchuadpter1;
    List<GuanxiList> list=new ArrayList();
    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_interface_two, container, false);
        recyclerView=view.findViewById(R.id.huifanglist);
        ChatDatabaseHelper  chatDatabaseHelper=new ChatDatabaseHelper(getContext());
        list=chatDatabaseHelper.fetchConversations();
        cunchuadpter1=new cunchuadpter(list,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cunchuadpter1);
        return view;
    }

}