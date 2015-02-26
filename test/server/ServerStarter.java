package server;

import java.net.URISyntaxException;



public class ServerStarter extends Thread {
	public Server server;
        
    public ServerStarter(Server mServer) {
    	this.server = mServer;
    }

   public void run() {
	   System.out.println("in " + getDefaultUncaughtExceptionHandler());
    	try {
			server.start();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}


