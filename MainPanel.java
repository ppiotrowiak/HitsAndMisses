/**
 * My attempt on Assignment B
 * @author Przemyslaw Piotrowiak
 */
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;


public class MainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// --- Instance variables ---
	JButton redButton = new JButton("Red");
	JButton blueButton = new JButton("Blue");
	JButton greenButton = new JButton("Green");
	JButton yellowButton = new JButton("Yellow");
	JButton blackButton = new JButton("Black");
	JLabel guessCode;
	JLabel guessesRemaining;
	
	// 
	// create variable and set default number of tries
	int tries = 8;
	// create variable to hold try number
	int guessNo = 1;
	// create variable to indicate end of game
	boolean newGame = false;
		
	ArrayList<Color> circles = new ArrayList<Color>(0);
	
	ArrayList<Integer> correctColors = new ArrayList<Integer>(0);
	ArrayList<Integer> correctPositions = new ArrayList<Integer>(0);
	
	Color[] correctAnswer = new Color[4];
	
	// --- Constructor(s) ---
	public MainPanel() {
		
		setSize(500,400);
		
		// setting layout of the main panel
		BorderLayout bordLO = new BorderLayout();
		setLayout(bordLO);
		
		// setting a grid layout panel in the CENTER part of BorderLayout
		JPanel centre = new JPanel();
		centre.setLayout(new GridLayout(1,2));
		
		// setting the left panel for buttons and a label
		JPanel left = new JPanel();
		left.setLayout(new GridLayout(3,1));
		
		// creating a panel for the buttons, adding buttons to the panel and adding panel to the left panel
		JPanel buttons = new JPanel();
		buttons.add(redButton);
		buttons.add(blueButton);
		buttons.add(greenButton);
		buttons.add(yellowButton);
		buttons.add(blackButton);
		
		// Creating the label for guesses remaining information
		guessesRemaining = new JLabel("          Guesses remaining: " + (tries - guessNo +1));
		
		// creating a panel for the guess code label
		JPanel leftBottom = new JPanel();
				
		guessCode = new JLabel("          Guess the hidden code");
		//guessCode.setVerticalAlignment(SwingConstants.BOTTOM);
		leftBottom.add(guessCode);
		add(guessesRemaining);
		
		left.add(buttons);
		left.add(guessCode);
		left.add(guessesRemaining);
		centre.add(left);
		
		// setting a panel for the graphics on the right hand side of the centre panel
		GamePanel gamePanel = new GamePanel();
		centre.add(gamePanel);
		
		add(centre,BorderLayout.CENTER);
		
		// adding empty panels to help alignement
		JPanel west = new JPanel();
		JPanel east = new JPanel();
		JPanel north = new JPanel();
		add(west,BorderLayout.WEST);
		add(east,BorderLayout.EAST);
		add(north,BorderLayout.NORTH);
		
		// adding hits and missess text at the bottom of the applet
		JLabel hitsandmisses = new JLabel("HITS AND MISSES");
		hitsandmisses.setForeground(Color.BLUE);
		hitsandmisses.setFont(new Font("Monospaced", Font.PLAIN, 20));
		hitsandmisses.setHorizontalAlignment(SwingConstants.CENTER);
		add(hitsandmisses, BorderLayout.SOUTH);
		
		// add "Click a colour" message at the top left
		JLabel clickacolour = new JLabel("                        Click a colour");
		clickacolour.setHorizontalAlignment(SwingConstants.LEADING);
		add(clickacolour, BorderLayout.NORTH);
		
		// creates instance of the class that handles Action events
		ListenForButton lForButton = new ListenForButton();
		
		// creates instance of the class that handles Key events
		ListenForKeys lForKeys = new ListenForKeys();
		
		// generate random colors
		generateRandomColors();
		

								
	}
	
	// --- 	Methods ---
	// write any public and private methods here
	
	//Generating random color table
	public void generateRandomColors() {
		Date dateObject = new Date();
		long startseed;
		int randColor;
		startseed = dateObject.getTime();
		Random rand = new Random(startseed);
		for (int i = 0; i < 3; i++) {
			randColor= rand.nextInt(5);
			switch (randColor) {
			case 0: correctAnswer[i] = Color.red;
			break;
			case 1: correctAnswer[i] = Color.green;
			break;
			case 2: correctAnswer[i] = Color.blue;
			break;
			case 3: correctAnswer[i] = Color.black;
			break;
			case 4: correctAnswer[i] = Color.yellow;
			
			}
		}
		
		correctAnswer[3] = null;
		
	}
	
	
	// Add a coloured filled circle after choosing a color to the arraylist
	public void addCircle(Color kolor) {
		circles.add(kolor);
		
	}
	
	// Checking number of correct colours on correct positions
	private int checkGuess() {
		int i = 0;
		if (circles.get(circles.size() - 3) == correctAnswer[0]) {
			i++;
		}
		if (circles.get(circles.size() - 2) == correctAnswer[1]) {
			i++;
		}
		if (circles.get(circles.size() - 1) == correctAnswer[2]) {
			i++;
		}
		correctPositions.add(i);
		System.out.println("Number of correct colours on correct positions: " + i);
		System.out.println(correctPositions.toString());
		return i;
	}
	
	// Checking number of correct colours regardless of position
	private int checkColors() {
		int i = 0;
		// make a copy of the array so it is more handy to compare
		int left[] = new int[5];
		int right[] = new int[5];
		// populate the arrays 0-red, 1-blue, 2-green, 3-yellow, 4-black
		for (int j = 0; j < 3; j++) {
			if (correctAnswer[j] == Color.RED) { left[0] += 1;}
			else if (correctAnswer[j] == Color.BLUE) {left[1] += 1;}
			else if (correctAnswer[j] == Color.GREEN) {left[2] += 1;}
			else if (correctAnswer[j] == Color.YELLOW) {left[3] += 1;}
			else if (correctAnswer[j] == Color.BLACK) {left[4] += 1;}
 		}
		for (int j = 3; j > 0; j--) {
			if (circles.get(circles.size() - j) == Color.RED) { right[0] += 1;}
			else if (circles.get(circles.size() - j) == Color.BLUE) { right[1] += 1;}
			else if (circles.get(circles.size() - j) == Color.GREEN) { right[2] += 1;}
			else if (circles.get(circles.size() - j) == Color.YELLOW) { right[3] += 1;}
			else if (circles.get(circles.size() - j) == Color.BLACK) { right[4] += 1;}
			
		}
		
		for (int k = 0; k < left.length; k++) {
			if ((left[k] > 0) && (right[k] > 0)) { 
				if (left[k] == right[k]) {
					i += left[k];
				}
				else if (left[k] < right[k]) {
					i += left[k];
				}
				else  {
					i += right[k];
				}
					
			}
			
		}
		System.out.println("Trafionych kolorow " + i);
		correctColors.add(i);
		System.out.println(correctColors.toString());
		return i;
	}
	// What happens if player wins
	private void victory() {
		guessCode.setText(("                         You win"));
		revealAnswer();
		guessNo--;
		newGame = true;
		
	}
	
	
	// What happens if player lose
	private void defeat() {
		guessCode.setText(("                         You lose"));
		guessesRemaining.setText("          Guesses remaining: 0");
		revealAnswer();
		guessNo--; // so the next line of circles is not drawn
		newGame = true; //
	}
	
	// Reveal the answer and start start new game
	private void revealAnswer() {
		// add any Color element to the array - this will trigger showing the correct answer
		correctAnswer[3] = Color.black;
	}
	
	// --- Private inner classes to handle events ---
	
	// Implements ActionListener so it can react to events on components - Button Clicks
	/*
	 * ActionListener: The listener interface for receiving action events. The class that is interested 
	 * in processing an action event implements this interface, and the object created with that class 
	 * is registered with a component, using the component's addActionListener method. When the action 
	 * event occurs, that object's actionPerformed method is invoked.
	 */
	// Implements ActionListener so it can react to events on components - Button Clicks
	private class ListenForButton implements ActionListener {
		
		ListenForButton() {
		// add action listeners to buttons
		redButton.addActionListener(this);
		blueButton.addActionListener(this);
		greenButton.addActionListener(this);
		yellowButton.addActionListener(this);
		blackButton.addActionListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// Invoked when an action occurs.
			System.out.println("performing action - button clicked");
			// if new game reset values to default and draw new set of colors
			if (newGame) {
				// turn of the flag
				newGame = false;
				// clear circles array
				circles.clear();
				// set guess No. to default
				guessNo = 1;
				// set labels to default
				guessCode.setText("          Guess the hidden code");
				guessesRemaining.setText("          Guesses remaining: " + (tries - guessNo +1));
				// draw new set of colors
				generateRandomColors();
				//clear guessed arrays
				correctColors.clear();
				correctPositions.clear();
			}
			
			String command = e.getActionCommand(); 
			System.out.println(command);
			switch (command) {
				case "Red":circles.add(Color.RED);
				break;
				case "Blue":circles.add(Color.blue);
				break;
				case "Green":circles.add(Color.GREEN);
				break;
				case "Yellow":circles.add(Color.yellow);
				break;
				case "Black":circles.add(Color.black);
				break;
	        }
			
			//checking if correct answer was selected after choosing third color in one row
			if ((circles.size() >= 0) && (circles.size() % 3 == 0)) {
				System.out.println("Checking answer");
				// update label showing number of remaining guesses
				guessesRemaining.setText(("          Guesses remaining: " + (tries - guessNo)));
				if (checkGuess() == 3) {
				// right guess
				victory();
				} else {
					if (guessNo == tries) {
						defeat();
					}
				}
				checkColors();
				guessNo++;
				// update label showing number of remaining guesses
				//guessesRemaining.setText(("          Guesses remaining: " + (tries - guessNo + 1)));
			}
			
			repaint();
		}
		
		
	}
	
	/*
	 * KeyListener: The listener interface for receiving keyboard events (keystrokes). 
	 * The class that is interested in processing a keyboard event implements this interface and writes 
	 * all the methods it contains.  Method bodies can be left blank if not needed.  
	 */
	private class ListenForKeys implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// Invoked when a key has been pressed.
		
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// Invoked when a key has been released.
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// Invoked when a key has been typed.
			System.out.println("Key pressed: " + e.getKeyChar() );
		}
	}
	
	private class GamePanel extends JPanel {
		
		GamePanel() {
			setSize(100,300);
					
		}
		
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			
			g2.setBackground(Color.white);
			g2.setColor(Color.black);
			
					
			// generating desciptor showing number of correctly guessed circles and positions
			g2.drawString("Colors", 120,10);
			g2.drawString("Positions", 160, 10);
			int y = -5;
			
			for (int i = 0; i < correctColors.size(); i++) {
				y += 30;
				g2.drawString(Integer.toString(correctColors.get(i)), 140, y);
  				g2.drawString(Integer.toString(correctPositions.get(i)),190, y);
  				
			}
			
			// starting y position for drawed circles
			y = -25;
			
			// generating circles showing guess progress
			// new set of circles drawed each time after action performed
			for (int i = 0; i < circles.size(); i++) {
				if ((i % 3) == 0) {
					y += 30;
				}
				if ((i % 3) == 0) {
					g2.setColor(circles.get(i));
					g2.fillOval(30, y, 20, 20);
				} 
				else if ((i % 3) == 1) { 
					g2.setColor(circles.get(i));
					g2.fillOval(60, y, 20, 20);
				}
				else {
					g2.setColor(circles.get(i));
					g2.fillOval(90, y, 20, 20);
				}
				
			}
			
			y = 5;
			g2.setColor(Color.BLACK);
			// generating empty black circles for guesses
			for (int i = 1; i <= guessNo; i++) {
			
				g2.drawOval(30, y, 20, 20);
				g2.drawOval(60, y, 20, 20);
				g2.drawOval(90, y, 20, 20);
				y += 30;
			}
			
			
		
			
			// draw circles at the bottom to show default or correct answer only if fourth element present
			if (correctAnswer[3] != null) {
				g2.setColor(correctAnswer[0]);
				g2.fillOval(30, 290, 20, 20);
				g2.setColor(correctAnswer[1]);
				g2.fillOval(60, 290, 20, 20);
				g2.setColor(correctAnswer[2]);
				g2.fillOval(90, 290, 20, 20);
				
			}
			else {
				g2.setColor(Color.WHITE);
				g2.fillOval(30, 290, 20, 20);
				g2.fillOval(60, 290, 20, 20);
				g2.fillOval(90, 290, 20, 20);
			}
			
					
			// draw default circles at the bottom of the screen
			g2.setColor(Color.BLACK);
			g2.drawOval(30, 290, 20, 20);
			g2.drawOval(60, 290, 20, 20);
			g2.drawOval(90, 290, 20, 20);
			
		}
	}
}