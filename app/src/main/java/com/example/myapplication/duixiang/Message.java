package com.example.myapplication.duixiang;

public class Message {
    private String geterId;//消息id,自动添加，非网名

    public String getGeterId() {
        return geterId;
    }

    public String getSender_name() {
        return sender_name;
    }
    private String sender_name;
    private String senderId;//发送人id，非网名
    private String content;//消息内容
    public Message(String geterId, String senderId, String content) {
        this.geterId=geterId;
        this.senderId = senderId;
        this.content = content;
    }

    // Getters
    public String getSenderId() { return senderId; }
    public String getContent() { return content; }
}
