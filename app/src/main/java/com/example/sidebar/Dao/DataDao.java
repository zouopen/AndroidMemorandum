package com.example.sidebar.Dao;

/**
 * Created by 黄铿 on 2018/12/5.
 */

public class DataDao {
    private String Text;
    private String Time;
    private Integer id;

    public static final String tbName ="memorandumtb";
    public static final String ID="id";
    public static final String TEXT="Text";
    public static final String TIME = "Time";
    public void setTime(String time) {
        Time = time;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getTime() {
        return Time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DataBaseDemo{" +
                "text='" + TEXT + '\'' +
                ", time='" + TIME + '\'' +
                '}';
    }
}
