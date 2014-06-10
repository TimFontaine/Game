package gameserver.server.communication;

import gameserver.commands.GameCommand;
import gameserver.data.Connection;
import gameserver.data.events.ConnectionEvent;
import gameserver.events.GameStartedEvent;
import gameserver.server.GameEvent;
import gameserver.server.executors.Modus;
import gameserver.server.executors.ModusEnum;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;

import com.google.common.eventbus.Subscribe;

public class NetworkConnection implements Connection {
	
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private Socket socket;
	
	@Inject private Event<GameCommand> gameCommandEvent;

	@Inject Event<ConnectionEvent> connectionEvent;
	
	public NetworkConnection(Socket socket) {
		this.socket = socket;
//		try {
//			
//			OutputStream outputStream = socket.getOutputStream();
//			
//			oos = new ObjectOutputStream(outputStream);
//			Thread receivingthread = new Thread(new Receiver());
//			receivingthread.start();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		System.out.println("ending connection");
	}
	
	@PostConstruct
	public void init() {
		try {
			
			OutputStream outputStream = socket.getOutputStream();
			
			oos = new ObjectOutputStream(outputStream);
			Thread receivingthread = new Thread(new Receiver());
			receivingthread.start();
			connectionEvent.fire(new ConnectionEvent(this));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class Receiver implements Runnable {

		@Override
		public void run() {
			InputStream inputStream = null;
			try {
				inputStream = socket.getInputStream();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			while (true) {
				try {
					ois = new ObjectInputStream(inputStream); 
					GameCommand command = (GameCommand) ois.readObject();
					System.out.println("network connection has received command");
					gameCommandEvent.fire(command);
					
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	@Subscribe
	public void handleGameEvent(GameEvent gameEvent) {
		try {
			System.out.println("server is writing gameevent");
			oos.writeObject(gameEvent);
		} catch (IOException e) {
			System.out.println("error whil connection is sending event ");
			e.printStackTrace();
		};
	}
	
//	public void onGameEvent(@Observes GameEvent gameEvent) {
//		System.out.println("the network connection receives its game event");
//		handleGameEvent(gameEvent);
//	}

}
