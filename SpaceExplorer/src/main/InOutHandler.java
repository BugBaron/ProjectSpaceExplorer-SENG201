package main;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class InOutHandler {
	private Scanner scanner;
	private ArrayBlockingQueue<Object> outputList = new ArrayBlockingQueue<Object>(20);
	
	
	/**
	 * Creates a new InOutHandler with System.in as the input and
	 * with System.out as the output
	 */
	public InOutHandler() {
		scanner = new Scanner(System.in);
	}
	
	
	/**
	 * Collects an integer input for a general menu selection
	 * @param minValue the minimum value that the user can choose from
	 * @param maxValue the maximum value that the user can choose from
	 * @return an integer received from the user
	 */
	public int collectInt(int minValue, int maxValue) {
		int choice = -1;
		while (choice == -1) {
			try {
				String line = scanner.nextLine();
				choice = Integer.parseInt(line);
				
				// If the choice is not in the required range
				if (!(choice > minValue - 1 && choice < maxValue + 1)) {
					System.out.println("Please input a number between " + minValue + " and " + maxValue);
					choice = -1;
				}
			// If the choice is not an integer
			} catch (NumberFormatException e) {
				System.out.println("Please input a number to select an option");
				choice = -1;
			}
		}
		return choice;
	}
	
	
	/**
	 * Collects a string input for a general user interaction
	 * @return a string received from the user
	 */
	public String collectString() {
		return scanner.nextLine();
	}
	
	
	/**
	 * Used in testing to allow tests to be done without user input
	 * @param newScanner the scanner to replace the current scanner with
	 */
	public void setScanner(Scanner newScanner) {
		scanner = newScanner;
	}


	/**
	 * Adds the specified output to the output queue
	 * @param output the output to add
	 */
	public void print(Object output) {
		outputList.add(output);
	}
	
	
	/**
	 * Removes the first added item from the output queue and returns it.
	 * Returns null if there are no remaining items
	 * @return the first added item in the output queue
	 */
	public Object getOutput() {
		return outputList.poll();
	}
	
	
}
