package com.example.myapplication.socket;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.myapplication.adpter.searchadpter;
import com.example.myapplication.duixiang.udp_user;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.InetAddress;
import java.util.List;

public class ReceiveBroadcast implements Runnable {
    private static final int PORT = 12354; // 端口号
    private MulticastSocket socket;
    private List<udp_user> list;
    private  searchadpter searchadpter;
    public ReceiveBroadcast(List<udp_user> list, searchadpter searchadpter) {
        this.list=list;
        this.searchadpter=searchadpter;
        new Thread(this).start();
    }
    @Override
    public void run() {
        try {
            socket = new MulticastSocket(PORT);
//            InetAddress group = InetAddress.getByName("255.255.255.255"); // 广播组地址
//            socket.joinGroup(group);

            System.out.println("Listening for broadcast messages...");
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());

                Gson gson = new GsonBuilder().create();
                udp_user user = gson.fromJson(message, udp_user.class); // 转换
                user.setUserip(packet.getAddress().getHostAddress());
                Log.e("RecevieBroadcast",user.getUserip());
                new Handler(Looper.getMainLooper()).post(() -> {
                    Boolean panduan=true;//判断是否存在
                    for (udp_user li:list){
                        if (li.getUserip().equals(user.getUserip())){
                            panduan=false;
                            break;
                        }
                    }
                    if (panduan){
                        list.add(user);
                        searchadpter.notifyDataSetChanged();
                    }

                });

                System.out.println("Received broadcast message: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.leaveGroup(InetAddress.getByName("255.255.255.255"));
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
