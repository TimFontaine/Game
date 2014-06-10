package gameserver.server.core;

import gameserver.cdi.qualifiers.Server;
import gameserver.data.Connection;
import gameserver.data.events.ConnectionEvent;
import gameserver.data.events.MessageEvent;
import gameserver.server.GameEvent;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import com.google.common.eventbus.EventBus;

@ApplicationScoped
public class GuavaEventBus {
	
	    private EventBus eventBus = new EventBus();
	    
	    private List<Connection> connections = new ArrayList<Connection>(); 
	    
	    public void broadcastEveryEvent(@Observes @Server GameEvent event) {
	        // There are a lot of bradcasted events, it may be a good solution to filter them.
	    	System.out.println("firing event via event bus");
	        eventBus.post(event);
	    }
	    
	    public void register(@Observes ConnectionEvent bean) {
	        eventBus.register(bean.getConnection());
	    }

	    public void unregister(@Observes ConnectionEvent bean) {
	        eventBus.unregister(bean);
	    }
	    
	    public void resetAllConnections() {
	    	for (Connection connection : connections) {
//	    		messageEventBus.unregister(connection);
	    		eventBus.unregister(connection);
	    	}
	    }
	    
	    public void broadCastMessage(@Observes @Server MessageEvent messageEvent) {
	    	EventBus messageEventBus = new EventBus("messageBus");
	    	for (Connection connection : messageEvent.getReceivers()) {
	    		messageEventBus.register(connection);
	    	}
	    	messageEventBus.post(messageEvent.getMessage());
	    }
}
