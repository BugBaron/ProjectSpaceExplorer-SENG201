package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class SpaceTitle extends JLabel {
	
	SpaceTitle(String name) {
		super(name);
		super.setHorizontalAlignment(SwingConstants.CENTER);
		super.setForeground(Color.YELLOW);
		super.setFont(new Font("MS Gothic", Font.PLAIN, 40));
	}
	
}
