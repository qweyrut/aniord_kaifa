package com.example.myapplication.socket;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer  implements Runnable {
     private final int port=8888;
     ServerSocket serverSocket;//服务端
     Socket socket;//获取到的客户端
     DataOutputStream out;
     public static List<Socket> list=new ArrayList();//获取到的客户端socket集合,作为全局变量
     DataInputStream in;
     public void close() throws IOException {
         in.close();
         out.close();
         socket.close();
     }
     public void startserver(){
         new Thread(new ChatServer()).start();
     }

    public void run() {
        try {
            serverSocket=new ServerSocket(port);
            while (true){
                socket=serverSocket.accept();
                list.add(socket);
                Log.e("服务器端:","当前存在"+list.size()+"个用户");
                new Thread(new serverthread(socket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class serverthread extends Thread {
    public Socket socket;
    public serverthread(Socket socket){
        this.socket=socket;
    }
    public void run(){

        try {
            DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());//获取到客户端传来的信息
            while (true){
            String message=dataInputStream.readUTF();//获取到的信息
                Log.e("ChatServer","获取到的消息为"+message);
                for (Socket socket_kehu:ChatServer.list){//传输信息给所有处于集合客户端
                    DataOutputStream dataOutputStream=new DataOutputStream(socket_kehu.getOutputStream());
                    dataOutputStream.writeUTF(message);
                    dataOutputStream.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }






}
