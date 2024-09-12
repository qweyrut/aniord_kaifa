package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.adpter.massageadpter;
import com.example.myapplication.duixiang.Message;
import com.example.myapplication.duixiang.massage;
import com.example.myapplication.socket.ChatClient;
import com.example.myapplication.sqlite.ChatDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    RecyclerView recyclerView;
    com.example.myapplication.adpter.massageadpter massageadpter;
    EditText massageedit;
    Button sendmassage;
    Button startservice;
    String sendmessage="";
    ChatClient chatClient;
    String ip;//获取IP地址
    String name;//获取对方的网名
    String nameid;//获取对方的uid
    Thread interface_two;
    TextView gukename;
    Bundle bundle;
    List<Message> messages=new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.xiaoxiliebiao);//消息列表
        massageedit=findViewById(R.id.massageedit);//发送消息栏
        sendmassage=findViewById(R.id.sendmassage);//发送消息按钮
        startservice=findViewById(R.id.startservice);//开启服务器端
        gukename=findViewById(R.id.gukename);
        startservice.setOnClickListener(this);
        sendmassage.setOnClickListener(this);
        Intent intent=getIntent();
        bundle=intent.getExtras();
        ip=bundle.getString("ip");
        name=bundle.getString("name");
        nameid=bundle.getString("nameid");

        ChatDatabaseHelper dbhp = new ChatDatabaseHelper(this);
        dbhp.insertConversation(Application1.getname,Application1.getuid,Application1.senduid);

        gukename.setText(name);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        massageadpter=new massageadpter(messages);
        recyclerView.setAdapter(massageadpter);
        recyclerView.scrollToPosition(messages.size()-1);
        if (!ip.equals("")){
            chatClient=new ChatClient(ip,8888,messages,massageadpter,recyclerView,this);//开启线程
            new Thread(chatClient).start();//开启支线程，获取信息
        }
    }
    public void onClick(View view) {
        if (view.getId()==R.id.sendmassage){
            sendmessage= massageedit.getText().toString();
            massageedit.setText("");//编辑栏清空
            Log.e("interface_two",sendmessage);
            new Thread(this::sendMessageAndClear).start();
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