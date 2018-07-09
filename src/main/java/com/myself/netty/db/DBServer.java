package com.myself.netty.db;


import com.myself.dao.HouseChannelDao;


import java.sql.SQLException;

/**
 * 数据相关业务功能
 * @Author:AwakeningCode
 * @Date: Created in 9:07 2018\3\12 0012
 */
public class DBServer {


    public int updateLocksBYChannelId(String ChannelId,String locks)
            throws ClassNotFoundException,SQLException{
        HouseChannelDao houseChannelDao = new HouseChannelDao();
        return houseChannelDao.updateLocksByChannelId(ChannelId,locks);
    }

}
