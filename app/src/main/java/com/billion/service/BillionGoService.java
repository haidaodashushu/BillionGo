/**
 * ****************************************************************************
 * Copyright (C) 2005-2016 UCWEB Corporation. All rights reserved
 * File : 2018-02-09
 *
 * Description :BillionGoService.java
 *
 * Creation : 2018-02-09
 * Author   : zhengkui.wzk@alibaba-inc.com
 * History  : Creation, 2018-02-09 wangzk, Create the file
 * ****************************************************************************
 */
package com.billion.service;

import android.app.Instrumentation;
import android.app.Service;
import android.content.Intent;
import android.graphics.Point;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import com.billion.FloatWidgetManager;
import com.billion.sharedpreferences.PreferenceKey;
import com.billion.sharedpreferences.SharePreferenceUtil;

public class BillionGoService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("BillionGoService", "onCreate");
        FloatWidgetManager.show(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tryClickScreenByInst();
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("BillionGoService", "onBind");
        return null;
    }

    private Point getCollectPoint() {
        int x = SharePreferenceUtil.getInt(PreferenceKey.POINT_X, -1);
        int y = SharePreferenceUtil.getInt(PreferenceKey.POINT_Y, -1);
        if (x > 0 && y > 0) {
            Point point = new Point();
            point.x = x;
            point.y = y;
            return point;
        }
        return null;
    }

    private void tryClickScreenByInst(){
        final Point point = getCollectPoint();
        if (point == null) {
            return;
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int x = point.x;
                int y = point.y;
                Instrumentation inst = new Instrumentation();
                inst.sendPointerSync(MotionEvent
                    .obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x, y, 0));
                inst.sendPointerSync(MotionEvent
                    .obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, x, y, 0));
            }
        });
        thread.start();
    }

    @Override
    public void onDestroy() {
        Log.i("BillionGoService", "onDestroy");
        super.onDestroy();
    }
}
