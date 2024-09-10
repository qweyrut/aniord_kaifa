package com.example.myapplication.duixiang;

public class massage {
    private String massage;//消息
    private int layouttype;//属于谁的;0代表自己，1代表他人
    private String id;//姓名


    public massage(String message, int layoutType, String id) {
        this.massage = message;
        this.layouttype = layoutType;
        this.id = id;
    }
    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public int  getLayouttype() {
        return layouttype;
    }

    public void setLayouttype(int layouttype) {
        this.layouttype = layouttype;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
