package gui.spaceWidgets;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * A label with specific styling to make it a title
 * @author Daniel Harris and Rebekah McKinnon
 */
public class SpaceTitle extends JLabel {
	
	/**
	 * Creates a new JLabel with specific styling
	 * @param text the text to display on the title
	 */
	public SpaceTitle(String text) {
		super(text);
		setHorizontalAlignment(SwingConstants.CENTER);
		setForeground(Color.YELLOW);
		setFont(new Font("MS Gothic", Font.PLAIN, 40));
	}
	
}
