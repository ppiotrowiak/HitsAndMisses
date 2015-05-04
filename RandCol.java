
import java.awt.*;
import java.util.*;


public class RandCol extends java.applet.Applet {
	public void paint(Graphics g) {
		int i, j, count=1;
		Date dateObject = new Date();
		long startseed;
		int RandColour, PegColour1=0, PegColour2=0, PegColour3=0, PegColour4=0;
		// The peg colours are held as numbers
		// red is 0, blue is 1, green is 2, yellow is 3, black is 4
		// set up a random object based on a starting seed of time
		startseed = dateObject.getTime();
		Random rand = new Random(startseed);
		while (count < 4){
			// loop three times until the three colours for the pegs have been randomly chosen
			// get the next random number generated which is between 0-4
			RandColour= rand.nextInt(5);
			switch (count){
				case 1:
					if (PegColour1 == 0){
						PegColour1 = RandColour;
						count++;
					}
					break;
				case 2:
					if (PegColour2 == 0) {
						PegColour2 = RandColour;
						count++;
					}
					break;
				case 3:
					if (PegColour3 == 0) {
						PegColour3 = RandColour;
						count++;
					}
					break;
			}
		}
		switch(PegColour1) { // set up the colour for first peg
			case 0:
				g.setColor(Color.red);
				break;
			case 1:
				g.setColor(Color.blue);
				break;
			case 2:
				g.setColor(Color.green);
				break;
			case 3:
				g.setColor(Color.yellow);
				break;
			case 4:
				g.setColor(Color.black);
				break;
		}
		j=5; // j is the horizontal position in the applet
		i=60;// i is the vertical position in the applet
		// draw the first peg
		g.fillOval(j,i,20,20);
		g.setColor(Color.black);
		g.drawOval(j-1,i,20,20);
		switch(PegColour2) { // set up the colour for second peg
			case 0:
				g.setColor(Color.red);
				break;
			case 1:
				g.setColor(Color.blue);
				break;
			case 2:
				g.setColor(Color.green);
				break;
			case 3:
				g.setColor(Color.yellow);
				break;
			case 4:
				g.setColor(Color.black);
				break;
		}
		// draw the second peg
		g.fillOval(j+30,i,20,20);
		g.setColor(Color.black);
		g.drawOval(j+29,i,20,20);
		switch(PegColour3) { // set up the colour for the third peg
			case 0:
				g.setColor(Color.red);
				break;
			case 1:
				g.setColor(Color.blue);
				break;
			case 2:
				g.setColor(Color.green);
				break;
			case 3:
				g.setColor(Color.yellow);
				break;
			case 4:
				g.setColor(Color.black);
				break;
		}
		// draw the third peg
		g.fillOval(j+60,i,20,20);
		g.setColor(Color.black);
		g.drawOval(j+59,i,20,20);
	}
}
	