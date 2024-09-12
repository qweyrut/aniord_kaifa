package com.example.myapplication.fragment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.myapplication.R;
import com.example.myapplication.adpter.searchadpter;
import com.example.myapplication.duixiang.udp_user;
import com.example.myapplication.socket.ReceiveBroadcast;
import com.example.myapplication.socket.SendBroadcast;
import com.example.myapplication.socket.UDPServer;
import java.util.ArrayList;
import java.util.List;

public class search_for extends Fragment implements Runnable{
    Button search1;
    Button search;
//    GetUdp get_udp;//准备废弃
    ReceiveBroadcast receiveBroadcast;
//    SendUdp send_udp;//准备废弃
    SendBroadcast sendBroadcast;
    UDPServer udpServer;
    searchadpter searchadpter;
    RecyclerView udp_userview;
    List<udp_user> list=new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search_for, container, false);
        search1=view.findViewById(R.id.searchuser);
        udp_userview=view.findViewById(R.id.searchlist);
        udp_userview.setLayoutManager(new LinearLayoutManager(getContext()));
        searchadpter=new searchadpter(list);
        udp_userview.setAdapter(searchadpter);
//        send_udp=new SendUdp();
        sendBroadcast=new SendBroadcast();

        udpServer=new UDPServer(requireActivity());
        new Thread(udpServer).start();
        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (receiveBroadcast==null){
                    receiveBroadcast=new ReceiveBroadcast(list,searchadpter);
//                        get_udp=new GetUdp(list,searchadpter);

                }
            }
        });
        return view;
    }
    @Override
    public void run() {

    }
}