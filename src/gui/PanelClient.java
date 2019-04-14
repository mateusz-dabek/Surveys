package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class PanelClient extends JPanel  implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JTextField questionField;
	private Font bigFont;
	private JRadioButton button1, button2, button3;
	private JButton next;
	private GridLayout gridLayout;
    private PrintWriter printWriter = null;
    private String question, answer1, answer2, answer3;
    private Boolean flag = false;
	
	public PanelClient(Socket socket, String q1, String a1, String a2, String a3) {
		
		this.question = q1;
		this.answer1 = a1;
		this.answer2 = a2;
		this.answer3 = a3;
		
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		gridLayout = new GridLayout(5, 1);
		setLayout(gridLayout);
		
		try {
			createComponents();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Create components for window client
	 */
	public void createComponents() throws IOException{
		
		questionField = new JTextField(question);
		questionField.setBackground(Color.GRAY);
		bigFont = questionField.getFont().deriveFont(Font.PLAIN, 20f);
		questionField.setEditable(false);
		questionField.setFont(bigFont);
		this.add(questionField);
			
			 button1 = new JRadioButton(answer1, true);
			 button2 = new JRadioButton(answer2, false);
			 button3 = new JRadioButton(answer3, false);

			 ButtonGroup group = new ButtonGroup();
			 group.add(button1);
			 group.add(button2);
			 group.add(button3);
			 
			 this.add(button1);
			 this.add(button2);
			 this.add(button3);
			 button1.addActionListener(this);
			 button2.addActionListener(this);
			 button3.addActionListener(this);
			 
		next = new JButton("NEXT");
		next.setBackground(Color.CYAN);
		this.add(next);
		next.addActionListener(this);
			 
	}

	/**
	 * The method is run when, for example, something was clicked on which action listener was added to
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		
		Object source = event.getSource();
		
		if(source == next) {
			
			if(button1.isSelected()) {
              printWriter.println("a");
              printWriter.flush();
			}
			if(button2.isSelected()) {
              printWriter.println("b");
              printWriter.flush();
			}
			if(button3.isSelected()){
              printWriter.println("c");
              printWriter.flush();
			}
			flag = true;
			
		}
		
	}
	
	public Boolean getFlag() {
		return flag;
	}
	
}
