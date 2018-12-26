package com.example.sidebar.Beans;

import java.util.List;

public class ErrorBeans {

    private LoginDataBeans data;
    private int errorCode;
    private String errorMsg;

    public LoginDataBeans getData() {
        return data;
    }

    public void setData(LoginDataBeans data) {
        this.data = data;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }


}
