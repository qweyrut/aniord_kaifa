package com.example.myapplication.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.socket.ChatServer;

import java.io.IOException;

public class Serverservice extends Service {
    private final IBinder binder = new LocalBinder();
    private ChatServer chatServer;

    public class LocalBinder extends Binder {
        Serverservice getService() {
            return Serverservice.this; // 返回当前服务实例
        }
    }
    public void onCreate() {
        super.onCreate();
        chatServer=new ChatServer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        chatServer.startserver();//打开服务端，并接受信息
        Log.e("klwnfanfpawf","服务器开启成功");
        return START_STICKY; // 表示服务在被系统杀死后会自动重启
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        try {
//            chatServer.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null; // 如果不需要绑定，返回 null
    }

}
