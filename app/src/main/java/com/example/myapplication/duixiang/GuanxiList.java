package com.example.myapplication.duixiang;

public class GuanxiList {
    private String sender_name;

    public String getSender_name() {
        return sender_name;
    }

    public String getSender_Id() {
        return sender_Id;
    }

    public String getReceiver_Id() {
        return receiver_Id;
    }

    private  String sender_Id;
    private  String receiver_Id;
    public GuanxiList(String sender_name,String sender_Id,String receiver_Id){
        this.receiver_Id=receiver_Id;
        this.sender_Id=sender_Id;
        this.sender_name=sender_name;
    }








}
