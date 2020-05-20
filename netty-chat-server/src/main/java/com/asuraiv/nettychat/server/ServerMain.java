package com.asuraiv.nettychat.server;

import com.asuraiv.nettychat.server.handler.MessageHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class ServerMain {

	public static void main(String[] args) {

		if(args.length == 0) {
			System.err.println("Usage: java -jar server.jar <port>");
		}

		ServerBootstrap bootstrap = new ServerBootstrap();

		bootstrap.group(new NioEventLoopGroup())
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) {
					ch.pipeline().addLast(new MessageHandler());
				}
			});

		ChannelFuture future = bootstrap.bind(new InetSocketAddress(Integer.parseInt(args[0])));

		future.addListener((ChannelFutureListener) futureListener -> {
			if(futureListener.isSuccess()) {
				System.out.println("Chat server started.");
			} else {
				System.err.println("Starting chat server failed.");
			}
		});
	}
}
