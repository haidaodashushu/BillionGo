/**
 * ****************************************************************************
 * Copyright (C) 2005-2016 UCWEB Corporation. All rights reserved
 * File : 2018-02-06
 *
 * Description :FloatWidgetManager.java
 *
 * Creation : 2018-02-06
 * Author   : zhengkui.wzk@alibaba-inc.com
 * History  : Creation, 2018-02-06 wangzk, Create the file
 * ****************************************************************************
 */
package com.billion;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import com.billion.application.MainApplicationManger;

public class FloatWidgetManager {
    private final static String TAG = "FloatWidgetManager";

    @SuppressLint("ClickableViewAccessibility")
    public static void show() {
        Application mainApplication = MainApplicationManger.getMainApplication();
        final WindowManager windowManager = (WindowManager)mainApplication.getSystemService(Context.WINDOW_SERVICE);
        final LayoutParams wmParams = new WindowManager.LayoutParams();
        //获取的是WindowManagerImpl.CompatModeWrapper
        Log.i(TAG, "windowManager--->" + windowManager);
        wmParams.type = LayoutParams.TYPE_PHONE;
        //设置图片格式，效果为背景透明
        wmParams.format = PixelFormat.RGBA_8888;
        wmParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
        //调整悬浮窗显示的停靠位置为左侧置顶
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        // 以屏幕左上角为原点，设置x、y初始值，相对于gravity
        wmParams.x = 0;
        wmParams.y = 0;

        //设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

         /*// 设置悬浮窗口长宽数据
        wmParams.width = 200;
        wmParams.height = 80;*/

        LayoutInflater inflater = LayoutInflater.from(mainApplication);
        //获取浮动窗口视图所在布局
        final LinearLayout floatLayout = (LinearLayout)inflater.inflate(R.layout.float_layout, null);
        //添加mFloatLayout
        windowManager.addView(floatLayout, wmParams);
        //浮动窗口按钮
        final Button mFloatView = (Button)floatLayout.findViewById(R.id.float_id);

        floatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
            View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
            .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        Log.i(TAG, "Width/2--->" + mFloatView.getMeasuredWidth() / 2);
        Log.i(TAG, "Height/2--->" + mFloatView.getMeasuredHeight() / 2);
        //设置监听浮动窗口的触摸移动
        mFloatView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                //getRawX是触摸位置相对于屏幕的坐标，getX是相对于按钮的坐标
                wmParams.x = (int)event.getRawX() - mFloatView.getMeasuredWidth() / 2;
                Log.i(TAG, "RawX" + event.getRawX());
                Log.i(TAG, "X" + event.getX());
                //减25为状态栏的高度
                wmParams.y = (int)event.getRawY() - mFloatView.getMeasuredHeight() / 2 - 25;
                Log.i(TAG, "RawY" + event.getRawY());
                Log.i(TAG, "Y" + event.getY());
                //刷新
                windowManager.updateViewLayout(floatLayout, wmParams);
                return false;  //此处必须返回false，否则OnClickListener获取不到监听
            }
        });

        mFloatView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }
}
