package com.myself.dao;

import com.myself.pojo.HouseChannel;

public interface HouseChannelMapper {
    int deleteByPrimaryKey(Integer houseId);

    int insert(HouseChannel record);

    int insertSelective(HouseChannel record);

    HouseChannel selectByPrimaryKey(Integer houseId);

    int updateByPrimaryKeySelective(HouseChannel record);

    int updateByPrimaryKey(HouseChannel record);

    HouseChannel selectByChannelId(String channelId);
}