package com.github.leleact.jtest.netty.common.encoder;

import com.github.leleact.jtest.netty.common.model.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * response encoder
 *
 * @author leleact
 * @since 2024-03-16
 */
public class ResponseDataEncoder extends MessageToByteEncoder<ResponseData> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ResponseData msg, ByteBuf out) {
        out.writeInt(msg.getIntValue());
    }
}
