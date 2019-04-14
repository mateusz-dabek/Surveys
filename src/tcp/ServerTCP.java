package tcp;

import java.awt.EventQueue;
import java.io.*; 
import java.net.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.swing.UIManager;

import database.DatabaseHandler;
import gui.FrameServer;
import gui.PanelServer;
 
/**
 * The TCP server waits for a connection to the client and starts a thread where communication
 * between the client and the server takes place
 */
public class ServerTCP{ 
	
    public static void main(String[] args) throws IOException { 
    	
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
       
    	ServerSocket serverSocket = new ServerSocket(5056); 
        ExecutorService exe = Executors.newFixedThreadPool(100);
        DatabaseHandler database = new DatabaseHandler();
        
        PanelServer panel = new PanelServer(database);        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
					new FrameServer(panel);
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        });
                
        while (true){ 
        	
        	Socket socket = null; 

            try { 
                socket = serverSocket.accept(); 
                  
                System.out.println("A new client is connected : " + socket); 
                
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
  
                ServerThreadTCP serverThreadTCP = new ServerThreadTCP(socket, bufferedReader, printWriter, database);
                FutureTask<String> ft = new FutureTask<String>(serverThreadTCP) {

					@Override
					protected void done() {
						try {
							System.out.println(get());
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
					}
                	
                };
                exe.execute(ft);
            } 
            catch (Exception e){ 
                e.printStackTrace(); 
            } 
            
        }
    } 
    
}
