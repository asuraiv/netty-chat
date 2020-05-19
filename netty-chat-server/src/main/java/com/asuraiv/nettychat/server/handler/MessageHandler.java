package com.asuraiv.nettychat.server.handler;

import com.asuraiv.nettychat.common.dto.NettyChatProto;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MessageHandler extends SimpleChannelInboundHandler<ByteBuf> {

	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		channelGroup.add(ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

		NettyChatProto.ChatMessage message = NettyChatProto.ChatMessage.newBuilder()
			.setUserId("tester")
			.setMessage(new String(ByteBufUtil.getBytes(msg)))
			.build();

		ByteBuf buffer = Unpooled.buffer();
		buffer.writeBytes(message.toByteArray());

		channelGroup.writeAndFlush(buffer);
	}
}
