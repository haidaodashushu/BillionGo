/**
 * ****************************************************************************
 * Copyright (C) 2005-2016 UCWEB Corporation. All rights reserved
 * File : 2018-02-09
 *
 * Description :SharePreferenceUtil.java
 *
 * Creation : 2018-02-09
 * Author   : zhengkui.wzk@alibaba-inc.com
 * History  : Creation, 2018-02-09 wangzk, Create the file
 * ****************************************************************************
 */
package com.billion.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharePreferenceUtil {
    private static SharedPreferences sSharedPreferences;
    public static void init(Context context) {
        sSharedPreferences = context.getSharedPreferences("collectPoint", Context.MODE_PRIVATE);
    }
    private static SharedPreferences getInstance() {
        return sSharedPreferences;
    }
    public static void setBoolean(String key, boolean value) {
        Editor edit = getInstance().edit();
        edit.putBoolean(key,value);
        edit.apply();
    }

    public static boolean getBoolean(String key,boolean def) {
        return getInstance().getBoolean(key, def);
    }

    public static void setInt(String key, int value) {
        Editor edit = getInstance().edit();
        edit.putInt(key,value);
        edit.apply();
    }

    public static int getInt(String key,int def) {
        return getInstance().getInt(key, def);
    }
}
