/**
 * 
 * City & Guilds 
 * Assignment B
 * @author Przemyslaw Piotrowiak
 */

import javax.swing.JApplet;

public class AppletShell extends JApplet {
	private static final long serialVersionUID = 1L;
	
	// init method runs when the applet is loaded for the first time	
	public void init() {
		setSize(500,400);
		setContentPane(new MainPanel());
	}

		
}