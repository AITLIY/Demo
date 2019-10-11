package com.demo.test.Activity;

import android.app.Activity;
import android.os.Bundle;
import com.demo.test.R;

public class CustomViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
    }
}
