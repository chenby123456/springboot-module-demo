/*
 * Copyright (c) 2016 iKang Guobin Healthcare Group. All rights reserved.
 */
package com.cby.constant;


import static com.cby.constant.ShareConstant.NUM_0;
import static com.cby.constant.ShareConstant.NUM_1;

/**
 * @ClassName: ErrorCode
 * @Description: 错误码枚举类
 * 使用枚举类型来封装异常码和异常信息
 * @Author: donglin.ni
 * @Date: 2019/8/21 15:22
 * @Version: 1.0.0
 */
public enum ErrorCode {

    SUCCESS(NUM_1, "success"),
    FAIL(NUM_0, "failure"),

    TEST_EXCEPTION(412,"测试异常"),
    USER_UN_ENABLE(1001, "账号已失效！"),
    SYSTEM_INNER_EXCEPTION(500,"系统内部异常"),
    PARAMETER_NULL_EXCEPTION(500,"null参数异常"),
    EXCEL_EXCEPTION(500,"文件下载异常"),
    UN_AUTHC(401, "未认证（签名错误）"),
    ERROR_ACCOUNT(416,"账号密码错误"),
    ALREADY_EXISTS(409,"此记录已存在"),
    UN_EXISTS(410,"记录不存在"),
    UN_PERMISSION(406,"无此操作权限"),
    ERROR_FILE_FORMAT(407,"文件格式错误"),
    NOT_NUMBER_EXCEPTION(601,"您输入的不是数值类型"),
    NUMBER_CROSSED_EXCEPTION(602,"您输入的值不在有效范围内"),
    NUMBER_POINT_EXCEPTION(603,"您输入值的小数位数不正确,最大小数位数为:"),
    NUMBER_LENGTH_EXCEPTION(604,"您输入值的长度不正确,最大长度为:"),
    OPTION_INPUT_EXCEPTION(605,"您录入值的格式不正确,不能出现|字符"),
    ANSWER_SAFTER_EXCEPTION(606,"您在该类别还有问题未回答完毕:"),
    PHONENUMBER_EXCEPTION(607,"电话号码格式不正确,请重新输入"),
    NULL_PHONE_NUMBER_EXCEPTION(700,"您所选的用户存在没有手机号码的情况,请取消勾选没有手机号的用户后再发送"),
    USER_CHECK_EXCEPTION(800,"用户校验失败,请传递正确的数据"),
    QUESTEMPLATE_EXCEPTION(801,"没有这个问卷模版,请传递正确的数据"),
    NAME_REPEAT_EXCEPTION(608,"模板名称重复,请重新输入"),
    AUTH_FLAG_EXCEPTION(802,"暂无权限"),
    MEDICAL_REPORT_EXCEPTION(803,"获取体检编号异常"),
    ;

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }
}


