package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.duixiang.userdata;
import com.example.myapplication.sqlite.Login_inf;

//67行跳转至主页面
public class login extends AppCompatActivity {
    private Boolean login_state=false;
    private TextView mregister;
    private EditText name,pass;
    private Button mconfirm;
    private userdata data;
    private CheckBox checkBox;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        name = findViewById(R.id.username123);
        pass = findViewById(R.id.password123);
        mconfirm = findViewById(R.id.btn_confirm);
        mregister = findViewById(R.id.btn_register);
        checkBox=findViewById(R.id.cb_rm);
        data=new userdata(this);
        initLogin();
        mregister.setOnClickListener(v -> {
            {
                if(data.getRegister(name.getText().toString(),pass.getText().toString())){
                    Toast.makeText(login.this,"注册成功！",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(login.this,"注册失败！用户名重复或者为空！",Toast.LENGTH_SHORT).show();
                }
            }

        });
        mconfirm.setOnClickListener(v -> {
            if(data.verifyPassword(name.getText().toString(),pass.getText().toString())&&data.search(name.getText().toString()))
            {
                Login_inf login_inf=new Login_inf(this);
                String uid=login_inf.getUIDByUsername(name.getText().toString());
                Application1.senduid=uid;
                Application1.sendname=name.getText().toString();
                Application1.sendpassword=pass.getText().toString();
                Intent intent=new Intent(login.this, main_interface.class);
                saveLogin(login_state);
                startActivity(intent);
                Toast.makeText(login.this,name.getText().toString()+"欢迎您！",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(login.this,"登陆失败！请检查用户是否存在或者密码错误！",Toast.LENGTH_SHORT).show();
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                login_state = true;
            }
        });
    }
    private void saveLogin(boolean flag)
    {
        SharedPreferences sharedPreferences = login.this.getSharedPreferences("ACCOUNT_REMEMBER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String secreat_name = name.getText().toString();
        String secreat_password=pass.getText().toString();
        if(sharedPreferences.getBoolean("flag",false))
        {
            return;
            //验证通过，不需要再次保存了
        }
        if(flag) {
            editor.putString("name",secreat_name);
            editor.putString("password", secreat_password);
            editor.putBoolean("flag", flag);
            editor.apply();
        }
        else{
            editor.clear();
            editor.putString("name",secreat_name);
            editor.putBoolean("flag",false);
            editor.apply();
        }

    }
    private void initLogin() {
        SharedPreferences sharedPreferences=login.this.getSharedPreferences("ACCOUNT_REMEMBER", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("flag",false)){
            String decode_password=sharedPreferences.getString("password","");
            name.setText(sharedPreferences.getString("name",""));
            pass.setText(decode_password);
            checkBox.setChecked(true);
        }
        else {
            name.setText(sharedPreferences.getString("name",""));
            checkBox.setChecked(false);
        }
    }

}
