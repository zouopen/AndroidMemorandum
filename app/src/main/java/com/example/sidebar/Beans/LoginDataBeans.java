package com.example.sidebar.Beans;

import java.util.List;

public class LoginDataBeans {
    private List<String> chapterTops;
    private List<String> collectIds;
    private String email;
    private String icon;
    private int id;
    private String password;
    private String token;
    private int type;
    private String username;

    public void setChapterTops(List<String> chapterTops) {
        this.chapterTops = chapterTops;
    }

    public List<String> getChapterTops() {
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


}
