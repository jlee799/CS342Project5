package Server;

import java.io.Serializable;
import java.util.function.Consumer;

public class ServerRPS extends NetworkConnectionRPS{
	
	private int port;
	
	public ServerRPS( int port, Consumer<Serializable> callback) {
		
		super( callback);
		this.port = port;
	}
	
	protected String getIP() {
		return null;
	}
	
	protected int getPort() {
		return port;
	}
	
	protected void setPort( int port) {
		this.port = port;
	}

}
