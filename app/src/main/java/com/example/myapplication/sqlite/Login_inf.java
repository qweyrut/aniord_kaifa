package com.example.myapplication.sqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class Login_inf extends SQLiteOpenHelper {
    private static final String Database_NAME = "userdata.db";
    private static final int Database_VERSION = 1;
    public Login_inf(Context context) {
        super(context, Database_NAME, null,  Database_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, password TEXT NOT NULL,UID TEXT NOT NULL)";
        db.execSQL(CREATE_TABLE);
        Log.d("Database", "Table created successfully");
    }
    @SuppressLint("Range")
    public String getUIDByUsername(String username) {//获取uid
        SQLiteDatabase db = this.getReadableDatabase();
        String uid = null;

        // 查询的 SQL 语句
        String query = "SELECT UID FROM users WHERE username = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                uid = cursor.getString(cursor.getColumnIndex("UID"));
            }
            cursor.close(); // 关闭游标
        }

        db.close(); // 关闭数据库
        return uid; // 返回 UID 或 null
    }
    public void updatePasswordByUsername(String username, String newPassword) {//通过name更新password
        SQLiteDatabase db = this.getWritableDatabase();
        String UPDATE_PASSWORD = "UPDATE users SET password = ? WHERE username = ?";

        SQLiteStatement statement = db.compileStatement(UPDATE_PASSWORD);
        statement.bindString(1, newPassword);
        statement.bindString(2, username);

        int rowsAffected = statement.executeUpdateDelete();
        if (rowsAffected > 0) {
            Log.d("Database", "Password updated successfully for user: " + username);
        } else {
            Log.d("Database", "No user found with username: " + username);
        }

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

}
