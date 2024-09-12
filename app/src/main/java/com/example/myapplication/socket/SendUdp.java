//package com.example.myapplication.socket;
//
//import com.example.myapplication.duixiang.udp_user;
//import com.google.gson.Gson;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.InetAddress;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//
//
//public class SendUdp implements Runnable {
//    private static final String MULTICAST_ADDRESS = "224.0.0.1";
//    private static final int PORT = 12345;
//    private static String MESSAGE;
//
//    public SendUdp() {
//        // 启动线程
//        new Thread(this).start();
//    }
//    @Override
//    public void run() {
//        try {
//            Gson gson = new Gson();
//            MESSAGE = gson.toJson(new udp_user("阿帅", InetAddress.getLocalHost().toString(), "56165161681"));
//            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
//            DatagramSocket socket = new DatagramSocket();
//
//            // 创建定时任务
//            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//            Runnable task = () -> {
//                try {
//                    byte[] buffer = MESSAGE.getBytes();
//                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, PORT);
//                    socket.send(packet);
//                    System.out.println("已发送: " + MESSAGE);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            };
//
//            // 定期每30秒发送一次消息
//            scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
//
//            // 保持主线程运行，直到手动停止
//            while (!Thread.currentThread().isInterrupted()) {
//                Thread.sleep(1000); // 每秒检查一次
//            }
//            // 正常关闭
//            scheduler.shutdown();
//            socket.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//}