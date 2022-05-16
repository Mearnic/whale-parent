package com.mearnic.whale.security.core.bean;


/**
 * 通用返回🐎
 */
public enum CommonCode implements ResultCode {

    SUCCESS(0, "OK"),
    LOGOUT_SUCCESS(200, "退出成功"),
    REQUEST_BAD(400, "普通错误"),
    UNAUTHORIZED(401, "没有授权"),
    FORBIDDEN(403, "没有权限访问"),
    USER_NOT_EXIST(405, "账户不存在或者账户被停用"),
    LOGIN_ERROR(406, "账户登陆失败"),
    PARAM_LOST(407, "业务参数丢失"),
    DATA_NO_EXIST(408, "数据不存在"),
    DATA_EXIST(409, "数据已存在"),
    DATA_UNIQUE(409, "数据唯一性冲突"),
    DATA_ADD_FAIL(410, "数据添加失败"),
    DATA_EDIT_FAIL(411, "数据编辑失败"),
    DATA_DEL_FAIL(412, "数据删除失败"),
    DATA_ASSOCIATED(413,"存在关联数据"),
    DATA_UNAUTH(414,"数据无权限"),
    ILLEGAL_PARAM(415, "非法参数"),
    USER_OR_PASSWORD(416, "用户名或密码错误"),
    RANGE(0, "不在范围内"),
    SYS_ERROR(500, "系统异常"),
    HTTP_REQUEST_ERROR(501, "HTTP请求错误，请检查Method和Content-Type"),
    TOKEN_ERROR(600, "TOKEN错误"),
    TOKEN_EMPTY(601, "TOKEN为空"),
    TOKEN_OVERDUE(602, "TOKEN过期");


    //操作代码
    private int code;
    //提示信息
    private String message;

    CommonCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
