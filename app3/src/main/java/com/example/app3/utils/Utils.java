package com.example.app3.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utils {

    private Context mContext = null;

    public Utils(Context context){
        mContext = context;
    }

    /**
     * 通过文件方式保存用户名和密码
     * @param userName 用户名
     * @param userPwd 密码
     * @return true:保存成功
     *          false:保存失败
     */
    public boolean saveFile(String userName,String userPwd){
        FileOutputStream fileOutputStream = null;
        //获取文件存储对象
        try {
            fileOutputStream = mContext.openFileOutput("user.txt",Context.MODE_PRIVATE);
            fileOutputStream.write((userName+":::"+userPwd).getBytes());
            fileOutputStream.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 获取文件保存的内容
     * @return 获取到的内容
     */
    public String getFile(){
        try {
            FileInputStream fileInputStream = mContext.openFileInput("user.txt");
            byte[] bytes = new byte[1024];
            fileInputStream.read(bytes);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 通过sharedpregerences 保存文件
     * @param name 用户名
     * @param pwd 用户密码
     */
    public void saveUser(String name,String pwd){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",name);
        editor.putString("pwd",pwd);
        editor.commit();
    }

    /**
     * 获取用户名和密码
     * @return 用户名和密码组成的字符串
     */
    public String getUser(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences("user",Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("name",null);
        String pwd = sharedPreferences.getString("pwd",null);
        return name+":::"+pwd;
    }
}
