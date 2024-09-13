package com.example.myapplication.socket;
import com.example.myapplication.duixiang.udp;
import com.google.gson.Gson;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient implements Runnable{
    //用于传输发送连接请求

    private static final int SERVER_PORT = 9876; // 服务器端口
    udp message;
    String serverIp;
    public UDPClient(udp message, String serverIp){
        this.message=message;
        this.serverIp=serverIp;
    }
    public void sendMessage(udp message, String serverIp) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            Gson gson=new Gson();
            String massage=gson.toJson(message);

            // 将消息转换为字节数组
            byte[] sendData = massage.getBytes();

            // 获取服务器地址
            InetAddress serverAddress = InetAddress.getByName(serverIp);

            // 创建数据包
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);

            // 发送数据包
            socket.send(sendPacket);
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
        sendMessage(message,serverIp);
    }
}
