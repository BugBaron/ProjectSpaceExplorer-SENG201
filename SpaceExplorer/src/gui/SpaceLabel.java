package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class SpaceLabel extends JLabel {
	
	/**
	 * Creates a new JLabel with specific styling
	 * @param text the text to display on the label
	 */
	SpaceLabel(String text) {
		super(text);
		super.setForeground(Color.WHITE);
		super.setFont(new Font("MS Gothic", Font.PLAIN, 20));
	}
}
