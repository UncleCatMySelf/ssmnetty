package com.myself.common;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 公共类
 * @Author:这个程序员有纹身
 * @Date: Created in 13:21 2017\12\15 0015
 */
public class Const {


    private static Map<String, String> map = new ConcurrentHashMap<String, String>();

    /**
     * 全局还书数据池
     *
     * @param channel_port 连接值
     * @param userId       用户id
     */
    public static void add(String channel_port, String userId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("add Channel-port:" + channel_port + " " + format.format(new Date()));
        map.put(channel_port, userId);
    }

    /**
     * 判断是否存在该连接值实例
     *
     * @param channel_port 连接值
     * @return
     */
    public static boolean hasChannelPort(String channel_port) {
        boolean state = map.containsKey(channel_port);
        return state;
    }

    /**
     * 根据连接值 获取用户id
     *
     * @param channel_port
     * @return
     */
    public static String get(String channel_port) {
        return map.get(channel_port);
    }

    /**
     * 根据连接值 删除用户id
     *
     * @param channel_port
     */
    public static void remove(String channel_port) {
        map.remove(channel_port);
    }

    /**
     * 根据用户id，删除连接信息
     *
     * @param userId
     */
    public static void removeByValue(String userId) {
        for (Map.Entry entry : map.entrySet()) {
            if (entry.getValue() == userId) {
                map.remove(entry.getKey());
            }
        }
    }

    /**
     * 获取连接池个数
     *
     * @return
     */
    public static int getSize() {
        return map.size();
    }
}

