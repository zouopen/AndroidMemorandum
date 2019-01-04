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
    public class LoginDataBeans {
        private List<Sk> chapterTops;
        private List<String> collectIds;
        private String email;
        private String icon;
        private int id;
        private String password;
        private String token;
        private int type;
        private String username;

        public void setChapterTops(List<Sk> chapterTops) {
            this.chapterTops = chapterTops;
        }

        public List<Sk> getChapterTops() {
            return chapterTops;
        }

        public void setCollectIds(List<String> collectIds) {
            this.collectIds = collectIds;
        }

        public List<String> getCollectIds() {
            return collectIds;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIcon() {
            return icon;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
        public class Sk {
            private int Q1;
        }
    }

}
