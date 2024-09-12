package com.example.myapplication.adpter;

import android.app.AlertDialog;
import android.content.Context;
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
import com.example.myapplication.R;
import com.example.myapplication.cunchuxiaoxi;
import com.example.myapplication.duixiang.GuanxiList;
import com.example.myapplication.duixiang.udp;
import com.example.myapplication.socket.UDPClient;

import java.util.List;

public class cunchuadpter extends RecyclerView.Adapter<cunchuadpter.cunchuadpterviewholder>{
    List<GuanxiList> list;
    Context context;

    public cunchuadpter(List<GuanxiList> list , Context context){
        this.list=list;
        this.context=context;
    }
    @NonNull
    @Override
    public cunchuadpter.cunchuadpterviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cunchulayout, parent,false);
        return new cunchuadpterviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cunchuadpter.cunchuadpterviewholder holder, int position) {
        GuanxiList guanxiList=list.get(position);
        holder.cunchuname.setText(guanxiList.getSender_name());
        holder.cunchuuid.setText(guanxiList.getReceiver_Id());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("系统提示")
                        .setMessage("是否查看聊天记录信息")
                        .setPositiveButton("确定", (dialogInterface, i) -> {
                            Intent intent=new Intent(context, cunchuxiaoxi.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("Sender_name", guanxiList.getSender_name());
                            bundle.putString("Sender_Id",guanxiList.getSender_Id());
                            bundle.putString("Receiver_Id",guanxiList.getReceiver_Id());
                            intent.putExtras(bundle);
                            context.startActivity(intent);

                        })
                        .setNegativeButton("拒绝", (dialogInterface, i) -> {

                        })
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class cunchuadpterviewholder extends RecyclerView.ViewHolder{
            TextView cunchuname;
            TextView cunchuuid;
            LinearLayout layout;
        public cunchuadpterviewholder(@NonNull View itemView) {
            super(itemView);
            cunchuname=itemView.findViewById(R.id.cunchuname);
            cunchuuid=itemView.findViewById(R.id.cunchuuid);
            layout=itemView.findViewById(R.id.cunchu_list);
        }
    }
}
