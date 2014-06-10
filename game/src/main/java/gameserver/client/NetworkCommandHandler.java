package gameserver.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import javax.jws.HandlerChain;

import org.codehaus.groovy.binding.PropertyBinding.UpdateStrategy;

import gameserver.cdi.qualifiers.Client;
import gameserver.cdi.qualifiers.ClientUpdate;
import gameserver.cdi.qualifiers.NetworkUpdate;
import gameserver.commands.GameCommand;
import gameserver.data.Connection;
import gameserver.data.events.ConnectionEvent;
import gameserver.server.GameEvent;
import gameserver.server.executors.Network;

public class NetworkCommandHandler implements ClientCommandHandler, Connection {
	
	ObjectOutputStream oos;
	

	private ObjectInputStream ois;


	private Receiver receiver;
	
	private boolean localConnection;

	@Inject private Event<ConnectionEvent> connectionEvent;

	@Inject @Client private Event<GameEvent> gameEvent;
	
	
	private Socket socket;
	public NetworkCommandHandler(Socket socket, boolean localConnection) {
		this.socket = socket;
		this.localConnection = localConnection;
	}
	
	@PostConstruct
	public void init() {
		OutputStream outputStream;
		try {
			outputStream = socket.getOutputStream();
			oos = new ObjectOutputStream(outputStream);
			
			receiver = new Receiver();
			new Thread(receiver).start();
			connectionEvent.fire(new ConnectionEvent(this));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void handle(GameCommand command) {
		try {
			oos.writeObject(command);
			System.out.println("sending command done");
		} catch (IOException exception) {
			System.out.println("exceotuib");
			exception.printStackTrace();
		}
	}
	
	private class Receiver implements Runnable {
		BlockingQueue<GameEvent> eventQueue = new ArrayBlockingQueue<GameEvent>(1);

		
		@Override
		public void run() {
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				GameEvent event = (GameEvent) ois.readObject();
				System.out.println("message coming in at:" + System.currentTimeMillis());
				System.out.println("client receives gameevent:" +event.toString());
				if (!localConnection) {
					gameEvent.select(new AnnotationLiteral<NetworkUpdate>() {}).fire(event);
				} else {
					gameEvent.select(new AnnotationLiteral<ClientUpdate>(){}).fire(event);
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public GameEvent getReturnMessage() {
		System.out.println("wainting for poll");
		return receiver.eventQueue.poll();
	}

}
