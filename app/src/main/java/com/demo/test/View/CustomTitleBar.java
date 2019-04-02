package com.demo.test.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.test.Activity.R;

/**
 * Created by Administrator on 2019/4/1.
 */

public class CustomTitleBar extends RelativeLayout {

    private  Button titleBarLeftBtn;
    private Button titleBarRightBtn;
    private TextView titleBarTitle;

    public CustomTitleBar(Context context) {
        super(context);

    }

    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.custom_title_bar, this, true);
        titleBarLeftBtn = (Button) findViewById(R.id.title_bar_left);
        titleBarRightBtn = (Button) findViewById(R.id.title_bar_right);
        titleBarTitle = (TextView) findViewById(R.id.title_bar_title);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
        if (attributes != null) {
            //处理titleBar背景色
//            int titleBarBackGround = attributes.getResourceId(R.styleable.CustomTitleBar_title_background_color, Color.GREEN); //Color.GREEN做默认值 未设置时会报错
            int titleBarBackGround = attributes.getResourceId(R.styleable.CustomTitleBar_title_background_color, R.drawable.titlebar_add_icon);
            setBackgroundResource(titleBarBackGround);

            // 默认为Color的方法
//            int titleBarBackGround = attributes.getColor(R.styleable.CustomTitleBar_title_background_color, Color.GREEN);
//            setBackgroundColor(titleBarBackGround);

            //先处理左边按钮
            //获取是否要显示左边按钮
            boolean leftButtonVisible = attributes.getBoolean(R.styleable.CustomTitleBar_left_button_visible, true);
            if (leftButtonVisible) {
                titleBarLeftBtn.setVisibility(View.VISIBLE);
            } else {
                titleBarLeftBtn.setVisibility(View.INVISIBLE);
            }
            //设置左边按钮的文字
            String leftButtonText = attributes.getString(R.styleable.CustomTitleBar_left_button_text);
            if (!TextUtils.isEmpty(leftButtonText)) {
                titleBarLeftBtn.setText(leftButtonText);
                //设置左边按钮文字颜色
                int leftButtonTextColor = attributes.getColor(R.styleable.CustomTitleBar_left_button_text_color, Color.WHITE);
                titleBarLeftBtn.setTextColor(leftButtonTextColor);
            }
            //设置左边图片icon
            int leftButtonDrawable = attributes.getResourceId(R.styleable.CustomTitleBar_left_button_drawable, -1);
            if (leftButtonDrawable != -1) {
                titleBarLeftBtn.setCompoundDrawablesWithIntrinsicBounds(leftButtonDrawable, 0, 0, 0);  //设置到哪个控件的位置（）
            }

            //处理标题
            //先获取标题是否要显示图片icon
            int titleTextDrawable = attributes.getResourceId(R.styleable.CustomTitleBar_title_text_drawable, -1);
            if (titleTextDrawable != -1) {
                titleBarTitle.setBackgroundResource(titleTextDrawable);
            } else { //这里是二选一 要么只能是文字 要么只能是图片
                //如果不是图片标题 则获取文字标题
                String titleText = attributes.getString(R.styleable.CustomTitleBar_title_text);
                if (!TextUtils.isEmpty(titleText)) {
                    titleBarTitle.setText(titleText);
                }
                //获取标题显示颜色
                int titleTextColor = attributes.getColor(R.styleable.CustomTitleBar_title_text_color, Color.WHITE);
                titleBarTitle.setTextColor(titleTextColor);
            }

            //先处理右边按钮
            //获取是否要显示右边按钮
            boolean rightButtonVisible = attributes.getBoolean(R.styleable.CustomTitleBar_right_button_visible, true);
            if (rightButtonVisible) {
                titleBarRightBtn.setVisibility(View.VISIBLE);
            } else {
                titleBarRightBtn.setVisibility(View.INVISIBLE);
            }
            //设置右边按钮的文字
            String rightButtonText = attributes.getString(R.styleable.CustomTitleBar_right_button_text);
            if (!TextUtils.isEmpty(rightButtonText)) {
                titleBarRightBtn.setText(rightButtonText);
                //设置右边按钮文字颜色
                int rightButtonTextColor = attributes.getColor(R.styleable.CustomTitleBar_right_button_text_color, Color.WHITE);
                titleBarRightBtn.setTextColor(rightButtonTextColor);
            }
            //设置右边图片icon
            int rightButtonDrawable = attributes.getResourceId(R.styleable.CustomTitleBar_right_button_drawable, -1);
            if (rightButtonDrawable != -1) {
                titleBarRightBtn.setCompoundDrawablesWithIntrinsicBounds(0, 0, rightButtonDrawable, 0);  //设置到哪个控件的位置（）
            }
            attributes.recycle();
        }
    }

    public CustomTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomTitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
