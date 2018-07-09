package com.myself.netty.thread;

import com.myself.netty.server.NettyServer;

/**
 * Netty 线程启动netty服务器监听
 * @Author:AwakeningCode
 * @Date: Created in 18:50 2018\1\30 0030
 */
public class NettyServerThread extends Thread{

    private int port;
    private boolean _run = true;

    public NettyServerThread(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        NettyServer server = new NettyServer();
        server.doStart();
    }

    public void stopThread(boolean run){
        this._run = !run;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean is_run() {
        return _run;
    }

    public void set_run(boolean _run) {
        this._run = _run;
    }
}
