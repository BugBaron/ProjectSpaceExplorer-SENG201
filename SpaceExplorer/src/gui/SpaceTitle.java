package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SpaceTitle extends JLabel {
	
	/**
	 * Creates a new JLabel with specific styling
	 * @param text the text to display on the title
	 */
	SpaceTitle(String text) {
		super(text);
		super.setHorizontalAlignment(SwingConstants.CENTER);
		super.setForeground(Color.YELLOW);
		super.setFont(new Font("MS Gothic", Font.PLAIN, 40));
	}
	
}
