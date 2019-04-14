package tcp;
  
import java.io.*; 
import java.net.*; 
import java.util.Scanner;

import javax.swing.UIManager;

import gui.FrameChart;
import gui.FrameClient;
import gui.PanelClient;

/**
 * Expects the customer to provide the ID and select the survey. Then a window is generated to 
 * complete this survey. After filling, a graph is displayed.
 */
public class ClientTCP { 
	
	/**
	 * Checks if the id is an integer
	 * 
	 * @param id - id provided by the user
	 * @return true/false
	 */
	public static boolean checkId(String id) {
	    try {
	        Long number = Long.parseLong(id);
	        return true;
	    } catch (Exception e) {
	    	System.out.println("Id must be a number, try again");
	        return false;
	    }
	}
	
    public static void main(String[] args) throws IOException { 
    	
    	try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        try{ 
            Scanner scanner = new Scanner(System.in); 
            InetAddress ip = InetAddress.getByName("localhost");
            Socket socket = new Socket(ip, 5056); 
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            String numberSurvey;
            String idClient;
       
            if (true) { 
            	
            	System.out.print("Give your id: ");
            	idClient = scanner.nextLine();
            	while(checkId(idClient) == false) {
        			idClient = scanner.nextLine();				
        		}
            	printWriter.println(idClient);
            	printWriter.flush();
            	
            	while(bufferedReader.readLine().equals("false")) {
            		System.out.println("Id exists, choose other");
            		idClient = scanner.nextLine();
                	while(checkId(idClient) == false) {
            			idClient = scanner.nextLine();				
            		}
                	printWriter.println(idClient);
                	printWriter.flush();
            	}
            	
            	String surveyAmount = bufferedReader.readLine();
            	for(int i = 1; i <= Integer.parseInt(surveyAmount) + 1; i++) {
            		System.out.println(bufferedReader.readLine());
            	}
            	
                numberSurvey = scanner.nextLine(); 
                printWriter.println(numberSurvey); 
                printWriter.flush();
                
                String question = bufferedReader.readLine();
                String answer1 = bufferedReader.readLine();
                String answer2 = bufferedReader.readLine();
                String answer3 = bufferedReader.readLine();
                bufferedReader.readLine();
                
                PanelClient panel = new PanelClient(socket, question, answer1, answer2, answer3);
                FrameClient frame = new FrameClient(panel);
                
                question = bufferedReader.readLine();
                answer1 = bufferedReader.readLine();
                answer2 = bufferedReader.readLine();
                answer3 = bufferedReader.readLine();
                bufferedReader.readLine();
                
                panel = new PanelClient(socket, question, answer1, answer2, answer3);
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                frame.getContentPane().add(panel);
                frame.getContentPane().revalidate();
                
                question = bufferedReader.readLine();
                answer1 = bufferedReader.readLine();
                answer2 = bufferedReader.readLine();
                answer3 = bufferedReader.readLine();
                bufferedReader.readLine();
                
                panel = new PanelClient(socket, question, answer1, answer2, answer3);
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                frame.getContentPane().add(panel);
                frame.getContentPane().revalidate();
                
                while(true) {
                	Thread.sleep(10);
                	if(panel.getFlag()) {
                		frame.dispose();
                		break;
                	}else
                		continue;
                }
                String a1 = bufferedReader.readLine();
                String a2 = bufferedReader.readLine();
                String a3 = bufferedReader.readLine();
                String a4 = bufferedReader.readLine();
                String a5 = bufferedReader.readLine();
                String a6 = bufferedReader.readLine();
                String a7 = bufferedReader.readLine();
                String a8 = bufferedReader.readLine();
                String a9 = bufferedReader.readLine();
                
                try {
        			FrameChart fChart = new FrameChart(numberSurvey, a1, a2, a3, a4, a5, a6, a7, a8, a9);
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
                socket.close();

            } 
            scanner.close(); 
               
        }catch(Exception e) { 
            e.printStackTrace(); 
        } 
    } 
} 