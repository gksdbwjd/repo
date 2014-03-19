package com.namoo.chat.client.gui;

import com.namoo.chat.client.logic.ChatClient;
import com.namoo.chat.core.MemberListMessage;
import com.namoo.chat.core.Message;
import com.namoo.chat.core.MessageType;

public class MessageSender implements Runnable {

	private ChatClient chatClient;
	private IChatMessage chatMessage;

	public MessageSender(ChatClient chatClient, IChatMessage chatMessage) {

		this.chatClient = chatClient;
		this.chatMessage = chatMessage;
	}
	
	@Override
	public void run() {
		//
		while (true) {
			chatClient.requestList();
			
			Message message = chatClient.readMessage();

			if (message != null) {
				//
				if (message.getType() == MessageType.ResponseList) {
					MemberListMessage listMessage = (MemberListMessage) message;
					chatMessage.appendList(listMessage);
				} else
					chatMessage.appendMessage(message);
			}

		}

	}

}
