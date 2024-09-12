//package com.example.myapplication.socket;
//
//import android.os.Handler;
//import android.os.Looper;
//import android.util.Log;
//
//import com.example.myapplication.adpter.searchadpter;
//import com.example.myapplication.duixiang.udp_user;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import java.io.IOException;
//import java.net.DatagramPacket;
//import java.net.InetAddress;
//import java.net.MulticastSocket;
//import java.util.List;
//
//public class GetUdp implements Runnable {//实现广播
//    private final InetAddress group;
//    private final List<udp_user> list;
//    private final searchadpter searchadpter;
//    private final int port = 12345;
//    private MulticastSocket socket;
//
//    public GetUdp(List<udp_user> list, searchadpter searchadpter) throws IOException {
//        this.list = list;
//        this.searchadpter = searchadpter;
//        this.group = InetAddress.getByName("224.0.0.1");
//        this.socket = new MulticastSocket(port);
//        this.socket.joinGroup(group);
//        new Thread(this).start(); // 启动当前对象的线程
//    }
//
//    @Override
//    public void run() {
//        try {
//            System.out.println("已加入多播组，等待接收消息...");
//            while (true) {
//                // 创建接收数据包
//                byte[] buffer = new byte[1024*64];
//                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
//
//                // 接收数据包
//                socket.receive(packet);
//                String message = new String(packet.getData(), 0, packet.getLength());
//                Gson gson = new GsonBuilder().create();
//                udp_user user = gson.fromJson(message, udp_user.class); // 转换
//                user.setUserip(packet.getAddress().getHostAddress());
//                // 更新 UI
//                new Handler(Looper.getMainLooper()).post(() -> {
//                    list.add(user);
//                    searchadpter.notifyDataSetChanged();
//                });
//
//                System.out.println("收到消息: " + message);
//            }
//        } catch (IOException e) {
//            e.printStackTrace(); // 记录错误信息
//        } finally {
//            // 确保 socket 被关闭
//            if (socket != null && !socket.isClosed()) {
//                try {
//                    socket.leaveGroup(group);
//                    socket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}