package com.example.myapplication.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.duixiang.Message;

import java.util.ArrayList;
import java.util.List;

public class chat_sqlite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "chat.db";
    private static final int DATABASE_VERSION = 1;

    public chat_sqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createMessagesTable = "CREATE TABLE messages (" +
                "message_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "sender_id TEXT NOT NULL, " +
                "receiver_id TEXT NOT NULL, " +
                "content TEXT NOT NULL, " +
                "timestamp DATETIME DEFAULT  CURRENT_TIMESTAMP)";
        db.execSQL(createMessagesTable);
    }
    public List<Message> fetchMessages(String senderId, String receiverId) {//查询列表并放到一张表里面去
        List<Message> messages = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // 查询语句
        Cursor cursor = db.query("messages",
                null, // 选择所有列
                "(sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?)",
                new String[]{senderId, receiverId, receiverId, senderId},
                null, null, "timestamp"); // 按时间戳排序

        // 遍历结果
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String sender = cursor.getString(cursor.getColumnIndex("sender_id"));
                @SuppressLint("Range") String receiver = cursor.getString(cursor.getColumnIndex("receiver_id"));
                @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                // 创建 Message 对象并添加到列表中
                messages.add(new Message(receiver, sender, content));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return messages; // 返回消息列表
    }
    public void insertMessage(Message message) {//插入一条信息
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sender_id", message.getSenderId());
        values.put("receiver_id", message.getGeterId());
        values.put("content", message.getContent());
        db.insert("messages", null, values);
        Log.e("chat_sqlite","插入成功");
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS messages");
        onCreate(db);
    }
}
