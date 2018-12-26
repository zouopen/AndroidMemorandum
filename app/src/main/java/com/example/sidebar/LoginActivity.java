package com.example.sidebar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sidebar.Beans.ErrorBeans;
import com.example.sidebar.Beans.LoginDataBeans;
import com.example.sidebar.Utils.DataUtils;
import com.example.sidebar.Utils.GsonUtils;
import com.example.sidebar.Utils.HttpUtils;

import org.json.JSONException;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.login_account) EditText loginAccount;
    @Bind(R.id.login_password) EditText loginPassword;
    @Bind(R.id.comeback_register) TextView comebackRegister;
    @Bind(R.id.login) Button login;
    private String account;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        ButterKnife.bind(this);
        GetRegister();
        GetStoragePass();
    }
    private void Login(){
        account = loginAccount.getText().toString();
        password = loginPassword.getText().toString();
        HttpUtils.Login_enqueue(account, password, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    ErrorBeans errorBeans =  GsonUtils.ErrorBeans(response.body().string());
                    CheckLogin(errorBeans);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void CheckLogin(final ErrorBeans errorBeans){
        if (errorBeans.getErrorCode() == 0){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("登录成功")
                            .setContentText("您好"+errorBeans.getData().getUsername())
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                }
                            })
                            .show();
                }
            });
        }else{
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("错误")
                            .setContentText(errorBeans.getErrorMsg())
                            .show();
                }
            });
        }
    }
    //注册界面传来的账号与密码
    private void GetRegister(){
        if (getIntent() != null){
            account = getIntent().getStringExtra(RegisterActivity.REGISTER_NAME);
            password= getIntent().getStringExtra(RegisterActivity.REGISTER_PASS);
            loginAccount.setText(account);
            loginPassword.setText(password);
            //储存密码
            DataUtils.StoragePass(this,account,password);
        }
    }
    //提取密码
    private void GetStoragePass(){
        SharedPreferences sharedPreferences = getSharedPreferences(DataUtils.LOGINPASSWORAD,MODE_PRIVATE);
        String username = sharedPreferences.getString(DataUtils.USERNAME, "");
        String password = sharedPreferences.getString(DataUtils.PASSWROD, "");
        loginAccount.setText(username);
        loginPassword.setText(password);
    }
    @OnClick({R.id.comeback_register, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comeback_register:
                break;
            case R.id.login:
                Login();
                break;
        }
    }
}
