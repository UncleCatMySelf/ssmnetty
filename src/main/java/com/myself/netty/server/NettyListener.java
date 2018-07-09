package com.myself.netty.server;

import java.util.concurrent.TimeUnit;

/**
 * @Author:AwakeningCode
 * @Date: Created in 17:03 2018\2\5 0005
 */
public class NettyListener {


    private Thread listenThread;

    public void start(){
        listenThread = new Thread(){
            @Override
            public void run() {
                try {

                    while (true){

                        TimeUnit.SECONDS.sleep(5);

                    }
                }catch (Exception ex){
                    System.out.println(String.format(
                            "Create Netty listener error %s",
                            ex.getMessage()));
                }
            }
        };
        listenThread.setDaemon(true);
        listenThread.start();
    }

}
