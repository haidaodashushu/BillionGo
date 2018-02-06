/**
 * ****************************************************************************
 * Copyright (C) 2005-2016 UCWEB Corporation. All rights reserved
 * File : 2018-02-06
 *
 * Description :MainApplicationManger.java
 *
 * Creation : 2018-02-06
 * Author   : zhengkui.wzk@alibaba-inc.com
 * History  : Creation, 2018-02-06 wangzk, Create the file
 * ****************************************************************************
 */
package com.billion.application;

import android.app.Application;

public class MainApplicationManger {
    private static Application sMainApplication;

    static void init(Application application) {
        if (sMainApplication == null) {
            sMainApplication = application;
        }
    }

    public static Application getMainApplication() {
        return sMainApplication;
    }
}
