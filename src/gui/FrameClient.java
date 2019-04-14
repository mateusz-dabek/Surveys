package gui;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * Frame with settings for the client window
 */
public class FrameClient extends JFrame{

	private static final long serialVersionUID = 1L;
	private Dimension screenSize;

	public FrameClient(PanelClient panel) throws IOException {
		super("Client");
		add(panel);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		pack();
		setSize(screenSize.width/2, 350);
		setLocation(screenSize.width/2, 0);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
}