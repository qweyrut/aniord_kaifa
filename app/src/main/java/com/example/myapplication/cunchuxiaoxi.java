package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.adpter.massageadpter;
import com.example.myapplication.duixiang.Message;
import com.example.myapplication.sqlite.chat_sqlite;

import java.util.List;

public class cunchuxiaoxi extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView imageView;
    List<Message> messages;
    massageadpter xiaoxi;
    public static String senduid;//自己的uid
    public static   String getname;//别人的名字
    public static String getuid;//别人的uid
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cunchuxiaoxi);
        recyclerView=findViewById(R.id.xiaoxiliebiao12);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        getname=bundle.getString("Sender_name");
        senduid=bundle.getString("Sender_Id");
        getuid=bundle.getString("Receiver_Id");
        imageView=findViewById(R.id.danhui1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        chat_sqlite chat_sqlite1=new chat_sqlite(this);
        messages=chat_sqlite1.fetchMessages(getuid,senduid);
        xiaoxi=new massageadpter(messages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(xiaoxi);

    }
}