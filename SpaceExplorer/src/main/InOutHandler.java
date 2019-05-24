package main;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * Handles all input and output sent to it.
 * @author Daniel Harris and Rebekah McKinnon
 */
public class InOutHandler {
	
	/** The scanner to receive input from. */
	private Scanner scanner;
	/** The queue where output is sent and collected. */
	private LinkedList<Object> outputList = new LinkedList<Object>();
	
	
	/**
	 * Creates a new InOutHandler with System.in as the input and
	 * with System.out as the output.
	 */
	public InOutHandler() {
		scanner = new Scanner(System.in);
	}
	
	
	/**
	 * Adds the specified output to the output queue.
	 * @param output the output to add
	 */
	public void print(Object output) {
		outputList.add(output);
	}
	
	
	/**
	 * Removes the first added item from the output queue and returns it.
	 * Returns null if there are no remaining items.
	 * @return the first added item in the output queue
	 */
	public Object getOutput() {
		return outputList.poll();
	}
	
	
}
