package com.example.myapplication.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.Service.Serverservice;
import com.example.myapplication.adpter.massageadpter;
import com.example.myapplication.duixiang.Message;
import com.example.myapplication.duixiang.massage;
import com.example.myapplication.socket.ChatClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class interface_two extends Fragment implements View.OnClickListener{
    RecyclerView recyclerView;
    massageadpter massageadpter;
    EditText massageedit;
    Button sendmassage;
    Button startservice;
    String sendmessage="";
    ChatClient chatClient;
    Thread interface_two;
    List<massage> messages=new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_interface_two, container, false);
        recyclerView=view.findViewById(R.id.xiaoxiliebiao);//消息列表
        massageedit=view.findViewById(R.id.massageedit);//发送消息栏
        sendmassage=view.findViewById(R.id.sendmassage);//发送消息按钮
        startservice=view.findViewById(R.id.startservice);//开启服务器端
        startservice.setOnClickListener(this);
        sendmassage.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));









        messages.add(new massage("你好！", 0, "我"));
        messages.add(new massage("你好，有什么事吗？", 1, "他人A"));
        messages.add(new massage("我在学习 Android 开发。", 0, "我"));
        messages.add(new massage("那很好！加油！", 1, "他人B"));
        messages.add(new massage("今天的天气真不错。", 0, "我"));
        messages.add(new massage("我也觉得。", 1, "他人C"));
        messages.add(new massage("你吃了吗？", 0, "我"));
        messages.add(new massage("还没呢，你呢？", 1, "他人D"));
        messages.add(new massage("我刚吃完。", 0, "我"));
        messages.add(new massage("想吃点什么？", 1, "他人E"));
        messages.add(new massage("我想吃披萨。", 0, "我"));
        messages.add(new massage("不错的选择！", 1, "他人F"));
        massageadpter=new massageadpter(messages);
        recyclerView.setAdapter(massageadpter);
        recyclerView.scrollToPosition(messages.size()-1);
        chatClient=new ChatClient("10.245.240.82",8888,messages,massageadpter,recyclerView);//开启线程
        new Thread(chatClient).start();//开启支线程，获取信息
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.sendmassage){
//            if (!massageedit.equals("")){
//                messages.add(new massage(massageedit.getText().toString(), 0, "我"));
//                massageadpter.notifyDataSetChanged();
//            }
            sendmessage= massageedit.getText().toString();
            massageedit.setText("");//编辑栏清空
            Log.e("interface_two",sendmessage);
            new Thread(this::sendMessageAndClear).start();







//            massageadpter.notifyDataSetChanged();
//            recyclerView.scrollToPosition(messages.size() - 1);
        }
        if (view.getId()==R.id.startservice){
            Intent intent=new Intent(getActivity(),Serverservice.class);
            requireActivity().startService(intent);
        }
    }
    private void sendMessageAndClear() {
        try {
            chatClient.sendmessage(sendmessage);
            Log.e("interface_two", "发送消息为" + sendmessage);
        } catch (Exception e) {
            Log.e("interface_two", "发送消息失败: " + e.getMessage());
        } finally {
            sendmessage = ""; // 清空消息
        }
    }
}