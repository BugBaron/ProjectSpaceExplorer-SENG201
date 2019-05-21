package gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIInstructions extends JPanel{

	private GUIWindow guiWindow;
	
	/**
	 * Create the application.
	 */
	public GUIInstructions() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		SpaceTitle lblInstructions = new SpaceTitle("Instructions");
		lblInstructions.setBounds(0, 11, 626, 41);
		super.add(lblInstructions);
		
		JTextPane txtpnInstructions = new JTextPane();
		txtpnInstructions.setForeground(new Color(255, 255, 255));
		txtpnInstructions.setBackground(new Color(0, 0, 128));
		txtpnInstructions.setFont(new Font("MS Gothic", Font.PLAIN, 20));
		String InstructionsText = "You are lost in out of space and your space ship is falling apart.\n"
				+ "In order for your mission to succeed you need to find the missing parts of your space ship "
				+ "while maintaining the safety of your crew.\n\n"
				+ "To find your missing space ship parts you must search a planet. There is a maximum of "
				+ "of 1 space ship part on each planet. If you do not find a space ship part you may find "
				+ "food, medical supplies, money or nothing. \n\n"
				+ "Food and medical supplies are used to boost a crew members health or nutrition. They can be "
				+ "purchased from the outpost or found on a planet. Crew members energy can be boosted by sleeping.\n\n"
				+ "Each day, every crew member has 2 actions. These actions can be used to sleep, search the planet, "
				+ "repair the space ship, pilot the space ship, eat food or use medical supplies. Note that piloting "
				+ "the space ship requires 2 crew members with at least one action remaining. \n "
				+ "Crew members lose 1 energy and 1 nutrition per action.\n\n"
				+ "A new day can be triggered at any time, regardless of how many actions you have remaining. Each day "
				+ "crew members lose 2 health. If a crew member has no energy, it will lose 1 additional health everyday. "
				+ "If a crew member has no nutrition, they will lose an additional 1 health and 1 energy daily. "
				+ "When a crew member runs out of health, they die and are removed from the spaceship.\n"
				+ "You also gain $20 at the end of every day.\n\n"
				+ "At the completion of every day there is a 40% chance a crew member may contract the space plague "
				+ "or your ship may be attacked by alien pirates. If the ship is attacked by pirates you will "
				+ "lose one random item from your inventory. When a crew member contracts the space plague, they "
				+ "can be cured only by the 'Space Plague Cure' which must be found or purchased. Crew members that "
				+ "have the space plague lose 1 additional health each day.\n\n"
				+ "The game ends when you are successful in finding all the parts, you run out of days or all of the "
				+ "crew members die.";
		txtpnInstructions.setText(InstructionsText);
		txtpnInstructions.setBounds(68, 105, 492, 170);
		super.add(txtpnInstructions);
		
		SpaceButton btnContinue = new SpaceButton("Continue");
		btnContinue.setBounds(201, 332, 237, 49);
		super.add(btnContinue);
		
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guiWindow.layout.show(guiWindow.frame.getContentPane(), "Set Up");
				guiWindow.updatePane();
			}
		});
	}

}
