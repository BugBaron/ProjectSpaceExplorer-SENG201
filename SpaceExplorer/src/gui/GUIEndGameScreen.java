package gui;

import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import main.GameEnvironment;
import main.InOutHandler;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIEndGameScreen extends JPanel {

	private NewGUIWindow guiWindow;
	private GameEnvironment gameEnvironment;
	private InOutHandler inOut;
	private JTextPane txtpnResultMessage;
	private SpaceTitle lblTitle;
	
	/**
	 * Create the panel
	 */
	public GUIEndGameScreen(NewGUIWindow guiWindow) {
		super();
		super.setBackground(new Color(25, 25, 112));
		setLayout(null);
		
		this.guiWindow = guiWindow;
		gameEnvironment = guiWindow.gameEnvironment;
		initialize();
	}
	
	public void runEndDay(boolean isVictory) {
		gameEnvironment.endGame(isVictory);
		String finalOutput = "";
		Object output = inOut.getOutput();
		finalOutput = finalOutput + output;
		lblTitle.setText(finalOutput);
		finalOutput = "";
		while (output != null) {
			finalOutput = finalOutput + output;
			output = inOut.getOutput();
		}
		txtpnResultMessage.setText(finalOutput);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		SpaceTitle lblTitle = new SpaceTitle("");
		lblTitle.setBounds(0, 27, 626, 63);
		super.add(lblTitle);
		
		JTextPane txtpnResultMessage = new JTextPane();
		txtpnResultMessage.setBackground(new Color(25, 25, 112));
		txtpnResultMessage.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnResultMessage.setForeground(Color.WHITE);
		txtpnResultMessage.setBounds(50, 115, 521, 193);
		super.add(txtpnResultMessage);
		
		SpaceButton btnNewGame = new SpaceButton("New Game");
		btnNewGame.setBounds(147, 319, 147, 43);
		super.add(btnNewGame);
		
		SpaceButton btnExit = new SpaceButton("Exit");
		btnExit.setBounds(345, 319, 147, 42);
		super.add(btnExit);
		
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Set Up");
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
