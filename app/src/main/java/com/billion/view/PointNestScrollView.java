/**
 * ****************************************************************************
 * Copyright (C) 2005-2016 UCWEB Corporation. All rights reserved
 * File : 2018-02-09
 *
 * Description :PointNestScrollView.java
 *
 * Creation : 2018-02-09
 * Author   : zhengkui.wzk@alibaba-inc.com
 * History  : Creation, 2018-02-09 wangzk, Create the file
 * ****************************************************************************
 */
package com.billion.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class PointNestScrollView extends NestedScrollView {
    public PointNestScrollView(Context context) {
        super(context);
    }

    public PointNestScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PointNestScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}
