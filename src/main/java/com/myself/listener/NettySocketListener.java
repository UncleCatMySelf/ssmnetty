package com.myself.listener;

import com.myself.netty.thread.NettyServerThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Netty——TCP/IP通信监听类（port：9999）
 * @Author:AwakeningCode
 * @Date: Created in 18:47 2018\1\30 0030
 */
public class NettySocketListener implements ServletContextListener{

    private NettyServerThread nettyServerThread;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String port = servletContextEvent.getServletContext().getInitParameter("socketPort");
        nettyServerThread = new NettyServerThread(Integer.parseInt(port));
        nettyServerThread.start();
        System.out.println("Project NettyServerThread Start in Port:" + port);
    }

    /**
     * tomcat关闭时，关闭线程，释放端口
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        nettyServerThread.stopThread(true);
        System.out.println("Project NettyServerThread End");
    }

}
