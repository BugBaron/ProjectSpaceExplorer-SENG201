package gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;

import gui.spaceWidgets.SpaceButton;
import gui.spaceWidgets.SpaceTitle;
import main.GameEnvironment;
import main.InOutHandler;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * A screen to display when the game has ended.
 * @author Daniel Harris and Rebekah McKinnon
 *
 */
public class EndGameScreen extends JPanel {

	/** The window holding this panel. */
	private GUIWindow guiWindow;
	/** The game environment that the game is running in. */
	private GameEnvironment gameEnvironment;
	/** The object which is handling the input and output of the game environment. */
	private InOutHandler inOut;
	
	/** A text pane to display a message after the game has ended. */
	private JTextPane txtpnResultMessage;
	/** A title for the screen. */
	private SpaceTitle lblTitle;
	
	
	/**
	 * Creates the panel.
	 * @param tempWindow the window to create this panel for
	 */
	public EndGameScreen(GUIWindow tempWindow) {
		super();
		setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		guiWindow = tempWindow;
		gameEnvironment = tempWindow.gameEnvironment;
		inOut = gameEnvironment.getInOut();
		initialize();
	}
	
	
	/**
	 * Runs the processes necessary to show the End Day Screen.
	 * @param isVictory whether or not the game ended as a victory
	 */
	public void runEndDay(boolean isVictory) {
		gameEnvironment.endGame(isVictory);
		String finalOutput = "";
		Object output = inOut.getOutput();
		finalOutput = finalOutput + output;
		lblTitle.setText(finalOutput);
		output = inOut.getOutput();
		finalOutput = (String) output;
		output = inOut.getOutput();
		while (output != null) {
			finalOutput = finalOutput + "\n" + output;
			output = inOut.getOutput();
		}
		txtpnResultMessage.setText(finalOutput);
	}

	
	/**
	 * Initialize the panel contents.
	 */
	private void initialize() {
		
		lblTitle = new SpaceTitle("");
		lblTitle.setBounds(0, 27, 626, 63);
		add(lblTitle);
		
		txtpnResultMessage = new JTextPane();
		txtpnResultMessage.setEditable(false);
		txtpnResultMessage.setBackground(new Color(25, 25, 112));
		txtpnResultMessage.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnResultMessage.setForeground(Color.WHITE);
		txtpnResultMessage.setBounds(50, 115, 521, 193);
		add(txtpnResultMessage);
		
		SpaceButton btnNewGame = new SpaceButton("New Game");
		btnNewGame.setBounds(147, 319, 147, 43);
		add(btnNewGame);
		
		SpaceButton btnExit = new SpaceButton("Exit");
		btnExit.setBounds(345, 319, 147, 42);
		add(btnExit);
		
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.messagePaneContents.removeAll(guiWindow.messagePaneContents);
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Set Up Screen");
				guiWindow.updatePane();
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
}
