package com.billion;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import android.support.annotation.IntDef;

/**
 * ****************************************************************************
 * Copyright (C) 2005-2016 UCWEB Corporation. All rights reserved
 * File : 2018-02-24
 *
 * Description :Test.java
 *
 * Creation : 2018-02-24
 * Author   : zhengkui.wzk@alibaba-inc.com
 * History  : Creation, 2018-02-24 wangzk, Create the file
 * ****************************************************************************
 */

public class Test {
    public final static int STATE_NONE = -1;
    public final static int STATE_ONE = 1;
    public final static int STATE_TWO = 2;
    public final static int STATE_THREE = 3;
    public final static int STATE_FOUR = 4;
    public final static int STATE_FIVE = 5;

    //定义状态变量
    private @State int state;

    /**
     * 设置状态
     * @param state 其取值只能是注解State上声明的值
     */
    public void setState(@State int state) {
        this.state = state;
    }

    @State
    public int getState() {
        return state;
    }

    /**
     * 定义注解State. 其取值范围是STATE_NONE, STATE_ONE, STATE_TWO, STATE_THREE, STATE_FOUR
     * RetentionPolicy.SOURCE表明该State只存在源码文件中。在编译成class文件时，State会被删除。
     */
    @IntDef({STATE_NONE, STATE_ONE, STATE_TWO, STATE_THREE, STATE_FOUR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {

    }



    private @Fruit String fruit;

    public void setFruit(@Fruit String fruitName) {
        this.fruit = fruitName;
    }

    @Fruit
    public String getFruit() {
        return fruit;
    }

}
