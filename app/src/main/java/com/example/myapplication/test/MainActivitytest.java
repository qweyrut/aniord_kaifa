//package com.example.myapplication.test;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import com.example.myapplication.R;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class MainActivitytest extends AppCompatActivity implements View.OnClickListener,Runnable{
//    Button tiaozhuan;
//    Button send;
//    EditText sentmassage;
//    EditText getmassage;
//    ServerSocket serverSocket;
//    public static Socket socket=null;
//    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_activitytest);
//        tiaozhuan=findViewById(R.id.tiaozhuan);
//        send=findViewById(R.id.send);
//        send.setOnClickListener(this);
//        tiaozhuan.setOnClickListener(this);
//        sentmassage=findViewById(R.id.sentmessage);
//        getmassage=findViewById(R.id.getmessage);
//
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
//                    1);
//        }
//    }
//
//    @Override
//    public void onClick(View view) {
//        if (view.getId()==R.id.tiaozhuan){
//            Intent intent=new Intent(MainActivitytest.this,MainActivitytest2.class);
//            startActivity(intent);
//
//        } else if (view.getId()==R.id.send) {
//            String string=sentmassage.getText().toString();
//            try {
//
//                if (!string.equals("")){
//                    DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
//                    dataOutputStream.writeUTF(string);
//                    dataOutputStream.flush();
//                }
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

//    @Override
//    public void run() {
//        try {
//                serverSocket=new ServerSocket(1234);
//                socket=serverSocket.accept();
//
//            DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
//            while (true){
//                String string=dataInputStream.readUTF();
//                if (string.equals("exit")){
//                    break;
//                }
//                String string1=getmassage.getText().toString();
//                getmassage.setText(string+string1);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}