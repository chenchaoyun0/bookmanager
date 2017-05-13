package com.sttx.bookmanager.other.test.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

/** 
 * Netty 服务端代码 
 *  
 * @author lihzh 
 * @alia OneCoder 
 * @blog http://www.coderli.com 
 */
public class HelloServer {
    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

        // Set up the default event pipeline.  
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new StringDecoder(), new StringEncoder(), new ServerHandler());
            }
        });

        // Bind and start to accept incoming connections.  
        Channel bind = bootstrap.bind(new InetSocketAddress(8000));
        System.out.println("Server已经启动，监听端口: " + bind.getLocalAddress() + "， 等待客户端注册。。。");
    }

    private static class ServerHandler extends SimpleChannelHandler {
        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            if (e.getMessage() instanceof String) {
                String message = (String) e.getMessage();
                System.out.println("Client发来:" + message);

                e.getChannel().write("Server已收到刚发送的:" + message);

                System.out.println("\n等待客户端输入。。。");
            }

            super.messageReceived(ctx, e);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
            super.exceptionCaught(ctx, e);
        }

        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("有一个客户端注册上来了。。。");
            System.out.println("Client:" + e.getChannel().getRemoteAddress());
            System.out.println("Server:" + e.getChannel().getLocalAddress());
            System.out.println("\n等待客户端输入。。。");
            super.channelConnected(ctx, e);
        }
    }
}
