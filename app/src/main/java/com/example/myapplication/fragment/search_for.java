package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class search_for extends Fragment implements Runnable{
    public static Socket socket;
    public static boolean canSpeak = true;
    EditText search1;
    Button search2;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search_for, container, false);
        search1=view.findViewById(R.id.search1);
        search2=view.findViewById(R.id.search2);
        ServerSocket serverSocket = null;
//        try {
//            serverSocket = new ServerSocket(8080);
//            socket = serverSocket.accept();
//            InputStream inputStream = socket.getInputStream();
//            DataInputStream dataInputStream = new DataInputStream(inputStream);
//
//            String message = dataInputStream.readUTF();
//            search1.setText(message);
//            if (message.equals("exit")){
//                search2.setEnabled(false);
//            }
//            dataInputStream.close();
//            inputStream.close();
//            socket.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }


        return view;
    }

    @Override
    public void run() {

    }
}