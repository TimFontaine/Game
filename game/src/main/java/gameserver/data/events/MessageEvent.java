package gameserver.data.events;

import gameserver.data.Connection;

import java.util.List;

public class MessageEvent {

	private String message;
	
	private List<Connection> receivers;
	
	public MessageEvent(String message, List<Connection> list) {
		this.message = message;
		this.receivers = list;
		
	}

	public String getMessage() {
		return message;
	}

	public List<Connection> getReceivers() {
		return receivers;
	}


}
