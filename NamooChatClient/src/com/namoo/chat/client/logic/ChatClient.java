package com.namoo.chat.client.logic;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.namoo.chat.core.Member;
import com.namoo.chat.core.MemberListMessage;
import com.namoo.chat.core.Message;
import com.namoo.chat.core.MessageType;
import com.namoo.chat.core.NamooChatConsts;

public class ChatClient {

	
	
	private static Socket socket;
	
	private static Member member;
	
	private static ChatClient instance = new ChatClient();
	
	private static OutputStream os;
	private static ObjectOutputStream oos;
	
	private static InputStream is;
	private static ObjectInputStream ois;

	
	
	private ChatClient(){
		//
		try {
			socket = new Socket("192.168.0.34", 9090);
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ChatClient getInstance(){
		//
		return instance;
	}
	
	public static boolean requestJoin(String userName) throws UnknownHostException, IOException, ClassNotFoundException{
		//
		member = new Member(userName);
		Message message = new Message(MessageType.RequestJoin, member.getNickName());
		
		oos.writeObject(message);
		oos.flush();
		
		//----------------------------------------------------------------------
		
		Message message2 = (Message) ois.readObject();
		if(message2.getType()==(MessageType.AcceptJoin)){
			return true;
		}
		else
			return false;
		
	}
	
	public void sendMessage(String inputMessage) throws IOException, ClassNotFoundException{
		//
		
		Message message = new Message(MessageType.SimpleMessage, member.getNickName());
		message.setBody(inputMessage);
		
		
		oos.writeObject(message);
		oos.flush();
	}
	
	

	
	/*public MemberListMessage readListMessage(){
		//
		MemberListMessage listMessage = null;
		synchronized (ois) {
			try {
				Object object = ois.readObject();
				if(object instanceof MemberListMessage){
					listMessage = (MemberListMessage) object;
				return listMessage;
				}	
			}
			catch(Exception e){
			}
			return null;
		}
		
	}*/
	
	public Message readMessage() {
		//
		Message message = null;
		synchronized (ois) {
			try {
				Object object = ois.readObject();
				if(object instanceof Message){
					message = (Message) object;
					
				return message;
				}	
			}
			catch(Exception e){
			}
			return null;
		}
	}
	
	public Message requestList(){
		//
		Message message = new Message(MessageType.RequestList, member.getNickName());
		
		synchronized (oos) {
			try {
				Thread.sleep(2000);
				oos.writeObject(message);
				oos.flush();
				
				//--------------------------------------------------------------------------------
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}

	
}

