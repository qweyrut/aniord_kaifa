//package com.example.myapplication.test;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.example.myapplication.R;
//import com.example.myapplication.adpter.massageadpter;
//import com.example.myapplication.duixiang.massage;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MainActivitytest2 extends AppCompatActivity implements View.OnClickListener,Runnable{
//    RecyclerView recyclerView;
//    com.example.myapplication.adpter.massageadpter massageadpter;
//    EditText massageedit;
//    Button sendmassage;
//    Button fanhui;
//    Socket socket;//客户端获取到的服务端
//    DataOutputStream dataOutputStream;
//    DataInputStream dataInputStream;
//
//    DataInputStream dataInputStream1;
//    Socket socket1;//服务端获取到的客户端
//    ServerSocket serverSocket;//服务端
//    EditText massageedit2;
//
//    List<massage> messages=new ArrayList<>();
//    @SuppressLint("MissingInflatedId")
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_activitytest2);
//        recyclerView=findViewById(R.id.xiaoxiliebiao1);//消息列表
//        massageedit=findViewById(R.id.massageedit1);//发送消息栏
//        massageedit2=findViewById(R.id.massageedit2);
//        sendmassage=findViewById(R.id.sendmassage1);//发送消息按钮
//        fanhui=findViewById(R.id.sendmassage2);
//        fanhui.setOnClickListener(this);
//        sendmassage.setOnClickListener(this);
//        Thread thread=new Thread(new MyRunnable1());
//        thread.start();
//        new Thread(new MainActivitytest2()).start();
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        massageadpter=new massageadpter(messages);
//        recyclerView.setAdapter(massageadpter);
//        recyclerView.scrollToPosition(messages.size() - 1);
//    }
//
//    @Override
//    public void onClick(View view) {
//        if (view.getId() == R.id.sendmassage1) {
//            String messageText = massageedit.getText().toString();
//            if (!messageText.isEmpty()) {
//                messages.add(new massage(messageText, 2, "ta"));
//                massageadpter.notifyDataSetChanged();
//                recyclerView.scrollToPosition(messages.size() - 1);
//                try {
//                    new Thread(() -> {
//                        try {
//                            dataOutputStream.writeUTF(messageText);
//                            dataOutputStream.flush();
//                        } catch (IOException e) {
//                            Log.e("MainActivity", "Failed to send message: " + e.getMessage());
//                        }
//                    }).start();
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
////            messages.add(new massage("我是测试案例", 0, "我"));
////            messages.add(new massage("我是测试案例1", 1, "我"));
//            recyclerView.scrollToPosition(messages.size() - 1);
//        } else if (view.getId()==R.id.sendmassage2) {
////            Intent intent=new Intent(MainActivitytest2.this,MainActivitytest.class);
////            startActivity(intent);
//        }
//    }
//
//    @Override
//    public void run() {
//        try {//客户端
//            socket=new Socket("localhost",1234);
//            dataOutputStream=new DataOutputStream(socket.getOutputStream());
//            dataInputStream=new DataInputStream(socket.getInputStream());
//            if (dataOutputStream!=null){
//                Log.e("djaoiwfbi","dataOutputStream is not null");
//            }
//            dataOutputStream.writeUTF("obaowbfoibawifobibawf");
//            dataOutputStream.flush();
//            while (true){
//            String string=dataInputStream.readUTF();
//            if (!string.equals("")){
//                messages.add(new massage(string, 0, "他"));
//                massageadpter.notifyDataSetChanged();
//                recyclerView.scrollToPosition(messages.size() - 1);
//            }
//            else {
//                break;
//            }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//     class MyRunnable1 implements Runnable {
//        @Override
//        public void run() {
//            try {
//                serverSocket=new ServerSocket(1234);
//                socket1=serverSocket.accept();
//                dataInputStream1=new DataInputStream(socket1.getInputStream());
//                while (true){
//                    String strings=dataInputStream1.readUTF();
//                    if (strings.equals("")){
//                        break;
//                    }
//                    massageedit2.setText(strings);
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}