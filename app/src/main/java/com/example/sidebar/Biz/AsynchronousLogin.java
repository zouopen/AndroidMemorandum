package com.example.sidebar.Biz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.example.sidebar.Beans.ErrorBeans;
import com.example.sidebar.Utils.GsonUtils;
import com.example.sidebar.Utils.HttpUtils;

import java.io.IOException;

public class AsynchronousLogin {
    private String username;
    private String password;
    private String repassword;
    public AsynchronousLogin(String username, String password, String repassword) {
        this.username = username;
        this.password = password;
        this.repassword = repassword;
    }

    public AsynchronousLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AsynchronousLogin() {
    }

    public  void AsyncTaskLoginData(final Context context, final CallBackData callBackData){
        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, ErrorBeans> asyncTask = new AsyncTask<Void, Void, ErrorBeans>() {
            Exception exception;
            @Override
            protected ErrorBeans doInBackground(Void... voids) {
                try {
                   return fromNetWork(context);
                } catch (IOException e) {
                    e.printStackTrace();
                    this.exception = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(ErrorBeans errorBeans) {
                if (exception != null || errorBeans == null){
                    callBackData.OnFail(exception);
                }else{
                    callBackData.OnSuccess(errorBeans);
                }
            }
        };
        asyncTask.execute();
    }
    private ErrorBeans fromNetWork(Context context) throws IOException {
        if (username != null && password != null && repassword != null){
            return GsonUtils.errorBeans(HttpUtils.Register(context, username, password, repassword));
        }else if (username != null && password != null){
            return GsonUtils.errorBeans(HttpUtils.Login_execute(context,username,password));
        }else{
            throw new IOException("Not Data Work");
        }
    }
     public interface CallBackData{
        void OnSuccess(ErrorBeans errorBeans);
        void OnFail(Exception ex);
    }
}
