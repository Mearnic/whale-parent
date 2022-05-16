package com.mearnic.whale.security.core.bean;

/**
 * 抽象统一返回 跟commoncode 配合
 */
public interface ResultCode {
    //操作代码
    int code();
    //提示信息
    String message();

}
