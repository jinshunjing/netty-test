package org.jim.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.LineEncoder;
import io.netty.util.ReferenceCountUtil;

/**
 * ChannelHandler
 * 主要处理ByteBuf
 *
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf)msg;
        try {
            // 读取数据
            System.out.print("Received: ");
            while (in.isReadable()) {
                System.out.print((char)in.readByte());
            }
            System.out.println();

            // 写回复
            ctx.write(msg);
            ctx.flush();
            System.out.println("Echo");
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
        LineBasedFrameDecoder
    }
}
