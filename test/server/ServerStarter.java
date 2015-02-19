package server;



public class ServerStarter extends Thread {
	public Server server;
        
    public ServerStarter(Server mServer) {
    	this.server = mServer;
    }

   public void run() {
	   System.out.println("in " + getDefaultUncaughtExceptionHandler());
    	server.start();

    }
}


