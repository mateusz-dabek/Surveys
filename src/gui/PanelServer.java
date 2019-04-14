package gui;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.DatabaseHandler;


public class PanelServer extends JPanel  implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField question1Prompt, question2Prompt, question3Prompt, answer11Prompt, answer12Prompt, answer13Prompt;
	private JTextField answer21Prompt, answer22Prompt, answer23Prompt, answer31Prompt, answer32Prompt, answer33Prompt;
	private JTextField question1, question2, question3, answer11, answer12, answer13, answer21, answer22, answer23;
	private JTextField answer31, answer32, answer33, namePrompt, name;
	private JButton addSurvey;
	private DatabaseHandler database;
		
	public PanelServer(DatabaseHandler database) {
		this.database  = database;
		setLayout(new GridBagLayout());
		createComponents();
	}
	
	/**
	 * Create components for server window
	 */
	public void createComponents() {
		
		GridBagConstraints c = new GridBagConstraints();
		
		namePrompt = new JTextField("Name:");
		namePrompt.setEditable(false);
		namePrompt.setBackground(Color.GRAY);
		c.weightx = 0.05;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		this.add(namePrompt, c);
		
		name= new JTextField();
		c.weightx = 0.95;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		this.add(name, c);
		
		question1Prompt = new JTextField("Question 1:");
		question1Prompt.setEditable(false);
		question1Prompt.setBackground(Color.GRAY);
		c.weightx = 0.05;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		this.add(question1Prompt, c);
		
		question1 = new JTextField();
		c.weightx = 0.95;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		this.add(question1, c);
		
		answer11Prompt = new JTextField("a)");
		answer11Prompt.setEditable(false);
		answer11Prompt.setBackground(Color.GRAY);
		c.weightx = 0.05;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		this.add(answer11Prompt, c);
		
		answer11 = new JTextField();
		c.weightx = 0.95;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		this.add(answer11, c);
		
		answer12Prompt = new JTextField("b)");
		answer12Prompt.setEditable(false);
		answer12Prompt.setBackground(Color.GRAY);
		c.weightx = 0.05;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		this.add(answer12Prompt, c);

		answer12 = new JTextField();
		c.weightx = 0.95;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 3;
		this.add(answer12, c);
		
		answer13Prompt = new JTextField("c)");
		answer13Prompt.setEditable(false);
		answer13Prompt.setBackground(Color.GRAY);
		c.weightx = 0.05;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		this.add(answer13Prompt, c);
		
		answer13 = new JTextField();
		c.weightx = 0.95;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 4;
		this.add(answer13, c);
		
		question2Prompt = new JTextField("Question 2:");
		question2Prompt.setEditable(false);
		question2Prompt.setBackground(Color.GRAY);
		c.weightx = 0.05;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		this.add(question2Prompt, c);
		
		question2 = new JTextField();
		c.weightx = 0.95;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		this.add(question2, c);
		
		answer21Prompt = new JTextField("a)");
		answer21Prompt.setEditable(false);
		answer21Prompt.setBackground(Color.GRAY);
		c.weightx = 0.05;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 6;
		this.add(answer21Prompt, c);
		
		answer21 = new JTextField();
		c.weightx = 0.95;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		this.add(answer21, c);
		
		answer22Prompt = new JTextField("b)");
		answer22Prompt.setEditable(false);
		answer22Prompt.setBackground(Color.GRAY);
		c.weightx = 0.05;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 7;
		this.add(answer22Prompt, c);
		
		answer22 = new JTextField();
		c.weightx = 0.95;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 7;
		this.add(answer22, c);
		
		answer23Prompt = new JTextField("c)");
		answer23Prompt.setEditable(false);
		answer23Prompt.setBackground(Color.GRAY);
		c.weightx = 0.05;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 8;
		this.add(answer23Prompt, c);
		
		answer23 = new JTextField();
		c.weightx = 0.95;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 8;
		this.add(answer23, c);
		
		question3Prompt = new JTextField("Question 3:");
		question3Prompt.setEditable(false);
		question3Prompt.setBackground(Color.GRAY);
		c.weightx = 0.05;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 9;
		this.add(question3Prompt, c);
		
		question3 = new JTextField();
		c.weightx = 0.95;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 9;
		this.add(question3, c);
		
		answer31Prompt = new JTextField("a)");
		answer31Prompt.setEditable(false);
		answer31Prompt.setBackground(Color.GRAY);
		c.weightx = 0.2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 10;
		this.add(answer31Prompt, c);
		
		answer31 = new JTextField();
		c.weightx = 0.95;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 10;
		this.add(answer31, c);
		
		answer32Prompt = new JTextField("b)");
		answer32Prompt.setEditable(false);
		answer32Prompt.setBackground(Color.GRAY);
		c.weightx = 0.05;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 11;
		this.add(answer32Prompt, c);
		
		answer32 = new JTextField();
		c.weightx = 0.95;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 11;
		this.add(answer32, c);
		
		answer33Prompt = new JTextField("c)");
		answer33Prompt.setEditable(false);
		answer33Prompt.setBackground(Color.GRAY);
		c.weightx = 0.05;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 12;
		this.add(answer33Prompt, c);
	
		answer33 = new JTextField();
		c.weightx = 0.95;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 12;
		this.add(answer33, c);
		
		addSurvey = new JButton("Add survey");
		addSurvey.setBackground(Color.CYAN);
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(10, 0, 0, 100);
		c.gridx = 1;
		c.gridy = 13;
		this.add(addSurvey, c);
		addSurvey.addActionListener(this);
		
	}

	/**
	 * The method is run when, for example, something was clicked on which action listener was added to
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		if(source == addSurvey) {
			database.addSurvey(name.getText(), question1.getText(), answer11.getText(), answer12.getText(), answer13.getText(), question2.getText(),
			answer21.getText(), answer22.getText(), answer23.getText(), question3.getText(), answer31.getText(), answer32.getText(), answer33.getText());
			database.insertIntoResult();
			name.setText("");
			question1.setText("");
			answer11.setText("");
			answer12.setText("");
			answer13.setText("");
			question2.setText("");
			answer21.setText("");
			answer22.setText("");
			answer23.setText("");
			question3.setText("");
			answer31.setText("");
			answer32.setText("");
			answer33.setText("");
			
		}
	}

}
