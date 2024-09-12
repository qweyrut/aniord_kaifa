package com.example.myapplication.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

}
