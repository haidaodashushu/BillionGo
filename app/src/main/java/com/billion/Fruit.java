/**
 * ****************************************************************************
 * Copyright (C) 2005-2016 UCWEB Corporation. All rights reserved
 * File : 2018-02-24
 *
 * Description :Fruit.java
 *
 * Creation : 2018-02-24
 * Author   : zhengkui.wzk@alibaba-inc.com
 * History  : Creation, 2018-02-24 wangzk, Create the file
 * ****************************************************************************
 */
package com.billion;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import android.support.annotation.StringDef;

@StringDef({"苹果", "菠萝", "香蕉"})
@Retention(RetentionPolicy.SOURCE)
public @interface Fruit {
    String value() default "";
    int id() default -1;
}

