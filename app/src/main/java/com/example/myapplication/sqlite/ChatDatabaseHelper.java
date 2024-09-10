package com.example.myapplication.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.duixiang.GuanxiList;

import java.util.ArrayList;
import java.util.List;

public class ChatDatabaseHelper extends SQLiteOpenHelper {//这个数据库存储发送者和被发送者的关系，以此传输发送者和被发送者id给聊天数据库
    private static final String DATABASE_NAME = "chat_people.db";
    private static final int DATABASE_VERSION = 1;

    public ChatDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建会话表，仅包含发送者和接收者 ID
        String createConversationsTable = "CREATE TABLE conversations (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "sender_name TEXT NOT NULL,"+
                "sender_id TEXT NOT NULL, " +
                "receiver_id TEXT NOT NULL)";
        db.execSQL(createConversationsTable);
    }
    public void insertConversation(String senderName, String senderId, String receiverId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sender_name", senderName);  // 插入发送者姓名
        values.put("sender_id", senderId);      // 插入发送者 ID
        values.put("receiver_id", receiverId);  // 插入接收者 ID
        db.insert("conversations", null, values); // 插入数据
        db.close(); // 关闭数据库
    }
    public List<GuanxiList> fetchConversations() {
        List<GuanxiList> conversations = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("conversations", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String senderName = cursor.getString(cursor.getColumnIndex("sender_name"));
                @SuppressLint("Range") String senderId = cursor.getString(cursor.getColumnIndex("sender_id"));
                @SuppressLint("Range") String receiverId = cursor.getString(cursor.getColumnIndex("receiver_id"));

                // 创建 GuanxiList 对象并添加到列表中
                conversations.add(new GuanxiList(senderName, senderId, receiverId));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return conversations; // 返回会话列表
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 升级数据库时删除旧表
        db.execSQL("DROP TABLE IF EXISTS conversations");
        onCreate(db);
    }
}