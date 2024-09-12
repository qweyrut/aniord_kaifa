package com.example.myapplication.socket;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adpter.massageadpter;
import com.example.myapplication.duixiang.massage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatClient implements Runnable{
    public  static   Socket socket;//获取服务端的socket实例
    public  static int port;
    public static  String ip;
    public  static String getmessage;//取得的消息
    public  static  RecyclerView recyclerView;
    public static  List<massage> messages;//实现获取消息列表实例
    public  static  massageadpter massageadpter;

    public static DataOutputStream dataOutputStream;//获取到的输入实例
    public static DataInputStream dataInputStream;//获取到的输出实例
    public ChatClient(String ip, int port,List<massage> messages,massageadpter massageadpter,RecyclerView recyclerView){//获取到ip地址和端口号
        this.ip=ip;
        this.port=port;
        this.messages=messages;
        this.massageadpter=massageadpter;
        this.recyclerView=recyclerView;
    }

    public boolean connectsocket() throws IOException {//连接服务端
        socket=new Socket(ip,port);
        dataOutputStream=new DataOutputStream(socket.getOutputStream());
        dataInputStream=new DataInputStream(socket.getInputStream());
        if (socket!=null&&dataInputStream!=null&&dataOutputStream!=null){
            return true;
        }
        else {
            return false;
        }
    }
    public void close() throws IOException {//关闭
        dataOutputStream.close();
        dataInputStream.close();
        socket.close();
    }
    public void sendmessage(String message){//客户端向服务端发送
        try {
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public String receivemessage() throws IOException {//服务端向客户端发送
        String getmessage;
        getmessage=dataInputStream.readUTF();
        return getmessage;
    }
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(2000);
                if (connectsocket()){//判断是否连接成功
                    break;
                }
            } catch (Exception e) {
                Log.e("ChatClient","连接失败");
            }
        }
        Log.e("ChatClient","连接成功");
        while (true){
            try {
                Thread.sleep(2000);
                Log.e("ChatClient","正在运行接收消息");
                getmessage=receivemessage();
                massage massage=new massage(getmessage,1,"jfbiuawvfuiwaf");
                new Handler(Looper.getMainLooper()).post(() -> {
                    messages.add(massage);
                    massageadpter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(messages.size() - 1);
                });

                Log.e("ChatClient","获取到的消息为:"+getmessage);
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }
}
