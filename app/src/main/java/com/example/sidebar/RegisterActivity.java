package com.example.sidebar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sidebar.Beans.ErrorBeans;
import com.example.sidebar.Biz.AsynchronousLogin;
import com.example.sidebar.Utils.GsonUtils;
import com.example.sidebar.Utils.HttpUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    @Bind(R.id.edit_account) EditText editAccount;
    @Bind(R.id.edit_password) EditText editPassword;
    @Bind(R.id.Cfmpassword) EditText Cfmpassword;
    @Bind(R.id.comeback_login) TextView comebackLogin;
    @Bind(R.id.register) Button register;
    public final static String REGISTER_NAME = "REGISTER_NAME";
    public final static String REGISTER_PASS = "REGISTER_USER";
    private AsynchronousLogin asynchronousLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ButterKnife.bind(this);
    }

    //注册登陆。
    private void Login() {
        String account = editAccount.getText().toString();
        String password = editPassword.getText().toString();
        String Cfmpasswordtext = Cfmpassword.getText().toString();
        asynchronousLogin = new AsynchronousLogin(account,password,Cfmpasswordtext);
        AsynchronousLogin();
    }
    private void AsynchronousLogin(){
        asynchronousLogin.AsyncTaskLoginData(this, new AsynchronousLogin.CallBackData() {
            @Override
            public void OnSuccess(ErrorBeans errorBeans) {
                registerCheck(errorBeans);
            }

            @Override
            public void OnFail(Exception ex) {
                new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("")
                        .setContentText("请检查网络")
                        .show();
            }
        });
    }
    private void registerCheck(ErrorBeans errorBeans){
        if (errorBeans.getErrorCode() == 0) {
            new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("成功")
                    .setContentText("注册成功")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            PutRegister();
                        }
                    }).show();
        }else{
            new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("错误")
                    .setContentText(errorBeans.getErrorMsg())
                    .show();
        }
    }
    private void PutRegister(){
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        intent.putExtra(REGISTER_NAME,editAccount.getText().toString());
        intent.putExtra(REGISTER_PASS,editPassword.getText().toString());
        startActivityForResult(intent,2);
        setResult(2,intent);
        startActivity(intent);
    }
    @OnClick({R.id.comeback_login, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.comeback_login:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                break;
            case R.id.register:
                Login();
                break;
        }
    }
}
