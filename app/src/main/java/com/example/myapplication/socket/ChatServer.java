package com.example.myapplication.socket;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.main_interface;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ChatServer  implements Runnable {
    //聊天服务器
     private final int port=8888;
     public  Context context;
     ServerSocket serverSocket;//服务端
    public ChatServer( Context context){
        this.context=context;
    }
     Socket socket;//获取到的客户端
     DataOutputStream out;

     public static List<Socket> list=new ArrayList();//获取到的客户端socket集合,作为全局变量
     DataInputStream in;
     public void close() throws IOException {
         if (in!=null&&out!=null&&socket!=null){
             in.close();
             out.close();
             socket.close();
         }
     }
     public void startserver(){
         new Thread(this).start();
     }

    public void run() {
        try {
            serverSocket=new ServerSocket(port);
            while (true){
                socket=serverSocket.accept();
                list.add(socket);
                Log.e("服务器端:","当前存在"+list.size()+"个用户");
                new Thread(new serverthread(socket,context)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class serverthread extends Thread {
    public Socket socket;
    Context context;
    public serverthread(Socket socket ,Context  context){
        this.socket=socket;
        this.context=context;
    }
    public void run(){

        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream()); // 获取到客户端传来的信息
            while (true) {
                try {
                    String message = dataInputStream.readUTF(); // 获取到的信息
                    Log.e("ChatServer", "获取到的消息为" + message);

                    // 传输信息给所有处于集合客户端
                    for (Socket socket_kehu : ChatServer.list) {
                        DataOutputStream dataOutputStream = new DataOutputStream(socket_kehu.getOutputStream());
                        dataOutputStream.writeUTF(message);
                        dataOutputStream.flush();
                    }
                } catch (IOException e) {
                    Toast.makeText(context, "读取消息时出错，客户端关闭", Toast.LENGTH_SHORT).show();
                    break; // 退出循环
                }
            }
        } catch (IOException e) {
            Log.e("ChatServer", "客户端关闭");
        }
    }
}
