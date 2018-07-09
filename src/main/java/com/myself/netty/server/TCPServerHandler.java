package com.myself.netty.server;


import com.myself.netty.db.DBServer;
import com.myself.netty.utils.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.concurrent.ScheduledFuture;


import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 自定义过滤
 * @Author:AwakeningCode
 * @Date: Created in 8:20 2018\3\2 0002
 */
public class TCPServerHandler extends ChannelInboundHandlerAdapter {

    static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String ChannelID = null;
        try{
            ByteBuf in = (ByteBuf) msg;
            String data = in.toString(CharsetUtil.UTF_8);
            if (DataValida.ValidateHeadAndFeet_test(data)){
                data = DataResction.ResctionHeadAndFeet_test(data);
                if (DataValida.ValidateCRCCode(DataResction.ResctionData(data),DataResction.ResctionCRCCode(data))){
                    data = DataResction.ResctionData(data);
                    ChannelID = DataResction.ResctionID(data);
                    System.out.println("Const.hasChannelID(ChannelID):"+ Const.hasChannelID(ChannelID));
                    //更换连接ID
                    if (!Const.hasChannelID(ChannelID)){
                        String realChannelID = Const.isChannel(ctx.channel());
                        System.out.println(realChannelID);
                        Const.ChangeClientId(realChannelID,ChannelID);
                    }
                    //检查重复链接ID 不同实例 切换实例
                    if(Const.hasChannelID(ChannelID)){
                        Const.changeChannel(ChannelID,ctx.channel());
                    }
                    data =  DataResction.ResctionDataNoID(data);
                    String type = DataResction.ResctionType(data);
                    String RealData = DataResction.ResctionRealData(data);
                    //数据类型判断
                    switch (type){
                        case "s":
                            //控制类型
                            futureByController(ctx,RealData,ChannelID);
                            break;
                        case "g":
                            //经纬度传输
                            futureByLoLa(ctx,RealData,ChannelID);
                            break;
                        case "v":
                            //设备电量信息
                            RealData = DataResction.ResctionPower(RealData);
                            futureByCharge(ctx,RealData,ChannelID);
                            break;
                        case "p":
                            //设备检测物体信息
                            futureByPStates(ctx,RealData,ChannelID);
                            break;
                        case "r":
                            //设备开关异常
                            futureByException(ctx,RealData,ChannelID);
                            break;
                        case "j":
                            //客户端执行结果
                            futureByChlientResult(ctx,RealData,ChannelID);
                            break;
                        case "t":
                            futureBYTesting(ctx,RealData,ChannelID);
                            break;
                        default:
                            //其他类型
                            ctx.writeAndFlush(CallBackMessage.sendString(
                                    CRC16MySelf.getAllString(ChannelID,Const.RESULT_TYPE,Const.ERROR)));
                            break;
                    }
                } else {
                    ctx.writeAndFlush(CallBackMessage.ERROR.duplicate());
                    ctx.close();
                }
            } else {
                ctx.writeAndFlush(CallBackMessage.ERROR.duplicate());
                ctx.close();
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }


    /**
     * 客户端执行开锁测试执行方法
     * @param ctx
     * @param realData
     * @param ChannelID
     */
    private void futureBYTesting(ChannelHandlerContext ctx, String realData, String ChannelID) {
        Set<String> ids = Const.getIdList();
        System.out.println("测试广播事件执行");
        for (String item : ids){
            SendUtil sendUtil = new SendUtil();
            Channel channel = Const.get(item);
            if (channel != null){
                sendUtil.sendAll(realData,channel,item,Const.RESULT_TEXT);
            }
        }
    }

    /**
     * 客户端执行结果执行方法
     * @param ctx
     * @param realData
     */
    private void futureByChlientResult(ChannelHandlerContext ctx, String realData,String ChannelID) {
        //测试方法
        ScheduledFuture<?> future = ctx.channel().eventLoop().schedule(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("-------尝试执行SQL操作--------客户端执行结果");
                    }
                },0,TimeUnit.SECONDS);
        ctx.writeAndFlush(CallBackMessage.sendString(
                CRC16MySelf.getAllString(ChannelID,Const.RESULT_TYPE,Const.SUCCESS)));
    }

    /**
     * 开关异常执行方法
     * @param ctx
     * @param realData
     */
    private void futureByException(ChannelHandlerContext ctx,final String realData,final String ChannelID) {
        //测试方法
        ScheduledFuture<?> future = ctx.channel().eventLoop().schedule(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("-------尝试执行SQL操作--------开关异常");
                        /*DBServer dbServer = new DBServer();
                        try {
                            int effectNum = dbServer.updateLocksExceptionByChannelId(ChannelID,realData);
                            if (effectNum > 0) {
                                System.out.println("locks信息更新成功！");
                            } else {
                                System.out.println("locks信息更新失败！");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }*/
                    }
                },0,TimeUnit.SECONDS);
        ctx.writeAndFlush(CallBackMessage.sendString(
                CRC16MySelf.getAllString(ChannelID,Const.RESULT_TYPE,Const.SUCCESS)));
    }

    /**
     * 设备电量执行方法
     * @param ctx
     * @param realData
     */
    private void futureByCharge(ChannelHandlerContext ctx,final String realData,final String ChannelID) {
        //测试方法
        ScheduledFuture<?> future = ctx.channel().eventLoop().schedule(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("-------尝试执行SQL操作--------设备电量");
                        /*DBServer dbServer = new DBServer();
                        try {
                            int effectNum = dbServer.updatePowerByChannelId(ChannelID,realData);
                            if (effectNum > 0) {
                                System.out.println("power信息更新成功！");
                            } else {
                                System.out.println("power信息更新失败！");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }*/
                    }
                },0,TimeUnit.SECONDS);
        ctx.writeAndFlush(CallBackMessage.sendString(
                CRC16MySelf.getAllString(ChannelID,Const.RESULT_TYPE,Const.SUCCESS)));
    }

    /**
     * 经纬度传输执行方法
     * @param ctx
     * @param realData
     */
    private void futureByLoLa(ChannelHandlerContext ctx, String realData, final String ChannelID) {
        final String Longitude = DataResction.ResctionLongitude(realData);
        final String Latitude = DataResction.ResctionLatitude(realData);
        ScheduledFuture<?> future = ctx.channel().eventLoop().schedule(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("-------尝试执行SQL操作--------经纬度传输");
                        /*DBServer dbServer = new DBServer();
                        try {
                            int houseId = dbServer.selectByChannelId(ChannelID);
                            int effectNum = dbServer.updateLoLaByHouseId(Longitude,Latitude,houseId);
                            if (effectNum > 0) {
                                System.out.println("经纬度信息更新成功！");
                            } else {
                                System.out.println("经纬度信息更新失败！");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }*/
                    }
                },0,TimeUnit.SECONDS);
        ctx.writeAndFlush(CallBackMessage.sendString(
                CRC16MySelf.getAllString(ChannelID,Const.RESULT_TYPE,Const.SUCCESS)));
    }

    /**
     * 控制类执行方法
     * @param ctx
     * @param realData
     */
    private void futureByController(ChannelHandlerContext ctx,final String realData, final String ChannelID) {
        //SQL入库操作
        ScheduledFuture<?> future = ctx.channel().eventLoop().schedule(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("-------尝试执行SQL操作--------控制类型");
                        DBServer dbServer = new DBServer();
                        try {
                            int effectNum = dbServer.updateLocksBYChannelId(ChannelID,realData);
                            if (effectNum > 0) {
                                System.out.println("locks信息更新成功！");
                            } else {
                                System.out.println("locks信息更新失败！");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                },0,TimeUnit.SECONDS);
        ctx.writeAndFlush(CallBackMessage.sendString(
                CRC16MySelf.getAllString(ChannelID,Const.RESULT_TYPE,Const.SUCCESS)));

    }

    private void futureByPStates(ChannelHandlerContext ctx,final String realData,final String channelID) {
        System.out.println("检测物体事件执行");
        ScheduledFuture<?> future = ctx.channel().eventLoop().schedule(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("-------尝试执行SQL操作--------物体检测类型");
                        /*DBServer dbServer = new DBServer();
                        try {
                            String pstates = dbServer.select(channelID);
                            String key = getUpdateKey(channelID,pstates,realData);
                            if (com.myself.common.Const.hasChannelPort(key)){
                                String userId = com.myself.common.Const.get(key);
                                System.out.println("通过key获取用户信息-userId："+userId);
                                //向用户推送还书成功的消息模板
                                if (userId != null){
                                    //模板推送
                                }
                            }
                            int effectNum = dbServer.updatePstatesByChannelId(channelID,realData);
                            if (effectNum > 0){
                                System.out.println("pstates信息更新成功！");
                            }else{
                                System.out.println("pstates信息更新失败！");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }*/
                    }
                },0,TimeUnit.SECONDS);
    }

    private String getUpdateKey(String channelID, String pstates, String realData) {
        Integer openid = null;
        for (int i = 0; i < realData.length(); i++){
            if(pstates.charAt(i) != realData.charAt(i)){
                openid = i;
                break;
            }
        }
        return channelID + "_" + openid;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
        System.out.println(cause.getMessage());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Const.add(String.valueOf(UUID.randomUUID()),ctx.channel());
        channels.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Disconnected client " + ctx.channel().remoteAddress());
        Const.remove(ctx.channel());
    }
}
