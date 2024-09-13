package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.sqlite.Login_inf;

public class ModifyActivity extends AppCompatActivity {
    private EditText name;
    private EditText pass;
    private Button ok;

    private String sname;

    private String spassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        name = findViewById(R.id.name_two);
        pass = findViewById(R.id.password_two);
        ok = findViewById(R.id.btn_ok);
        sname=Application1.sendname;
        spassword=Application1.sendpassword;
        name.setText(sname);
        pass.setText(spassword);

        ok.setOnClickListener(v -> {
            {
                Intent intent=new Intent(ModifyActivity.this, main_interface.class);
                Login_inf login_inf=new Login_inf(this);
                login_inf.updatePasswordByUsername(name.getText().toString(),pass.getText().toString());
                Application1.sendpassword=pass.getText().toString();
                startActivity(intent);
            }

        });
    }
}