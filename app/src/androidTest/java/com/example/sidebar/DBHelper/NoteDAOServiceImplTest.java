package com.example.sidebar.DBHelper;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.example.sidebar.Dao.DataDao;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class NoteDAOServiceImplTest {
    private Context context = InstrumentationRegistry.getTargetContext().getApplicationContext();
    private NoteDAOServiceImpl<DataDao,Integer,String> dataDaoIntegerNoteDAOService = new NoteDAOServiceImpl<>(context,DataDao.class);
    @Test
    public void save()throws SQLException {
        DataDao dataDao = new DataDao();
        dataDao.setText("123");
        dataDao.setTime("11122233");
        dataDao.setWith(1);
        dataDaoIntegerNoteDAOService.save(dataDao);
    }

    @Test
    public void delete()throws SQLException {
        DataDao dataDao = dataDaoIntegerNoteDAOService.queryById(2);
        dataDaoIntegerNoteDAOService.delete(dataDao);
    }

    @Test
    public void update()throws SQLException {
        DataDao dataDao = dataDaoIntegerNoteDAOService.queryById(2);
        dataDao.setText("11111");
        dataDaoIntegerNoteDAOService.update(dataDao);
        Assert.assertNotNull(dataDao);
    }

    @Test
    public void queryById() {
    }

    @Test
    public void queryAll() throws SQLException {
        List<DataDao> dataDaoList = dataDaoIntegerNoteDAOService.queryAll();
        for (DataDao dao: dataDaoList) {
            Log.e("1", "queryAll: "+dao.getText() );
        }
    }
    @Test
    public void Vague() throws SQLException{
        List<DataDao> vague = dataDaoIntegerNoteDAOService.Vague("1");
        for (DataDao dataDao : vague){
            Log.e("test", "Vague: "+ dataDao.getText() );
        }
    }
}