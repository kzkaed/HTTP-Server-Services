package server;

public class ServerStarter extends Thread {
	public Server server;
        
    public ServerStarter(Server mServer) {
    	this.server = mServer;
    }

   public void run() {
			server.start();

    }
}


