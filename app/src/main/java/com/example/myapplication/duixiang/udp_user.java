package com.example.myapplication.duixiang;

public class udp_user {
    String username;

    public void setUsername(String username) {
        this.username = username;
    }
    public void setUserip(String userip) {
        this.userip = userip;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    String userip;
    String userid;
    public udp_user(String username, String userip, String userid){
        this.username=username;
        this.userip=userip;
        this.userid=userid;
    }
    public String getUsername() {
        return username;
    }

    public String getUserip() {
        return userip;
    }

    public String getUserid() {
        return userid;
    }
}
