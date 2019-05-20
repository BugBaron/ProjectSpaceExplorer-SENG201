package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class SpaceLabel extends JLabel {
	
	SpaceLabel(String name) {
		super(name);
		super.setForeground(Color.WHITE);
		super.setFont(new Font("MS Gothic", Font.PLAIN, 20));
	}
}
