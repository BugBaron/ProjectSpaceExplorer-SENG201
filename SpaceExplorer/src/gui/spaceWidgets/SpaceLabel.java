package gui.spaceWidgets;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * A label with specific styling.
 * @author Daniel Harris and Rebekah McKinnon
 */
public class SpaceLabel extends JLabel {
	
	/**
	 * Creates a new JLabel with specific styling.
	 * @param text the text to display on the label
	 */
	public SpaceLabel(String text) {
		super(text);
		setForeground(Color.WHITE);
		setFont(new Font("MS Gothic", Font.PLAIN, 20));
	}
}
