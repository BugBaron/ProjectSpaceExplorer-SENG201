package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class GUIEndGameScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIEndGameScreen window = new GUIEndGameScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIEndGameScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 128));
		frame.getContentPane().setLayout(null);
		
		JLabel lblSuccess = new JLabel("Congratulations!");
		lblSuccess.setBounds(0, 27, 626, 63);
		lblSuccess.setVerticalAlignment(SwingConstants.TOP);
		lblSuccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccess.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblSuccess.setForeground(Color.YELLOW);
		frame.getContentPane().add(lblSuccess);
		
		JTextPane txtpnResultMessage = new JTextPane();
		txtpnResultMessage.setBackground(new Color(0, 0, 128));
		txtpnResultMessage.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		txtpnResultMessage.setForeground(Color.WHITE);
		txtpnResultMessage.setText("You found all the space ship parts and completed the mission! Well Done!");
		txtpnResultMessage.setBounds(50, 115, 521, 193);
		frame.getContentPane().add(txtpnResultMessage);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnNewGame.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0)), new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(0, 255, 0)))));
		btnNewGame.setForeground(Color.WHITE);
		btnNewGame.setBounds(147, 319, 147, 43);
		frame.getContentPane().add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnExit.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0)), new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(0, 255, 0)))));
		btnExit.setForeground(Color.WHITE);
		btnExit.setBounds(345, 319, 147, 42);
		frame.getContentPane().add(btnExit);
		frame.setBackground(new Color(0, 0, 128));
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
