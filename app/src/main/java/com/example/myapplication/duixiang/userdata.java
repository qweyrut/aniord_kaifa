package com.example.myapplication.duixiang;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.myapplication.sqlite.Login_inf;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;


public class userdata {
    private Map<String,String> root;//用户数据根目录
    private static String flag="user_data";//标识
    private Context mcontext;
    private Login_inf logininf;
    public userdata(Context context)
    {
        logininf = new Login_inf(context);
        root=new HashMap<>();
        mcontext=context;
        readData(flag);
    }
    public boolean search(String name)//查找是否有该用户
    {
        SQLiteDatabase db = logininf.getReadableDatabase();
        Cursor cursor = db.query("users", null, null, null, null, null, "id");
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
                if(username.equals(name))
                    return true;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return false;
    }
    public boolean findExistUsername(String name)//查找是否有该用户名
    {
        return root.containsKey(name);
    }
    public boolean verifyPassword(String name,String password)//验证用户名和密码是否匹配
    {
        return findExistUsername(name) && root.get(name).equals(password);
    }
    public boolean getRegister(String name,String password)//注册，并返回是否注册成功
    {
        if(findExistUsername(name)||name.isEmpty()||password.isEmpty())
        {
            return false;
        }
        root.put(name,password);

        if (!update(name,password))
            Log.i("login","insert false");
        saveData(flag);
        return true;
    }

    public boolean update(String username, String password){
        SQLiteDatabase db = logininf.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(!username.isEmpty()||!password.isEmpty()){
            Random random = new Random();
            long randomNumber = 1000000000000000000L + (long)(random.nextDouble() * 9000000000000000000L);
            values.put("username", username);
            values.put("password", password);
            values.put("UID",randomNumber);
        }
        else
            return false;
        if(db.insert("users", null, values)==-1){
            return false;
        }
        db.close();
        return true;
    }
    private void saveData(String key)//保存数据到本地
    {
        //原理：将Map格式转换成json字符串，并指定一个特定标识来记录，Value按照发生器逐一保存就好
        JSONArray mJsonArray = new JSONArray();
        Iterator<Map.Entry<String, String>> iterator = root.entrySet().iterator();
        JSONObject object = new JSONObject();

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            try {
                String password = entry.getValue();
                object.put(entry.getKey(), password);

            } catch (JSONException e) {
                //异常处理
            }
        }
        mJsonArray.put(object);
        SharedPreferences sp=mcontext.getSharedPreferences("config",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(key,mJsonArray.toString());
        editor.commit();
    }
    private void readData(String key)//sharedpreferences从本地读取数据
    {
        root.clear();
        SharedPreferences sp=mcontext.getSharedPreferences("config",Context.MODE_PRIVATE);
        String result=sp.getString(key,"");
        try {
            JSONArray array=new JSONArray(result);
            for(int i=0;i<array.length();i++)
            {
                JSONObject itemObject =array.getJSONObject(i);
                JSONArray names=itemObject.names();
                if(names!=null)
                {
                    for(int j=0;j<names.length();j++)
                    {
                        String name=names.getString(j);
                        String value=itemObject.getString(name);
                        root.put(name,value);
                    }
                }
            }
        }catch (JSONException e){
            //异常处理
        }
    }

}