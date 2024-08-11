package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.myapplication.fragment.fragment_me;
import com.example.myapplication.fragment.interface_three;
import com.example.myapplication.fragment.interface_two;
import com.example.myapplication.fragment.search_for;

public class main_interface extends AppCompatActivity implements View.OnClickListener {
    FrameLayout fragment_manage;//管理器fragment
    //以下四个为fragment
    fragment_me  fragment_me;
    interface_two interface_two;
    interface_three interface_three;
    search_for search_for;
    //以下四个为按钮
    ImageView search_for_icon;
    ImageView fragment_two_icon;
    ImageView fragment_three_icon;
    ImageView fragment_me_icon;
    //主界面
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_interface);
        fragment_manage=findViewById(R.id.fragment_manage);
        //创建fragment
        search_for=new search_for();
        interface_two=new interface_two();
        interface_three=new interface_three();
        fragment_me=new fragment_me();
        //创建fragment
        //绑定按钮事件
        search_for_icon=findViewById(R.id.search_for_icon);
        search_for_icon.setOnClickListener(this);

        fragment_two_icon=findViewById(R.id.fragment_two_icon);
        fragment_two_icon.setOnClickListener(this);

        fragment_three_icon=findViewById(R.id.fragment_three_icon);
        fragment_three_icon.setOnClickListener(this);

        fragment_me_icon=findViewById(R.id.fragment_me_icon);
        fragment_me_icon.setOnClickListener(this);


        //绑定按钮事件







    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.search_for_icon){
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(R.id.fragment_manage, search_for, null);
            transaction.commit();
        }
        else if (view.getId()==R.id.fragment_two_icon){
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(R.id.fragment_manage, interface_two, null);
            transaction.commit();
        }
        else if (view.getId()==R.id.fragment_three_icon) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(R.id.fragment_manage, interface_three, null);
            transaction.commit();
        }
        else if (view.getId()==R.id.fragment_me_icon) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(R.id.fragment_manage, fragment_me, null);
            transaction.commit();
        }
    }
}
