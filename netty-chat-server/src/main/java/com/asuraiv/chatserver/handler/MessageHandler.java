package com.asuraiv.chatserver.handler;

import com.asuraiv.chatserver.dto.ChatMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class MessageHandler extends SimpleChannelInboundHandler<ByteBuf> {

	private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		channelGroup.add(ctx.channel());
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

		ChatMessage message = new ChatMessage("tester", new String(ByteBufUtil.getBytes(msg)));

		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream oout = new ObjectOutputStream(bout);

		oout.writeObject(message);

		oout.close();
		bout.close();

		ByteBuf buffer = Unpooled.buffer();
		buffer.writeBytes(bout.toByteArray());

		channelGroup.writeAndFlush(buffer);
	}
}
