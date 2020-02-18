package org.jim.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import javafx.scene.layout.Priority;

import java.net.InetSocketAddress;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class EchoServer {

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private ServerBootstrap bootstrap;

    public void init() {
        // boss group
        bossGroup = new NioEventLoopGroup(1);

        // worker group
        workerGroup = new NioEventLoopGroup();

        GlobalTrafficShapingHandler

        // Server引导
        bootstrap = new ServerBootstrap();
        bootstrap
                // selector
                .group(bossGroup, workerGroup)
                // channel
                .channel(NioServerSocketChannel.class)
                // channel handler
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new EchoServerHandler());
                    }
                })
                // option
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
    }

    public void listen() throws InterruptedException {
        try {
            ChannelFuture cf = bootstrap.bind(new InetSocketAddress("localhost", 8788)).sync();
            cf.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
