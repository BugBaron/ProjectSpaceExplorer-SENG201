package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;

public class SpaceMessagePane extends JTextPane {
	
	SpaceMessagePane() {
		super();
		super.setEditable(false);
		super.setText("Message pane");
		super.setForeground(new Color(0, 0, 128));
		super.setFont(new Font("MS Gothic", Font.PLAIN, 15));
	}
	
}
