package com.example.myapplication.socket;

import com.example.myapplication.duixiang.udp_user;
import com.google.gson.Gson;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.google.gson.Gson;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SendBroadcast implements Runnable {
    private static final String BROADCAST_ADDRESS = "255.255.255.255"; // 广播地址
    private static final int PORT = 12354; // 端口号
    private static String MESSAGE;
    public SendBroadcast() {
       new Thread(this).start();
    }



    @Override
    public void run() {
        try {
            Gson gson = new Gson();
            MESSAGE = gson.toJson(new udp_user("阿帅", "", "56165161681"));
            DatagramSocket socket = new DatagramSocket();
            // 创建定时任务
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            Runnable task = () -> {
                try {
                    socket.setBroadcast(true); // 允许广播
                    byte[] buffer = MESSAGE.getBytes();
                    InetAddress broadcastAddress = InetAddress.getByName(BROADCAST_ADDRESS);
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, broadcastAddress, PORT);
                    socket.send(packet);

                } catch (Exception e){
                    e.printStackTrace();
                }
            };

            // 定期每30秒发送一次消息
            scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);

            // 保持主线程运行，直到手动停止
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(1000); // 每秒检查一次
            }
            // 正常关闭
            scheduler.shutdown();
            socket.close();
            System.out.println("Broadcast message sent: " + MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
