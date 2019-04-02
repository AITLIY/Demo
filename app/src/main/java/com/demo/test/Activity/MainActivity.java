package com.demo.test.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TabHost;

import com.demo.test.Fragment.AFragment;
import com.demo.test.Fragment.BFragment;
import com.demo.test.Fragment.CFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setToptabHost();
        setbuttonHost();

    }

    //设置底部标签
    private void setbuttonHost() {
        FragmentTabHost tabHost = (FragmentTabHost)findViewById(R.id.tabhost2);
        tabHost.setup(this, getSupportFragmentManager(), R.id.content2);//设置容器

        TabHost.TabSpec one = tabHost.newTabSpec("A");//设置选显卡 标识符
        one.setIndicator("A");//设置指示器 显示内容
        tabHost.addTab(one, AFragment.class, null);//添加到tabhost 附带fragment

        TabHost.TabSpec two = tabHost.newTabSpec("B");
        two.setIndicator("B");
        tabHost.addTab(two, BFragment.class, null);

        TabHost.TabSpec three = tabHost.newTabSpec("C");
        three.setIndicator("C");
        tabHost.addTab(three, CFragment.class, null);

        //设置监听
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                Log.i("fragment","TabSpec" + s);
            }
        });

        //设置默认选中 两种都可以
        //        tabHost.setCurrentTab(0);
        tabHost.setCurrentTabByTag("A");
    }

    //设置顶部标签
    public void setToptabHost() {
        FragmentTabHost tabHost = (FragmentTabHost)findViewById(R.id.tabhost1);
        tabHost.setup(this, getSupportFragmentManager(), R.id.tabhost1);//设置容器

        TabHost.TabSpec one = tabHost.newTabSpec("left");//设置选显卡 标识符
        one.setIndicator("A");//设置指示器 显示内容
        tabHost.addTab(one, AFragment.class, null);//添加到tabhost 附带fragment

        TabHost.TabSpec two = tabHost.newTabSpec("centre");
        two.setIndicator("B");
        tabHost.addTab(two, AFragment.class, null);

        TabHost.TabSpec three = tabHost.newTabSpec("right");
        three.setIndicator("C");
        tabHost.addTab(three, AFragment.class, null);

        //设置监听
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                Log.i("fragment","FabSpec" + s);
            }
        });

        //设置默认选中 两种都可以
        //        tabHost.setCurrentTab(1);
        tabHost.setCurrentTabByTag("right");
    }
}
