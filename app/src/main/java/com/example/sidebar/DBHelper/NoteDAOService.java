package com.example.sidebar.DBHelper;

import java.sql.SQLException;
import java.util.List;

/**@author EvilSay
 * 数据访问接口
 * @param <T> 操作的实体类型
 * @param <Z> 表对应的主键类型
 * @param <S> 自定义泛型
 */
public interface NoteDAOService<T,Z,S> {

    int save(T t) throws SQLException;

    int delete(T t) throws SQLException;

    int update(T t) throws SQLException;

    //  T 指定实体类型 Z为指定主键类型
    T queryById(Z z) throws SQLException;

    // T 实体类型
    List<T> queryAll() throws SQLException;

    //S 自定义泛型
    List<T> Vague(S s) throws SQLException;
}   
