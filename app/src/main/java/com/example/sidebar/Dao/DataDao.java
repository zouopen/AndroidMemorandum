package com.example.sidebar.Dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 黄铿 on 2018/12/5.
 */
@DatabaseTable(tableName = "note_tb")
public class DataDao {
    @DatabaseField
    private String Text;
    @DatabaseField
    private String Time;
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private Integer With;
    public static final String tbName = "memorandumtb";
    public static final String ID = "id";
    public static final String TEXT = "Text";
    public static final String TIME = "Time";
    public static final String WITH = "With";
    public Integer getWith() {
        return With;
    }

    public void setWith(Integer with) {
        With = with;
    }

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
