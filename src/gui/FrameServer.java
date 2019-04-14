package gui;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

public class FrameServer extends JFrame{

	private static final long serialVersionUID = 1L;
	private Dimension screenSize;

	public FrameServer(PanelServer panel) throws IOException {
		super("Server");
		add(panel);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		pack();
		setSize(screenSize.width/2, 450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
}
