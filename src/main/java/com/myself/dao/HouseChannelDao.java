package com.myself.dao;

import com.myself.netty.db.DbAccess;
import com.myself.pojo.HouseChannel;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

/**
 * @Author:AwakeningCode
 * @Date: Created in 17:41 2018\3\22 0022
 */
public class HouseChannelDao {

    /**
     * 根据Channeld更新locks信息
     * @param ChannelId
     * @param locks
     * @return
     */
    public int updateLocksByChannelId(String ChannelId,String locks){
        DbAccess dbAccess = new DbAccess();
        int effectNum = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            HouseChannel houseChannel = new HouseChannel();
            houseChannel.setChannelId(ChannelId);
            houseChannel.setLocks(locks);
            effectNum = sqlSession.update("HouseChannel.updateLocksByChannelId",houseChannel);
            sqlSession.commit();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
        return effectNum;
    }

    /**
     * 根据ChannelId更新power
     * @param ChannelId
     * @param power
     * @return
     */
    public int updatePowerByChannelId(String ChannelId,String power){
        DbAccess dbAccess = new DbAccess();
        int effectNum = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            HouseChannel houseChannel = new HouseChannel();
            houseChannel.setChannelId(ChannelId);
            houseChannel.setPower(power);
            effectNum = sqlSession.update("HouseChannel.updatePowerByChannelId",houseChannel);
            sqlSession.commit();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
        return effectNum;
    }

    /**
     * 根据ChannelId更新Locks异常信息
     * @param ChannelId
     * @param locks
     * @return
     */
    public int updateLocksExceptionByChannelId(String ChannelId,String locks){
        DbAccess dbAccess = new DbAccess();
        int effectNum = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            HouseChannel houseChannel = new HouseChannel();
            houseChannel.setChannelId(ChannelId);
            houseChannel.setLocks(locks);
            effectNum = sqlSession.update("HouseChannel.updateLocksExceptionByChannelId",houseChannel);
            sqlSession.commit();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
        return effectNum;
    }

    /**
     * 根据ChannelId查询对应的唯一house_id
     * @param ChannelId
     * @return
     */
    public int selectByChannelId(String ChannelId){
        DbAccess dbAccess = new DbAccess();
        Integer houseId = null;
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            HouseChannel houseChannel = new HouseChannel();
            houseChannel.setChannelId(ChannelId);
            houseId = sqlSession.selectOne("HouseChannel.selectByChannelId",houseChannel);
            sqlSession.commit();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
        return houseId;
    }

    public int updatePstatesByChannelId(String channelID, String realData) {
        DbAccess dbAccess = new DbAccess();
        int effectNum = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            HouseChannel houseChannel = new HouseChannel();
            houseChannel.setChannelId(channelID);
            houseChannel.setPstates(realData);
            effectNum = sqlSession.update("HouseChannel.updatePstatesByChannelId",houseChannel);
            sqlSession.commit();
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
        return effectNum;
    }

    public String select(String channelID) {
        DbAccess dbAccess = new DbAccess();
        String pstates = null;
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            HouseChannel houseChannel = new HouseChannel();
            houseChannel.setChannelId(channelID);
            pstates = sqlSession.selectOne("HouseChannel.select",houseChannel);
            sqlSession.commit();
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            if (sqlSession!=null){
                sqlSession.close();
            }
        }
        return pstates;
    }
}
