package com.myself.netty.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @Author:AwakeningCode
 * @Date: Created in 11:20 2018\3\11 0011
 */
public class CallBackMessage {

    public static final ByteBuf SUCCESS = Unpooled.unreleasableBuffer(
            Unpooled.copiedBuffer("success", Charset.forName("UTF-8")));

    public static final ByteBuf ERROR = Unpooled.unreleasableBuffer(
            Unpooled.copiedBuffer("error", Charset.forName("UTF-8")));

    public static ByteBuf sendString(String send){
        return Unpooled.unreleasableBuffer(
                Unpooled.copiedBuffer(send, Charset.forName("UTF-8")));
    }
}
