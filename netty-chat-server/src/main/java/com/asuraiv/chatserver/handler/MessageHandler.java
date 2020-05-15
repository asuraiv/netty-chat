package com.asuraiv.chatserver.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageHandler extends SimpleChannelInboundHandler<ByteBuf> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {

		// TODO: 들어온 메시지 처리 구현 필요
		System.out.println("Received message: " + new String(ByteBufUtil.getBytes(msg)));
	}
}
