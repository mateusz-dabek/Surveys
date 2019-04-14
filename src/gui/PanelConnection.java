package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PanelConnection extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JTextField userPrompt, passwordPrompt, hostnamePrompt, portPrompt;
	private JTextField user, password, hostname, port;
	private JButton connection;
	private String sUser, sPassword, sHostname, sPort;
	private Boolean flag = false;
	
	public PanelConnection() {
		GridLayout gridLayout = new GridLayout(5,2);
		setLayout(gridLayout);	
		createComponents();
	}
	
	/**
	 * Create components for connection window
	 */
	public void createComponents() {
		
		userPrompt = new JTextField("User:");
		userPrompt.setEditable(false);
		userPrompt.setBackground(Color.GRAY);
		this.add(userPrompt);
		
		user = new JTextField();
		this.add(user);
		
		passwordPrompt = new JTextField("Password:");
		passwordPrompt.setEditable(false);
		passwordPrompt.setBackground(Color.GRAY);
		this.add(passwordPrompt);
		
		password = new JTextField();
		this.add(password);
		
		hostnamePrompt = new JTextField("Hostname:");
		hostnamePrompt.setEditable(false);
		hostnamePrompt.setBackground(Color.GRAY);
		this.add(hostnamePrompt);
		
		hostname = new JTextField();
		this.add(hostname);
		
		portPrompt = new JTextField("Port:");
		portPrompt.setEditable(false);
		portPrompt.setBackground(Color.GRAY);
		this.add(portPrompt);
		
		port = new JTextField();
		this.add(port);
		
		connection = new JButton("CONNECT");
		connection.setBackground(Color.CYAN);
		this.add(new JPanel());
		this.add(connection);
		connection.addActionListener(this);
	}

	/**
	 * The method is run when, for example, something was clicked on which action listener was added to
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		if(source == connection) {
			sUser = user.getText();
			sPassword = password.getText();
			sHostname = hostname.getText();
			sPort = port.getText();
			flag = true;
		}

	}
	
	public String getUser() { return sUser; }
	public String getPassword() { return sPassword; }
	public String getHostname() { return sHostname; }
	public String getPort() { return sPort; }
	public Boolean getFlag() { return flag; }
	
}
