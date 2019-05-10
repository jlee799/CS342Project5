package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;
import java.util.ArrayList;

public abstract class NetworkConnection {
	
	private ConnThread connthread = new ConnThread();
	private Consumer<Serializable> callback;
	
	public NetworkConnection( Consumer<Serializable> callback) {
		this.callback = callback;
		connthread.setDaemon(true);
	}
	
	public void startConn() throws Exception{
		connthread.start();
	}
	
	public void sendAll( Serializable data) throws Exception{
		
		for( int i = 0; i < connthread.arrayCT.size(); ++i) {
			connthread.arrayCT.get(i).oos.writeObject(data);
		}
	}
	
	// will send the string to the client who has that clientNum. clientNum != index of arrayCT
	
	public void sendClient( String clientNumTo, String stringToSend) throws Exception{
		int i = 0;
		int size = this.connthread.arrayCT.size();
		
		while( i < size) {
			if( this.connthread.arrayCT.get(i).clientNum.equals(clientNumTo) ) {
				connthread.arrayCT.get(i).oos.writeObject( stringToSend);
				break;
			}
			else {
				i++;
			}
		}
	}
	
	public boolean clientExists( String id) {
		int i = 0;
		int size = this.connthread.arrayCT.size();
		
		while( i < size) {
			if( this.connthread.arrayCT.get(i).clientNum.equals(id) ) {
				return true;
			}
			else {
				i++;
			}
		}
		return false;
	}
	
	
	public int getClientThreadSize() {
		return this.connthread.arrayCT.size();
	}
	
	/*
	public void makeClientBusy( String clientNum) {
		int i = 0;
		int size = this.connthread.arrayCT.size();
		
		while( i < size) {
			if( this.connthread.arrayCT.get(i).clientNum.equals(clientNum)) {
				connthread.arrayCT.get(i).busy = true;
				break;
			}
			else {
				i++;
			}
		}
	}
	
	public void resetBusy( String clientNum) {
		int i = 0;
		int size = this.connthread.arrayCT.size();
		
		while( i < size) {
			if( this.connthread.arrayCT.get(i).clientNum.equals(clientNum)) {
				connthread.arrayCT.get(i).busy = false;
				break;
			}
			else {
				i++;
			}
		}
	}
	
	public boolean isClientBusy( String clientNum) {
		int i = 0;
		int size = this.connthread.arrayCT.size();
		
		while( i < size) {
			if( this.connthread.arrayCT.get(i).clientNum.equals(clientNum)) {
				return connthread.arrayCT.get(i).busy;
			}
			else {
				i++;
			}
		}
		return true;
	}
	
	public void setClientMove( String clientNum, String move) {
		int i = 0;
		int size = this.connthread.arrayCT.size();
		
		while( i < size) {
			if( this.connthread.arrayCT.get(i).clientNum.equals(clientNum)) {
				connthread.arrayCT.get(i).move = move;
				break;
			}
			else {
				i++;
			}
		}
	}
	
	public void resetClientMove( String clientNum) {
		int i = 0;
		int size = this.connthread.arrayCT.size();
		
		while( i < size) {
			if( this.connthread.arrayCT.get(i).clientNum.equals(clientNum)) {
				connthread.arrayCT.get(i).move = null;
				break;
			}
			else {
				i++;
			}
		}
	}
	
	public String getClientMove( String clientNum) {
		int i = 0;
		int size = this.connthread.arrayCT.size();
		
		while( i < size) {
			if( this.connthread.arrayCT.get(i).clientNum.equals(clientNum)) {
				return connthread.arrayCT.get(i).move;
			}
			else {
				i++;
			}
		}
		return "";
	}
	
	public boolean clientExists( String clientNum) {
		
		int i = 0;
		int size = this.connthread.arrayCT.size();
		
		while( i < size) {
			if( this.connthread.arrayCT.get(i).clientNum.equals(clientNum)) {
				return true;
			}
			else {
				i++;
			}
		}
		return false;
	}
	*/
	public void closeConnNum( String clientNum) throws Exception{
		int i = 0;
		int size = this.connthread.arrayCT.size();
		
		while( i < size) {
			if( this.connthread.arrayCT.get(i).clientNum.equals(clientNum)) {
				connthread.unusedNums.add( clientNum);
				connthread.arrayCT.get(i).s.close();
				connthread.arrayCT.remove(i);
				break;
			}
			else {
				i++;
			}
		}
	}
	/*
	public void sendClientsAlreadyCon( String clientNum) {
		int i = 0;
		int size = this.connthread.arrayCT.size();
		
		while( i < size) {
			if( ! this.connthread.arrayCT.get(i).clientNum.equals( clientNum)) {
				try {
					sendClient( clientNum, "10-" + connthread.arrayCT.get(i).clientNum );
					System.out.println( "10-" + connthread.arrayCT.get(i).clientNum ); 	// this line prints each string this function
																						// is sending to the console
				}
				catch(Exception e) {
					System.out.println( " Error: Failed to send client list.");
				}
				i++;
			}
			else {
				i++;
			}
		}
	}
	*/
	public void sendClientInd( int ind, Serializable data) throws Exception{
		
		connthread.arrayCT.get(ind).oos.writeObject(data);
		
	}
	
	public void closeConn() throws Exception{
		
		for( int i = 0; i < connthread.arrayCT.size(); ++i) {
			connthread.arrayCT.get(0).s.close();
		}
		
	}
	
	public ConnThread getConnThread() {
		return this.connthread;
	}
	
	abstract protected String getIP();
	abstract protected int getPort();
	

	
	class ConnThread extends Thread{
		
		//for multiple clients
		private ArrayList<ClientThread> arrayCT;
		private int clientIDCounter;
		private ArrayList<String> unusedNums;
		
		public ConnThread() {
			arrayCT = new ArrayList<ClientThread>();
			unusedNums = new ArrayList<String>();
			clientIDCounter = 1;
		}

		
		public void run() {
			
			ServerSocket ss = null;
			// try ( ServerSocket ss = new ServerSocket( getPort()) ){
			try{
			ss = new ServerSocket( getPort());
			
			}
			catch( Exception e) {
				callback.accept("Connection Closed");
			}
			
			
			while( true) {
				
				while( arrayCT.size() < 4)	{
					
					Socket s = null;
				
					try {
						s = ss.accept();
						System.out.println("A new client is connected: " + s);
					
						//getting input and output streams
						ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
						ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					
						System.out.println("Assigning new thread for this client");
					
						// create a new thread object
						arrayCT.add( new ClientThread( s, ois, oos) );
						System.out.println( "arrayCT size: " + arrayCT.size());
					
						// if no unused nums from clients closing get new number
						if( unusedNums.size() == 0) {
							arrayCT.get( arrayCT.size() - 1 ).clientNum = Integer.toString( clientIDCounter);
							clientIDCounter++;
						}
						else {		// if there are collected unused numbers use the oldest one
							arrayCT.get( arrayCT.size() - 1 ).clientNum = unusedNums.get(0);
							unusedNums.remove(0);
						}
						// send the client their clientID
						sendClientInd( arrayCT.size() - 1, "id-" + arrayCT.get( arrayCT.size()-1).clientNum );
						// invoking the start method
						arrayCT.get( arrayCT.size() - 1 ).start();
					}
					catch(Exception e){
						callback.accept("Failed to accept");
						try {
							s.close();
						}
						catch(Exception e1) {
							return;
						}
						e.printStackTrace();
					}
				}	
			}
			/*
			}
			catch( Exception e) {
				callback.accept("Connection Closed");
			}
			*/
		}
		
		public ArrayList<ClientThread> getArrayCT(){
			return this.arrayCT;
		}
	}
	
	class ClientThread extends Thread {
		
		final Socket s;
		final ObjectOutputStream oos;
		final ObjectInputStream ois;
		private String clientNum;
		//private boolean busy;
		//private String move;
		
		public ClientThread( Socket s, ObjectInputStream ois, ObjectOutputStream oos) throws Exception{
			this.s = s;
			this.ois = ois;			// input stream
			this.oos = oos;			// output stream
			this.clientNum = null;	// will be given a number when accepted
			//this.busy = false;		// will get updated when client awaiting move from player
			//this.move = null;		// move saved here so server knows who won
		}
		
		public void run() {
			
			try {
				s.setTcpNoDelay(true);
				while(true) {

					Serializable game = (Serializable) ois.readObject();
					callback.accept(game);
				}
			}
			catch(Exception e){
				System.out.println("client disconnected");
				//callback.accept("Connection Closed");
			}
				
		}
		
		public Socket getSocket() {
			return this.s;
		}
		
		public String getClientNum() {
			return this.clientNum;
		}
			
	}
		
	
		
}



