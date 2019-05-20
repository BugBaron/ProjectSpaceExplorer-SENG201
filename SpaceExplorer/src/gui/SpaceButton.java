package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class SpaceButton extends JButton {
	
	SpaceButton(String name) {
		super(name);
		super.setFocusable(false);
		super.setBorder(new CompoundBorder(new LineBorder(new Color(50, 205, 50)), new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(new Color(50, 205, 50)))));
		super.setForeground(Color.WHITE);
		super.setBackground(new Color(25, 25, 112));
		super.setFont(new Font("MS Gothic", Font.PLAIN, 20));
	}
	
}
