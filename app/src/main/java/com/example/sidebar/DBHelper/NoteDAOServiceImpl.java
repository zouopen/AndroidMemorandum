package com.example.sidebar.DBHelper;

import android.content.Context;

import com.example.sidebar.Dao.DataDao;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.DatabaseConnection;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**@see com.example.sidebar.DBHelper.NoteDAOService
 * 数据库增删查改，带事务操作
 * @author EvilSay
 */
public class NoteDAOServiceImpl<T,Z,S> implements NoteDAOService<T,Z,S> {
    private Context context;
    private Class<T> aClass;
    //    缓存DAO层
    private Map<Class<T>,Dao<T,Z>>daoMap = new HashMap<>();

    public NoteDAOServiceImpl(Context context, Class<T> aClass) {
        this.context = context;
        this.aClass  = aClass;
    }
    //    获取Helper dao 帮助类
    private Dao<T,Z> getDao()throws SQLException{
        Dao<T,Z> dao = daoMap.get(aClass);
        if (dao == null){
            dao = DataSQLHelper.getDatabase(context).getDao(aClass);
            daoMap.put(aClass,dao);
        }
        return dao;
    }
    @Override
    public int save(T t) throws SQLException {
        Dao<T,Z> dao = getDao();
        DatabaseConnection connection = null;
        try {
            //开启数据库事物
            connection = dao.startThreadConnection();
            //设置为手动提交
            dao.setAutoCommit(connection,false);
            //向表中添加一条数据
            int save = dao.create(t);
            //提交数据
            dao.commit(connection);
            //关闭提交
            dao.endThreadConnection(connection);
            return save;
        }catch (SQLException e){
            //添加错误，回滚数据库
            dao.rollBack(connection);
            e.printStackTrace();
        }finally {
            //结束事物
            dao.endThreadConnection(connection);
        }
        return 0;
    }

    @Override
    public int delete(T t) throws SQLException {
        Dao<T,Z> dao = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            int save = dao.delete(t);
            dao.commit(connection);
            dao.endThreadConnection(connection);
            return save;
        }catch (SQLException e){
            dao.rollBack(connection);
            e.printStackTrace();
        }finally {
            dao.endThreadConnection(connection);
        }
        return 0;
    }

    @Override
    public int update(T t) throws SQLException {
        Dao<T,Z> dao = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            int save = dao.update(t);
            dao.commit(connection);
            dao.endThreadConnection(connection);
            return save;
        }catch (SQLException e){
            dao.rollBack(connection);
            e.printStackTrace();
        }finally {
            dao.endThreadConnection(connection);
        }
        return 0;
    }

    @Override
    public T queryById(Z z) throws SQLException {
        Dao<T,Z> dao  = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            T t = dao.queryForId(z);
            dao.commit(connection);
            dao.endThreadConnection(connection);
            return t;
        }catch (SQLException e){
            dao.rollBack(connection);
            e.printStackTrace();
        }finally {
            dao.endThreadConnection(connection);
        }
        return null;
    }

    @Override
    public List<T> queryAll() throws SQLException {
        Dao<T,Z> dao = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            dao.commit(connection);
            dao.endThreadConnection(connection);
            return dao.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
            dao.rollBack(connection);
        }finally {
            dao.endThreadConnection(connection);
        }
        return null;
    }

    @Override
    public List<T> Vague(S s) throws SQLException {
        Dao<T,Z> dao = getDao();
        DatabaseConnection connection = null;
        try {
            connection = dao.startThreadConnection();
            dao.setAutoCommit(connection,false);
            List<T> query = dao.queryBuilder().where().like(DataDao.TEXT,"%" + s + "%").query();
            dao.commit(connection);
            dao.endThreadConnection(connection);
            return query;
        }catch (SQLException e){
            e.printStackTrace();
            dao.rollBack(connection);
        }finally {
            dao.endThreadConnection(connection);
        }
        return null;
    }
}
