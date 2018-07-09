package com.myself.pojo;

import java.util.Date;

public class HouseChannel {
    private Integer houseId;

    private String channelId;

    private String locks;

    private String pstates;

    private String power;

    private String state;

    private Date createTime;

    private Date updateTime;

    public HouseChannel(Integer houseId, String channelId, String locks, String pstates, String power, String state, Date createTime, Date updateTime) {
        this.houseId = houseId;
        this.channelId = channelId;
        this.locks = locks;
        this.pstates = pstates;
        this.power = power;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getPstates() {
        return pstates;
    }

    public void setPstates(String pstates) {
        this.pstates = pstates;
    }

    public HouseChannel() {
        super();
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getLocks() {
        return locks;
    }

    public void setLocks(String locks) {
        this.locks = locks == null ? null : locks.trim();
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power == null ? null : power.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}