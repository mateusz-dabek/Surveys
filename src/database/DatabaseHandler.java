package database;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;

import javax.swing.UIManager;

import gui.FrameConnection;
import gui.PanelConnection;

public class DatabaseHandler {
	
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String user;
	private String password;
	private String hostname;
	private int port;
	private static int amount = 11;
	
	public DatabaseHandler(){
		writeDataToConnection();
		loadDriver();
		connectToDatabase(hostname, port);
		createStatement();
		prepareDatabase();
	}
	
	/**
	 * Displays a window in which to complete the data to connect to the database, 
	 * then retrieves this data and uses it to connect
	 */
	public void writeDataToConnection() {
		PanelConnection panel = new PanelConnection();
		FrameConnection frame = null;
		try {
			frame = new FrameConnection(panel);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(!panel.getFlag()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		user = panel.getUser();
		password = panel.getPassword();
		hostname = panel.getHostname();
		port = Integer.parseInt(panel.getPort());
		frame.dispose();
	}
	
	/**
	 * The method loads the jdbc driver
	 * @return true/false
	 */
	@SuppressWarnings("deprecation")
	public boolean loadDriver() {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	        return true;
	    } catch (Exception e) {
	        System.out.println("Error when loading the driver for the database");
	        return false;
	    }
	}
	
	/**
	 * The method is used to connect to the database
	 * 
	 * @param adress - database address
	 * @param port - database port
	 * @return connection with database
	 */
	public Connection connectToDatabase(String adress, int port) {
        Properties connectionProps = new Properties();
        connectionProps.put("user", user);
        connectionProps.put("password", password);
        connectionProps.put("useUnicode", "true");
        connectionProps.put("useJDBCCompliantTimezoneShift", "true");
        connectionProps.put("useLegacyDatatimeCode", "false");
        connectionProps.put("serverTimezone", "UTC");
        connectionProps.put("useSSL", "false");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + adress + ":" + port + "/",
                    connectionProps);
        } catch (SQLException e) {
        	System.out.println("Error when connecting to the database");
            e.printStackTrace();
        }
        return connection;
    }
	
	/**
	 * Creating a Statement object that sends queries to the connection database
	 * 
	 * @return Statement object sending database queries
	 */
	public Statement createStatement() {
	    try {
	    	statement = connection.createStatement();
	        return statement;
	    } catch (SQLException e) {
	    	System.out.println("Error when creating statement for database");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	/**
	 * Performs all inquiries on the database necessary before the user starts filling in the questionnaires
	 */
	public void prepareDatabase()  {
		ResultSet resultSet = executeQuery("SHOW DATABASES;");
		boolean flag = true;
		try{
			while(resultSet.next())
				if(resultSet.getString("Database").equals("surveys"))
					flag = false;
		}catch(SQLException sqlException) {
			System.out.println("Database access error or other errors");
			sqlException.printStackTrace();
		}
		if(flag) {
			executeUpdate("CREATE DATABASE SURVEYS;");
			executeUpdate("USE SURVEYS");
			executeUpdate("CREATE TABLE SURVEY(ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255) NOT NULL, "
					+ "QUESTION1 VARCHAR(255) NOT NULL, ANSWER11 VARCHAR(255) NOT NULL, ANSWER12 VARCHAR(255) NOT NULL, ANSWER13 VARCHAR(255) NOT NULL, "
					+ "QUESTION2 VARCHAR(255) NOT NULL, ANSWER21 VARCHAR(255) NOT NULL, ANSWER22 VARCHAR(255) NOT NULL, ANSWER23 VARCHAR(255) NOT NULL, "
					+ "QUESTION3 VARCHAR(255) NOT NULL, ANSWER31 VARCHAR(255) NOT NULL, ANSWER32 VARCHAR(255) NOT NULL, ANSWER33 VARCHAR(255) NOT NULL);");
			executeUpdate("CREATE TABLE ANSWER(ID_ANSWER INT PRIMARY KEY AUTO_INCREMENT, ID_SURVEY INT NOT NULL,"
					+ "ID_USER VARCHAR(255), ANSWER1 VARCHAR(255) NOT NULL, ANSWER2 VARCHAR(255) NOT NULL, ANSWER3 VARCHAR(255) NOT NULL);");
			executeUpdate("CREATE TABLE RESULT(ID_RESULT INT PRIMARY KEY AUTO_INCREMENT, ID_SURVEY INT NOT NULL, ANSWER1A INT NOT NULL, "
					+ "ANSWER1B INT NOT NULL, ANSWER1C INT NOT NULL, ANSWER2A INT NOT NULL, ANSWER2B INT NOT NULL, ANSWER2C INT NOT NULL, ANSWER3A INT NOT NULL,"
					+ "ANSWER3B INT NOT NULL, ANSWER3C INT NOT NULL);");
			executeUpdate("INSERT INTO RESULT(ID_SURVEY, ANSWER1A, ANSWER1B, ANSWER1C, ANSWER2A, ANSWER2B, ANSWER2C, ANSWER3A, ANSWER3B, ANSWER3C) VALUES(1, 0, 0, 0, 0, 0, 0, 0, 0, 0);");
			executeUpdate("INSERT INTO RESULT(ID_SURVEY, ANSWER1A, ANSWER1B, ANSWER1C, ANSWER2A, ANSWER2B, ANSWER2C, ANSWER3A, ANSWER3B, ANSWER3C) VALUES(2, 0, 0, 0, 0, 0, 0, 0, 0, 0);");
			executeUpdate("INSERT INTO RESULT(ID_SURVEY, ANSWER1A, ANSWER1B, ANSWER1C, ANSWER2A, ANSWER2B, ANSWER2C, ANSWER3A, ANSWER3B, ANSWER3C) VALUES(3, 0, 0, 0, 0, 0, 0, 0, 0, 0);");
			executeUpdate("INSERT INTO RESULT(ID_SURVEY, ANSWER1A, ANSWER1B, ANSWER1C, ANSWER2A, ANSWER2B, ANSWER2C, ANSWER3A, ANSWER3B, ANSWER3C) VALUES(4, 0, 0, 0, 0, 0, 0, 0, 0, 0);");
			executeUpdate("INSERT INTO RESULT(ID_SURVEY, ANSWER1A, ANSWER1B, ANSWER1C, ANSWER2A, ANSWER2B, ANSWER2C, ANSWER3A, ANSWER3B, ANSWER3C) VALUES(5, 0, 0, 0, 0, 0, 0, 0, 0, 0);");
			executeUpdate("INSERT INTO RESULT(ID_SURVEY, ANSWER1A, ANSWER1B, ANSWER1C, ANSWER2A, ANSWER2B, ANSWER2C, ANSWER3A, ANSWER3B, ANSWER3C) VALUES(6, 0, 0, 0, 0, 0, 0, 0, 0, 0);");
			executeUpdate("INSERT INTO RESULT(ID_SURVEY, ANSWER1A, ANSWER1B, ANSWER1C, ANSWER2A, ANSWER2B, ANSWER2C, ANSWER3A, ANSWER3B, ANSWER3C) VALUES(7, 0, 0, 0, 0, 0, 0, 0, 0, 0);");
			executeUpdate("INSERT INTO RESULT(ID_SURVEY, ANSWER1A, ANSWER1B, ANSWER1C, ANSWER2A, ANSWER2B, ANSWER2C, ANSWER3A, ANSWER3B, ANSWER3C) VALUES(8, 0, 0, 0, 0, 0, 0, 0, 0, 0);");
			executeUpdate("INSERT INTO RESULT(ID_SURVEY, ANSWER1A, ANSWER1B, ANSWER1C, ANSWER2A, ANSWER2B, ANSWER2C, ANSWER3A, ANSWER3B, ANSWER3C) VALUES(9, 0, 0, 0, 0, 0, 0, 0, 0, 0);");
			executeUpdate("INSERT INTO RESULT(ID_SURVEY, ANSWER1A, ANSWER1B, ANSWER1C, ANSWER2A, ANSWER2B, ANSWER2C, ANSWER3A, ANSWER3B, ANSWER3C) VALUES(10, 0, 0, 0, 0, 0, 0, 0, 0, 0);");
			executeUpdate("INSERT INTO SURVEY(NAME, QUESTION1, ANSWER11, ANSWER12, ANSWER13, QUESTION2, ANSWER21, "
					+ "ANSWER22, ANSWER23, QUESTION3, ANSWER31, ANSWER32, ANSWER33) VALUES(\"Firefighter job\", \"What is the most dangerous in firefighter job in your opinion?\", "
					+ "\"Car trash\", \"Buildings on fire\", \"Agressive victims\", \"Who is better firefighter in your opinion?\", \"Man\", \"Woman\", "
					+ "\"Sex doesn't matter\", \"In your opinion, a firefighters are handsome?\", \"Yes\", \"No\", \"I never seem a firefighter\");");
			executeUpdate("INSERT INTO SURVEY(NAME, QUESTION1, ANSWER11, ANSWER12, ANSWER13, QUESTION2, ANSWER21, "
					+ "ANSWER22, ANSWER23, QUESTION3, ANSWER31, ANSWER32, ANSWER33) VALUES(\"Fures\", \"Which fures are the most fine in your opinion?\", "
					+ "\"Alpaca's fure\", \"Cat's fure\", \"Horse fure\", \"Which furry animal is the most cute in your opinion?\", \"Cat\", \"Badger\", "
					+ "\"Fox\", \"Which animal do you want to have in your home?\", \"Cat\", \"Dog\", \"Alpaca\");");
			executeUpdate("INSERT INTO SURVEY(NAME, QUESTION1, ANSWER11, ANSWER12, ANSWER13, QUESTION2, ANSWER21, "
					+ "ANSWER22, ANSWER23, QUESTION3, ANSWER31, ANSWER32, ANSWER33) VALUES(\"Music\", \"What kind of music are you listening?\", "
					+ "\"Glam metal\", \"Heavy metal\", \"Punk rock\", \"Which music band do you want to see on the live concert?\", \"Iron Maiden\", \"Lady Gaga\", "
					+ "\"Queen\", \"Do you want to be a member of music band?\", \"Yes\", \"No\", \"I don't know\");");
			executeUpdate("INSERT INTO SURVEY(NAME, QUESTION1, ANSWER11, ANSWER12, ANSWER13, QUESTION2, ANSWER21, "
					+ "ANSWER22, ANSWER23, QUESTION3, ANSWER31, ANSWER32, ANSWER33) VALUES(\"Family\", \"How big is your siblings?\", "
					+ "\"Less than 2 members\", \"2-3 members\", \"More than 3 members\", \"Are your parents works?\", \"Both parents are working\", \"One parent are working\", "
					+ "\"Any parent are working\", \"How is a average salary in your home?\", \"Less than 1500 PLN\", \"1500 PLN- 3000 PLN\", \"More than 3000 PLN\");");
			executeUpdate("INSERT INTO SURVEY(NAME, QUESTION1, ANSWER11, ANSWER12, ANSWER13, QUESTION2, ANSWER21, "
					+ "ANSWER22, ANSWER23, QUESTION3, ANSWER31, ANSWER32, ANSWER33) VALUES(\"House\", \"In what kind of house do you live?\", "
					+ "\"Single-family house\", \"Multi-family house\", \"Block of flats\", \"How is estimated surface of your house?\", \"Less than 50m^2\", \"50m^2 - 100m^2\", "
					+ "\"More than 100m^2\", \"How big is your backyard?\", \"Big\", \"Small\", \"I don't have a backyard\");");
			executeUpdate("INSERT INTO SURVEY(NAME, QUESTION1, ANSWER11, ANSWER12, ANSWER13, QUESTION2, ANSWER21, "
					+ "ANSWER22, ANSWER23, QUESTION3, ANSWER31, ANSWER32, ANSWER33) VALUES(\"Transport\", \"What kind of transport do you prefer?\", "
					+ "\"Public transport\", \"Private car\", \"Taxi\", \"Have you trying to pass exam on a driving licence?\", \"Yes\", \"No\", "
					+ "\"I'm too young to try\", \"If you have a car, what colour it will had?\", \"Red\", \"Blue\", \"Black\");");
			executeUpdate("INSERT INTO SURVEY(NAME, QUESTION1, ANSWER11, ANSWER12, ANSWER13, QUESTION2, ANSWER21, "
					+ "ANSWER22, ANSWER23, QUESTION3, ANSWER31, ANSWER32, ANSWER33) VALUES(\"Air and enviroment\", \"Are you have a fresh air in your city?\", "
					+ "\"Yes\", \"No\", \"I don't know\", \"Are you carring about enviroment?\", \"Yes\", \"No\", "
					+ "\"I'm trying\", \"Are you segregating rubbish?\", \"Always\", \"Never\", \"I'm trying\");");
			executeUpdate("INSERT INTO SURVEY(NAME, QUESTION1, ANSWER11, ANSWER12, ANSWER13, QUESTION2, ANSWER21, "
					+ "ANSWER22, ANSWER23, QUESTION3, ANSWER31, ANSWER32, ANSWER33) VALUES(\"Pets\", \"How many pets do you want to have in your home?\", "
					+ "\"A lot of\", \"Anyone\", \"One or two little pets\", \"What species of animals do you prefer?\", \"Reptile\", \"Fish\", "
					+ "\"Mammal\", \"What kind of animal are the most scare for you?\", \"Snakes\", \"Spiders\", \"Lizard\");");
			executeUpdate("INSERT INTO SURVEY(NAME, QUESTION1, ANSWER11, ANSWER12, ANSWER13, QUESTION2, ANSWER21, "
					+ "ANSWER22, ANSWER23, QUESTION3, ANSWER31, ANSWER32, ANSWER33) VALUES(\"Meal\", \"How many meals do you eat every day?\", "
					+ "\"Less than 3\", \"3-5\", \"More than 5\", \"What kind of meal do you prefer?\", \"Meat\", \"Vegetables\", "
					+ "\"Fruits\", \"Are you eating healthly?\", \"Yes\", \"No\", \"I'm trying\");");
			executeUpdate("INSERT INTO SURVEY(NAME, QUESTION1, ANSWER11, ANSWER12, ANSWER13, QUESTION2, ANSWER21, "
					+ "ANSWER22, ANSWER23, QUESTION3, ANSWER31, ANSWER32, ANSWER33) VALUES(\"Sport\", \"Do You like sport?\", "
					+ "\"Yes\", \"No\", \"A little\", \"Which sports do you prefer?\", \"Team sports\", \"Individual sport\", "
					+ "\"Calm sports like chess\", \"How do you determine your attiude in sport?\", \"Strong\", \"Fast\", \"Agile\");");
		}else {
			executeUpdate("USE SURVEYS;");
		}
	}
	
	/**
	 * Execution of the query and sending the results to the ResultSet object
	 * @param sql - sql question
	 * @return result queries
	 */
	public ResultSet executeQuery(String sql) {
	    try {
	        return statement.executeQuery(sql);
	    } catch (SQLException e) {
	    	System.out.println("Error while executing the query on the database");
	        e.printStackTrace();
	    }
	    return null;
	}
	
	/**
	 * Performs a query that does not return a result
	 * @param sql - sql question
	 * @return result queries
	 */
	public int executeUpdate(String sql) {
	       try {
	           return statement.executeUpdate(sql);
	       } catch (SQLException e) {
	    	   System.out.println("Error while executing the query without result on the database");
	           e.printStackTrace();
	       }
	       return -1;
	}
	/**
	 * The method returns the number of surveys in the database
	 * @return number of surveys
	 */
	public String getSurveyAmount() {
		Object obj = null;
		resultSet = executeQuery("SELECT COUNT(*) FROM SURVEY;");
		try {
			resultSet.next();
			obj = resultSet.getObject(1);
		}catch(SQLException sqlException) {
			System.out.println("Error while executing the query without result on the database");
	        sqlException.printStackTrace();
		}
		return obj.toString();
	}
	
	/**
	 * The method returns the names of all surveys in the database
	 * @return names of all surveys
	 */
	public String getNameSurveys() throws SQLException {
		resultSet = executeQuery("SELECT NAME FROM SURVEY;");
		String allNames ="";
		int number = 1;
		 while (resultSet.next()) {
             for (int i = 1; i <= 1; i++) {
                 Object obj = resultSet.getObject(i);
                 if (obj != null) {
                    allNames += number + ") " + obj.toString() + "\n";
                    number++;
                 }
             }
         }
		 return allNames;
	}
	
	/**
	 * The method returns a specific question and its possible answers
	 * @param numberSurvey - id survey
	 * @param numberQuestion - number question in survey
	 * @param numberFirstAnswer - first answer to question
	 * @param numberSecondAnser - second answer to question
	 * @param numberThirdAnswer - third answer to question
	 * @return all information as one String
	 */
	public String getQuestionSurvey(String numberSurvey, String numberQuestion, String numberFirstAnswer, String numberSecondAnser, String numberThirdAnswer) throws SQLException {
		resultSet = executeQuery("SELECT " + numberQuestion + ", " + numberFirstAnswer + ", " + numberSecondAnser + ", " + numberThirdAnswer + " FROM SURVEY WHERE ID = " + numberSurvey + ";");
		String allQuestion ="";
		 while (resultSet.next()) {
             for (int i = 1; i <= 4; i++) {
                 Object obj = resultSet.getObject(i);
                 if (obj != null) {
                    allQuestion +=  obj.toString() + "\n";
                 }
             }
         }
		 return allQuestion;
	}

	/**
	 * The method check user id (is it already in the database)
	 * 
	 * @param id - user id
	 * @return true/false
	 */
	public boolean checkExistId(String id) {
		resultSet = executeQuery("SELECT * FROM ANSWER WHERE ID_USER = " + id + ";");
		try {
			resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(readValueOne() == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * The method returns the first result from the database query
	 * 
	 * @return first result from the database query
	 */
	public String readValueOne() {
		Object obj;
		try {
			obj = resultSet.getObject(1);
		}catch(SQLException sqlException) {
			return null;
		}
	    return obj.toString();
	}
	
	/**
	 * The method returns the number of answers to a specific question from a specific questionnaire
	 * 
	 * @param id - survey id
	 * @param answer - answer id
	 * @return
	 */
	public int returnAmount(String id, String answer) {
		resultSet = executeQuery("SELECT " + answer + " FROM RESULT WHERE ID_SURVEY =" + id + ";");
		try {
			resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Integer.parseInt(readValueOne());
	}

	/**
	 * The method adds the user's answers to those already existing in the database
	 * 
	 * @param id - survey id
	 * @param answer1 - first answer user
	 * @param answer2 - second answer user
	 * @param answer3 - third answer user
	 */
	public void insertIntoResult(String id, String answer1, String answer2, String answer3) {
		int current1AAmount = returnAmount(id, "ANSWER1A");
		int current1BAmount = returnAmount(id, "ANSWER1B");
		int current1CAmount = returnAmount(id, "ANSWER1C");

		int current2AAmount = returnAmount(id, "ANSWER2A");
		int current2BAmount = returnAmount(id, "ANSWER2B");
		int current2CAmount = returnAmount(id, "ANSWER2C");
		
		int current3AAmount = returnAmount(id, "ANSWER3A");
		int current3BAmount = returnAmount(id, "ANSWER3B");
		int current3CAmount = returnAmount(id, "ANSWER3C");
		
		if(answer1.equals("a")) {
			current1AAmount++;
		}
		if(answer1.equals("b")) {
			current1BAmount++;
		}
		if(answer1.equals("c")) {
			current1CAmount++;
		}
		if(answer2.equals("a")) {
			current2AAmount++;
		}
		if(answer2.equals("b")) {
			current2BAmount++;
		}
		if(answer2.equals("c")) {
			current2CAmount++;
		}
		if(answer3.equals("a")) {
			current3AAmount++;
		}
		if(answer3.equals("b")) {
			current3BAmount++;
		}
		if(answer3.equals("c")) {
			current3CAmount++;
		}
		
		executeUpdate("UPDATE RESULT SET ANSWER1A = " + current1AAmount + ", ANSWER1B = " + current1BAmount + ", ANSWER1C = " 
		+ current1CAmount + ", ANSWER2A = " + current2AAmount + ", ANSWER2B = " +current2BAmount + ", ANSWER2C = " + current2CAmount 
		+ ", ANSWER3A = " + current3AAmount + ", ANSWER3B = " + current3BAmount + ", ANSWER3C = " + current3CAmount + " WHERE ID_SURVEY = " 
		+ id + ";");
	}
	
	/**
	 * Inserts a single result of the user filling the survey into the database
	 * 
	 * @param id - survey id
	 * @param idUser
	 * @param answer1 - first answer user
	 * @param answer2 - second answer user
	 * @param answer3 - third answer user
	 */
	public void insertIntoAnswer(String id, String idUser, String answer1, String answer2, String answer3) {
		executeUpdate("INSERT INTO ANSWER(ID_SURVEY, ID_USER, ANSWER1, ANSWER2, ANSWER3) VALUES(" + id + "," + idUser + ",'"+ answer1 + "','" + answer2 + "','" + answer3 + "')");
	}
	
	/**
	 * Inserts a record in the database in which the response values will be increased
	 */
	public void insertIntoResult() {
		executeUpdate("INSERT INTO RESULT(ID_SURVEY, ANSWER1A, ANSWER1B, ANSWER1C, ANSWER2A, ANSWER2B, ANSWER2C, ANSWER3A, ANSWER3B, ANSWER3C) VALUES(" + amount + ", 0, 0, 0, 0, 0, 0, 0, 0, 0);");
		amount++;
	}
	
	/**
	 * Added survey to database
	 * 
	 * @param name - name survey
	 * @param question1 - first question
	 * @param answer11 - first answer from first question
	 * @param answer12 - second answer from first question
	 * @param answer13 - third answer from first question
	 * @param question2 - second question
	 * @param answer21 - first answer from second question
	 * @param answer22 - second answer from second question
	 * @param answer23 - third answer from second question
	 * @param question3 - third question
	 * @param answer31 - first answer from third question
	 * @param answer32 - second answer from third question
	 * @param answer33 - third answer from third question
	 */
	public void addSurvey(String name, String question1, String answer11, String answer12, String answer13, String question2, String answer21, String answer22, String answer23, String question3, String answer31, String answer32, String answer33) {
		executeUpdate("INSERT INTO SURVEY(NAME, QUESTION1, ANSWER11, ANSWER12, ANSWER13, QUESTION2, ANSWER21, "
				+ "ANSWER22, ANSWER23, QUESTION3, ANSWER31, ANSWER32, ANSWER33) VALUES(\"" + name + "\", \"" + question1 + "\", "
				+ "\"" + answer11 + "\", \"" + answer12 + "\", \"" + answer13 + "\", \"" + question2 + "\", \"" + answer21 +"\", \"" + answer22 + "\", "
				+ "\"" + answer23 + "\", \"" + question3 + "\", \"" + answer31 + "\", \"" + answer32 + "\", \"" + answer33 + "\");");
	}
	
	/**
	 * Closes the connection to the database
	 */
	public void closeConnection() {
	    try {
	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        System.out.println("Mistake when closing the connection " + e.toString());
	        System.exit(4);
	    }
	}

	
}
