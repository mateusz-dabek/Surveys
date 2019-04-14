package gui;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Frame with settings for the connection window
 */
public class FrameConnection extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public FrameConnection(PanelConnection panel) throws IOException {
		super("New database connection");
		add(panel);
		pack();
		setSize(300, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
}
