package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class GUIInstructions {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIInstructions window = new GUIInstructions();
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
	public GUIInstructions() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(640, 480));
		frame.getContentPane().setMinimumSize(new Dimension(640, 480));
		frame.getContentPane().setBackground(new Color(0, 0, 128));
		frame.getContentPane().setLayout(null);
		
		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setBounds(0, 11, 626, 41);
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblInstructions.setForeground(new Color(255, 255, 0));
		frame.getContentPane().add(lblInstructions);
		
		JTextPane txtpnInsertTextHere = new JTextPane();
		txtpnInsertTextHere.setForeground(new Color(255, 255, 255));
		txtpnInsertTextHere.setBackground(new Color(0, 0, 128));
		txtpnInsertTextHere.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		String InstructionsText = "You are lost in out of space and your space ship is falling apart. \n"
				+ "In order for your mission to succeed you need to find the missing parts of your space ship \n"
				+ "while maintaining the safety of your crew.";
		txtpnInsertTextHere.setText(InstructionsText);
		txtpnInsertTextHere.setBounds(68, 105, 492, 170);
		frame.getContentPane().add(txtpnInsertTextHere);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnContinue.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0)), new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(0, 255, 0)))));
		btnContinue.setForeground(new Color(255, 255, 255));
		btnContinue.setBounds(201, 332, 237, 49);
		frame.getContentPane().add(btnContinue);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
