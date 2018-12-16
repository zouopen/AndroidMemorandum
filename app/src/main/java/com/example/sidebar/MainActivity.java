package com.example.sidebar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sidebar.Framgnet.FragmentAddBtn;
import com.example.sidebar.Framgnet.FragmentMain;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected DrawerLayout  mDrawerLayout;
    protected NavigationView navigationView;
    private   FragmentMain fragmentMain = new FragmentMain();
    private FragmentAddBtn fragmentAddBtn=new FragmentAddBtn();
    protected ImageButton add;
    protected TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FragmentView();
        init();
        settingColor();
        initListener();

    }
//    加载fragment布局
    private void FragmentView(){
        this.getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentMain,fragmentMain)
                .add(R.id.fragmentBtn,fragmentAddBtn)
                .commit();


    }
    
    private void init() {
        add = findViewById(R.id.tianjia);
        textView = findViewById(R.id.text);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        //下面的代码主要通过actionbardrawertoggle将toolbar与drawablelayout关联起来
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(toggle);
        add.setOnClickListener(this);
    }
    //设置menu类目颜色
    private void settingColor(){
        navigationView = findViewById(R.id.nav_view);
        for (int i = 0; i <= 3 ; i++) {
            Resources resource = getBaseContext().getResources();
            ColorStateList csl = resource.getColorStateList(R.color.navigation_menu_item_color);
            navigationView.setItemTextColor(csl);
            navigationView.getMenu().getItem(i).setChecked(true);
        }
    }
    //设置监听器
    private void initListener(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Toast.makeText(getApplication(),"["+menuItem.getTitle()+"]"+"功能正在添加,敬请期待",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this,InputBoxActivity.class));
    }
}


