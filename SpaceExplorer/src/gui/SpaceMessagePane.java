package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class SpaceMessagePane {
	
	private JTextPane messagePane;
	private JScrollPane scrollPane;
	
	/**
	 * Gets the scroll pane of this class
	 * @return
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	
	
	/**
	 * Sets the bounds of this message pane
	 * @param x the desired x position of the pane
	 * @param y	the desired y position of the pane
	 * @param width the desired width of the pane
	 * @param height the desired height of the pane
	 */
	public void setBounds(int x, int y, int width, int height) {
		scrollPane.setBounds(x, y, width, height);
	}
	
	
	/**
	 * Sets the text of the message pane
	 * @param text the text to set this message pnaes text to
	 */
	public void setText(String text) {
		messagePane.setText(text);
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
		verticalScrollBar.setValue(verticalScrollBar.getMaximum());
	}
	
	
	/**
	 * Creates a new message pane with specific styling
	 */
	SpaceMessagePane() {
		messagePane = new JTextPane();
		messagePane.setEditable(false);
		messagePane.setText("Message pane");
		messagePane.setForeground(new Color(0, 0, 128));
		messagePane.setFont(new Font("MS Gothic", Font.PLAIN, 15));
		
		scrollPane = new JScrollPane(messagePane);
	}
	
}
