package com.example.myapplication.adpter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Application1;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.duixiang.udp;
import com.example.myapplication.duixiang.udp_user;
import com.example.myapplication.main_interface;
import com.example.myapplication.socket.UDPClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class searchadpter extends RecyclerView.Adapter<searchadpter.searchadpterviewholder>{
   public static List<udp_user>  list;
    public searchadpter(List<udp_user>  list){
        this.list=list;
    }

    @NonNull
    @Override
    public searchadpter.searchadpterviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchlist, parent,false);
        return new searchadpterviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchadpter.searchadpterviewholder holder, int position) {
        udp_user user=list.get(position);
       holder.user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(main_interface.context);
                builder.setTitle("系统提示").setMessage("是否发送连接请求").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        udp message =new udp(Application1.sendname, Application1.senduid,"我想和你进行连接66666666666");
                        UDPClient udpClient=new UDPClient(message,user.getUserip());
                        new Thread(udpClient).start();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
        holder.username.setText(user.getUsername());
        holder.userip.setText(user.getUserip());
        holder.userid.setText(user.getUserid());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class searchadpterviewholder extends RecyclerView.ViewHolder{
        TextView username;
        TextView userip;
        TextView userid;
        LinearLayout user;

        public searchadpterviewholder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            userip=itemView.findViewById(R.id.userip);
            userid=itemView.findViewById(R.id.userid);
            user=itemView.findViewById(R.id.user);
        }
    }
}

