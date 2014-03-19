package com.namoo.chat.client.gui;

import com.namoo.chat.core.MemberListMessage;
import com.namoo.chat.core.Message;

public interface IChatMessage{

	void appendMessage(Message message);

	void appendList(MemberListMessage message);
	
}
