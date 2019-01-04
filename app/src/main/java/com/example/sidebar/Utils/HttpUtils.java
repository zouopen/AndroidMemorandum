package com.example.sidebar.Utils;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 网络请求工具类
 */
public class HttpUtils {
    private static final String Register_Url = "http://www.wanandroid.com/user/register";
    private static final String Login_Url    = "http://www.wanandroid.com/user/login";

    public static String Register(Context context, String username, String password, String repassword) throws IOException {
        OkHttpClient client = new OkHttpClient();
        //提交表单
        RequestBody body = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .add("repassword",repassword)
                .build();
        //请求注册
        Request request = new Request.Builder()
                .url(Register_Url)
                .post(body)
                .build();
        //获取返回数据
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }else {
            Toast.makeText(context,"网络异常哦",Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    public static void Register_enqueue( String username, String password, String repassword, Callback callback){
        OkHttpClient client = new OkHttpClient();
        //提交表单
        RequestBody body = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .add("repassword",repassword)
                .build();
        //请求注册
        Request request = new Request.Builder()
                .url(Register_Url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
    public static void Login_enqueue(String username,String password,Callback callback){
        OkHttpClient httpClient = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();
        Request request = new Request.Builder()
                .url(Login_Url)
                .post(body)
                .build();
        httpClient.newCall(request).enqueue(callback);
    }
    public static String Login_execute(Context context,String username,String password) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();
        Request request = new Request.Builder()
                .url(Login_Url)
                .post(body)
                .build();
        Response response = httpClient.newCall(request).execute();
        if (response.isSuccessful()){
            return response.body().string();
        }else {
            Toast.makeText(context,"网络异常哦",Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
