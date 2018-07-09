package com.myself.controller;

import com.myself.common.ResponseCode;
import com.myself.common.ServerResponse;
import com.myself.netty.utils.Const;
import com.myself.netty.utils.SendUtil;
import io.netty.channel.Channel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:AwakeningCode
 * @Date: Created in 22:26 2018\2\6 0006
 */
@Controller
@RequestMapping("/netty")
public class NettyController {

    /**
     * 获取当前连接数
     * @return 连接个数
     */
    @RequestMapping(value = "/get_channel_size",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<Integer> getChannelSize(){
        return ServerResponse.createBySuccess(Const.getSzie());
    }

    /**
     * 获取连接Id数组
     * @return Id数组
     */
    @RequestMapping(value = "/get_channel_id_list",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getChannelIDList(){
        return ServerResponse.createBySuccess(Const.getIdList());
    }

    /**
     * 向存在链接发送指定的端口
     * @param channelId 连接ID
     * @param lock 打开第几把锁
     * @return
     */
    @RequestMapping(value = "/send_to_channel",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse SendToChannel(String channelId,Integer lock){
        SendUtil sendUtil = new SendUtil();
        Channel channel = Const.get(channelId);
        if (channel != null){
            if (sendUtil.send(lock,channel,channelId,Const.CONTROL_TYPE)){
                return ServerResponse.createBySuccess();
            }
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.NOT_CHANNEL.getCode(),ResponseCode.NOT_CHANNEL.getDesc());
    }
}
