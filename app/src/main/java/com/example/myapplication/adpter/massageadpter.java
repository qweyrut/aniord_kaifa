package com.example.myapplication.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.duixiang.massage;

import java.util.List;

public class massageadpter extends RecyclerView.Adapter <massageadpter.massageadpterviewholder>{
    public List<massage> massageList;
    public massageadpter(List<massage> massageList){//传送数据
        this.massageList=massageList;
    }

    public massageadpterviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType==0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.massagelist2, parent,false);
        }
        else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.massagelist, parent,false);
        }
        return new massageadpterviewholder(view);
    }
    public int getItemViewType(int position) {
        return massageList.get(position).getLayouttype();//修改逻辑
        //receiver_Id来进行判断
    }

    public void onBindViewHolder(@NonNull massageadpterviewholder holder, int position) {
        massage massage=massageList.get(position);
        holder.messageTextView.setText(massage.getMassage());
    }
    @Override
    public int getItemCount() {
        return massageList.size();
    }

    class massageadpterviewholder extends RecyclerView.ViewHolder {
        TextView messageTextView;
        ImageView zhaopian;

        massageadpterviewholder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.xiaoxi);
            zhaopian=itemView.findViewById(R.id.zhaopian);
        }
    }
}
