package com.demo.test.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class LeakCanaryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);


    }


    public void test1(View view) {


    }
}
