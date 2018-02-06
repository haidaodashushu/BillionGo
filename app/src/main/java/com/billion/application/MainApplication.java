/**
 * ****************************************************************************
 * Copyright (C) 2005-2016 UCWEB Corporation. All rights reserved
 * File : 2018-02-06
 *
 * Description :MainApplication.java
 *
 * Creation : 2018-02-06
 * Author   : zhengkui.wzk@alibaba-inc.com
 * History  : Creation, 2018-02-06 wangzk, Create the file
 * ****************************************************************************
 */
package com.billion.application;

import android.app.Application;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MainApplicationManger.init(this);
    }

}
