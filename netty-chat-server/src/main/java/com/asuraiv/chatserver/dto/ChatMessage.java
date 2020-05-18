package com.asuraiv.chatserver.dto;

import java.io.Serializable;

public class ChatMessage implements Serializable {

	private String userId;
	private String message;

	public ChatMessage(String userId, String message) {
		this.userId = userId;
		this.message = message;
	}
}
