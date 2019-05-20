package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIOpeningScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIOpeningScreen window = new GUIOpeningScreen();
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
	public GUIOpeningScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setMaximumSize(new Dimension(640, 480));
		frame.setMaximumSize(new Dimension(640, 480));
		frame.setMinimumSize(new Dimension(640, 480));
		frame.setTitle("Space Explorer Game");
		frame.getContentPane().setMinimumSize(new Dimension(640, 480));
		frame.getContentPane().setBackground(new Color(25, 25, 112));
		frame.getContentPane().setForeground(new Color(0, 0, 128));
		
		JButton btnCreateGame = new JButton("Create New Game");
		btnCreateGame.setBounds(173, 305, 304, 70);
		btnCreateGame.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnCreateGame.setBackground(new Color(0, 0, 128));
		btnCreateGame.setForeground(Color.WHITE);
		btnCreateGame.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnCreateGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnCreateGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		btnExit.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		btnExit.setBackground(new Color(0, 0, 128));
		btnExit.setBounds(173, 386, 304, 70);
		frame.getContentPane().add(btnExit);
		
		JLabel lblTitle = new JLabel("SPACE EXPLORER:");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("MS Gothic", Font.PLAIN, 58));
		lblTitle.setForeground(Color.YELLOW);
		lblTitle.setBounds(31, 11, 596, 196);
		frame.getContentPane().add(lblTitle);
		
		JLabel lblSubTitle = new JLabel("THE GAME");
		lblSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubTitle.setFont(new Font("MS Gothic", Font.PLAIN, 40));
		lblSubTitle.setForeground(Color.YELLOW);
		lblSubTitle.setBounds(210, 146, 216, 89);
		frame.getContentPane().add(lblSubTitle);
		
	}
}
