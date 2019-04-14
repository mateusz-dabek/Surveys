package tcp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.Callable;

import database.DatabaseHandler;

/**
 * Responsible for data exchange with the client
 */
class ServerThreadTCP implements Callable<String>{ 
	
	 private final BufferedReader bufferedReader;
	 private final PrintWriter printWriter;
	 private final Socket socket; 
	 private final DatabaseHandler database;
	 
	 public ServerThreadTCP(Socket s, BufferedReader in, PrintWriter out, DatabaseHandler database) { 
	     this.socket = s; 
	     this.bufferedReader = in; 
	     this.printWriter = out; 
	     this.database = database;
	 } 
	
	 @Override
	 public String call() { 
		 
		 String idClient = null;
	     String numberSurvey = null;
	     String answer1 = null;
	     String answer2 = null;
	     String answer3 = null;
	     
         try {
        	 idClient = bufferedReader.readLine();
        	 while(database.checkExistId(idClient) == true) {
        		 printWriter.println("false");
        		 printWriter.flush();
        		 idClient = bufferedReader.readLine();
        	 }
        	 if(database.checkExistId(idClient) == false) {
        		 printWriter.println("true");
        		 printWriter.flush();
        	 }
        		 
        	 printWriter.println(database.getSurveyAmount());
        	 printWriter.println(database.getNameSurveys());
        	 printWriter.flush();
        	 
             numberSurvey = bufferedReader.readLine();
             printWriter.println(database.getQuestionSurvey(numberSurvey, "QUESTION1", "ANSWER11", "ANSWER12", "ANSWER13"));
             printWriter.flush();
             
             answer1 = bufferedReader.readLine();
             printWriter.println(database.getQuestionSurvey(numberSurvey, "QUESTION2", "ANSWER21", "ANSWER22", "ANSWER23"));
             printWriter.flush();
             
             answer2 = bufferedReader.readLine();
             printWriter.println(database.getQuestionSurvey(numberSurvey, "QUESTION3", "ANSWER31", "ANSWER32", "ANSWER33"));
             printWriter.flush();
             
             answer3 = bufferedReader.readLine();
             
             database.insertIntoResult(numberSurvey, answer1, answer2, answer3);
             database.insertIntoAnswer(numberSurvey, idClient, answer1, answer2, answer3);
             
             printWriter.println(database.returnAmount(numberSurvey, "ANSWER1A"));
             printWriter.println(database.returnAmount(numberSurvey, "ANSWER1B"));
             printWriter.println(database.returnAmount(numberSurvey, "ANSWER1C"));
             printWriter.println(database.returnAmount(numberSurvey, "ANSWER2A"));
             printWriter.println(database.returnAmount(numberSurvey, "ANSWER2B"));
             printWriter.println(database.returnAmount(numberSurvey, "ANSWER2C"));
             printWriter.println(database.returnAmount(numberSurvey, "ANSWER3A"));
             printWriter.println(database.returnAmount(numberSurvey, "ANSWER3B"));
             printWriter.println(database.returnAmount(numberSurvey, "ANSWER3C"));
             printWriter.flush();
             
             if(!socket.isClosed())
             	socket.close(); 
             
         } catch (IOException e) { 
             e.printStackTrace(); 
         } catch (SQLException e) {
			e.printStackTrace();
         }
     
     return "The survey number " + numberSurvey + " was completed by a new user with id = " + idClient + " , his answers it " + answer1 + ", " + answer2 + ", " + answer3 + "\n";
	 }
	 
}