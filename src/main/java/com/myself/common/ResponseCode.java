package com.myself.common;

/**
 * API接口返回协议
 * @Author:这个程序员有纹身
 * @Date: Created in 21:13 2017\12\13 0013
 */
public enum ResponseCode {
    SUCCESS(200,"SUCCESS"),
    ERROR(100,"ERROR"),
    //netty
    NOT_CHANNEL(-2000,"NOT_CHANNEL");

    private final int code;
    private final String desc;


    ResponseCode(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
