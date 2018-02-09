/**
 * ****************************************************************************
 * Copyright (C) 2005-2016 UCWEB Corporation. All rights reserved
 * File : 2018-02-06
 *
 * Description :PointCollectManager.java
 *
 * Creation : 2018-02-06
 * Author   : zhengkui.wzk@alibaba-inc.com
 * History  : Creation, 2018-02-06 wangzk, Create the file
 * ****************************************************************************
 */
package com.billion;

import android.graphics.Point;
import android.view.MotionEvent;
import com.billion.sharedpreferences.PreferenceKey;
import com.billion.sharedpreferences.SharePreferenceUtil;

public class PointCollectManager {
    Point mStartPoint;

    protected boolean collectPoint(MotionEvent event) {
        if (event == null) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                mStartPoint = new Point();
                mStartPoint.x = (int)event.getRawX();
                mStartPoint.y = (int)event.getRawY();
                if (checkPointValid(mStartPoint)) {
                    saveCollectPoint(mStartPoint);
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    private boolean checkPointValid(Point point) {
        return point != null && point.x > 0 && point.y > 0;
    }

    private void saveCollectPoint(Point point) {
        SharePreferenceUtil.setInt(PreferenceKey.POINT_X, point.x);
        SharePreferenceUtil.setInt(PreferenceKey.POINT_Y, point.y);
    }

    protected Point getCollectPoint() {
        if (mStartPoint == null) {
            mStartPoint = new Point(-1, -1);
            int x = SharePreferenceUtil.getInt(PreferenceKey.POINT_X, -1);
            int y = SharePreferenceUtil.getInt(PreferenceKey.POINT_Y, -1);
            mStartPoint.x = x;
            mStartPoint.y = y;
        }
        if (mStartPoint.x == -1 || mStartPoint.y == -1) {
            return null;
        }
        return mStartPoint;
    }
}
