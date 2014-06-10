package gameserver.server;

import gameserver.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class EventObserver {
	
	private List<Client> clients = new ArrayList<Client>();

	public void update(GameEvent gameEvent) {
		for (Client client : clients) {
			System.out.println("observer sending to client");
			client.handleGameEvent(gameEvent);
		}
	}
	
	public void registerClient(Client client) {
		clients.add(client);
	}
	
	public void registerAllClients(List<Client> clients) {
		this.clients.addAll(clients);
		System.out.println("eventobserver size : " + clients.size());
	}

}
