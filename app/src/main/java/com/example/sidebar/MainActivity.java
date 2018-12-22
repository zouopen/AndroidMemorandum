package com.example.sidebar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.core.Controller;
import com.app.hubert.guide.listener.OnGuideChangedListener;
import com.app.hubert.guide.listener.OnLayoutInflatedListener;
import com.app.hubert.guide.listener.OnPageChangedListener;
import com.app.hubert.guide.model.GuidePage;
import com.app.hubert.guide.model.HighLight;
import com.app.hubert.guide.model.RelativeGuide;
import com.example.sidebar.Adapter.MyAdapter;
import com.example.sidebar.DBHelper.DataWay;
import com.example.sidebar.DBHelper.NoteDAOServiceImpl;
import com.example.sidebar.Dao.DataDao;
import com.example.sidebar.Utils.ColorUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.btn_search) Button btn_search;
    @Bind(R.id.tianjia)    ImageButton add;
    @Bind(R.id.text)       TextView textView;
    @Bind(R.id.et_search)  EditText et_search;
    @Bind(R.id.toolbar1)   Toolbar  toolbar;
    @Bind(R.id.add_btn)    LinearLayout add_btn;
    @Bind(R.id.drawer_layout) DrawerLayout  mDrawerLayout;
    @Bind(R.id.nav_view)      NavigationView navigationView;
    @Bind(R.id.list)          ListView listView;
    protected MyAdapter myAdapter;
    private List<DataDao> daoList     = new ArrayList<>();
    private NoteDAOServiceImpl<DataDao, Integer, String> noteDAOService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
//        nonstop();
        moreStop();
        init();
        initListener();
    }

    private void init() {
        //调用数据库实现类
        noteDAOService = new NoteDAOServiceImpl<>(this, DataDao.class);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //清除标题栏默认文字
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        myAdapter = new MyAdapter(this, daoList);
        try { daoList.addAll(noteDAOService.queryAll()); } catch (SQLException e) { e.printStackTrace(); }
        listView.setAdapter(myAdapter);
        //主要通过ActionBarDrawerToggle将toolbar与DrawerListener关联起来
        mDrawerLayout.addDrawerListener(toggle);
        //设置menu类目颜色
        ColorUtils.settingColor(R.color.navigation_menu_item_color, navigationView, this);
    }
    //设置监听器
    private void initListener(){
        //侧边栏
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Toast.makeText(getApplication(),"["+menuItem.getTitle()+"]"+"功能正在添加,敬请期待",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
    @OnItemClick(R.id.list) void onViewClicked(int position){
        DataDao dataDao = daoList.get(position);
        Intent intent=new Intent(getApplicationContext(),updateMainActivity.class);
        intent.putExtra("id",dataDao.getId());
        startActivity(intent);
    }
    @OnItemLongClick(R.id.list) boolean onViewLongClicked(int position){
        DataDao dataDao = daoList.get(position);
        noteDAOService = new NoteDAOServiceImpl<>(this,DataDao.class);
        final Integer Id= dataDao.getId();
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this).setTitle("提示")
                .setMessage("是否删除所选？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try { DataDao id = noteDAOService.queryById(Id);noteDAOService.delete(id); } catch (SQLException e) { e.printStackTrace(); }
                        Refresh();
                    }
                });
        builder.show();
        return true;
    }
    @OnClick(R.id.btn_search) void Search(){
//        Toast.makeText(this,"点了一下",Toast.LENGTH_SHORT).show();
        String et_text= et_search.getText().toString();
        noteDAOService = new NoteDAOServiceImpl<>(this,DataDao.class);
        daoList.clear();
        try { daoList.addAll(noteDAOService.Vague(et_text)); } catch (SQLException e) { e.printStackTrace(); }
        myAdapter=new MyAdapter(getApplicationContext(),daoList);
        myAdapter.CheckData(daoList);
        listView.setAdapter(myAdapter);
    }
    @OnClick({R.id.tianjia,R.id.add_btn}) void StartActivity(){
        startActivity(new Intent(MainActivity.this,InputBoxActivity.class));}
    //刷新
    void Refresh(){
        noteDAOService = new NoteDAOServiceImpl<>(this,DataDao.class);
        daoList.clear();
        try { daoList.addAll(noteDAOService.queryAll()); } catch (SQLException e) { e.printStackTrace(); }
        myAdapter=new MyAdapter(getApplicationContext(),daoList);
        myAdapter.CheckData(daoList);
        listView.setAdapter(myAdapter);
    }
    public void moreStop(){
        Animation enterAnimation = new AlphaAnimation(0f, 1f);
        enterAnimation.setDuration(600);
        enterAnimation.setFillAfter(true);

        Animation exitAnimation = new AlphaAnimation(1f, 0f);
        exitAnimation.setDuration(600);
        exitAnimation.setFillAfter(true);

        //新增多页模式，即一个引导层显示多页引导内容
        NewbieGuide.with(this)
                .setLabel("page")//设置引导层标示区分不同引导层，必传！否则报错
                .alwaysShow(false)//是否每次都显示引导层，默认false，只显示一次
                .addGuidePage(//添加一页引导页
                        GuidePage.newInstance()//创建一个实例
                                .setLayoutRes(R.layout.view_guide_simple)//设置引导页布局
                                .setEnterAnimation(enterAnimation)//进入动画
                                .setExitAnimation(exitAnimation)//退出动画
                )
                .addGuidePage(GuidePage.newInstance()
                        .addHighLight(add_btn, HighLight.Shape.RECTANGLE, 20)
                        .addHighLight(add)
                        .setLayoutRes(R.layout.view_guide)
                        .setEverywhereCancelable(true)//是否点击任意地方跳转下一页或者消失引导层，默认true
                        .setEnterAnimation(enterAnimation)//进入动画
                        .setExitAnimation(exitAnimation)//退出动画
                )
                .show();//显示引导层(至少需要一页引导页才能显示)
    }
}


