package com.example.myapplication.socket;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.Application1;
import com.example.myapplication.MainActivity;
import com.example.myapplication.duixiang.udp;
import com.example.myapplication.duixiang.udp_user;
import com.example.myapplication.main_interface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class UDPServer implements Runnable {
///用于接受连接邀请
    private static final int SERVER_PORT = 9876; // 服务器端口
    private DatagramSocket socket;
    private final Context context;

    public UDPServer(Context context) {
        this.context = context; // 使用应用上下文
    }

    public void start1() {
        try {
            socket = new DatagramSocket(SERVER_PORT);
            byte[] receiveData = new byte[1024]; // 接收缓冲区

            while (true) {
                // 创建数据包
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // 接收数据包
                socket.receive(receivePacket);

                // 创建一个新线程处理请求
                new Thread(new RequestHandler(receivePacket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    @Override
    public void run() {
        start1();
    }

    private class RequestHandler implements Runnable {
        private final DatagramPacket receivePacket;
        private final Handler handler;
        String ip;

        public RequestHandler(DatagramPacket receivePacket) {
            this.receivePacket = receivePacket;
            this.handler = new Handler(Looper.getMainLooper()); // 在主线程中使用Handler
        }

        @Override
        public void run() {
            try {
                // 将接收到的数据转换为字符串
                ip="localhost";
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                Gson gson = new GsonBuilder().create();
                udp user = gson.fromJson(message, udp.class); // 转换
                handler.post(() -> {
                    if (user.content1.equals("我想和你进行连接66666666666")) {
                        showDialog(user);
                    } else if (user.content1.equals("我确定和你进行连接66666666")) {
                        startMainActivity(receivePacket.getAddress().getHostAddress(),user);
                    } else if (user.content1.equals("我拒绝和你进行连接66666666")) {
                        Toast.makeText(context, "对方拒绝了你的连接", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void showDialog(udp user) {
            new AlertDialog.Builder(context)
                    .setTitle("系统提示")
                    .setMessage("是否接受请求")
                    .setPositiveButton("确定", (dialogInterface, i) -> {
                        udp message =new udp(Application1.sendname,Application1.senduid,"我确定和你进行连接66666666");//要获取自己的name和id，用数据库
                        UDPClient udpClient = new UDPClient(message, receivePacket.getAddress().getHostAddress());
                        new Thread(udpClient).start();
                        startMainActivity(ip,user);//这个要传送信息
                    })
                    .setNegativeButton("拒绝", (dialogInterface, i) -> {
                        udp message =new udp(Application1.sendname,Application1.senduid,"我拒绝和你进行连接66666666");//要获取自己的name和id,用数据库
                        UDPClient udpClient = new UDPClient(message, receivePacket.getAddress().getHostAddress());
                        new Thread(udpClient).start();
                    })
                    .show();
        }

        private void startMainActivity(String ip,udp user) {
            Intent intent = new Intent(context, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("ip", ip);
            bundle.putString("nameid",user.nameid);
            bundle.putString("name",user.name);
            Application1.getuid=user.nameid;
            Application1.getname=user.name;
            intent.putExtras(bundle);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 添加这个标志以在非Activity上下文中启动Activity
            context.startActivity(intent);
        }
    }
}