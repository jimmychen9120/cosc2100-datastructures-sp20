//----------------------------------------------------------------------
// PFixCLI.java           by Dale/Joyce/Weems                  Chapter 2
//
// Evaluates postfix expressions entered by the user.
// Uses a command line interface.
//----------------------------------------------------------------------
package ch02.apps;

import java.util.*;

import ch02.postfix.*;
/**
 * COSC 2100 - Spring 2020
 * Assignment #3
 * This program asks the user for integers and operators to push to a stack 
 * to perform arithmetic with the given values and prints out the result and statistics
 * @author Jimmy Chen
 */
public class JChenPFixCLI {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		String expression = null; // expression to be evaluated
		final String STOP = "X"; // indicates end of input
		int result; // result of evaluation

		while (!STOP.equals(expression)) {
			// Get next expression to be processed.
			System.out.print("\nPostfix Expression (" + STOP + " to stop): ");
			expression = scan.nextLine();
			int count = expression.length() / 3;
			int sum = 0;
			int min = 2147483647; // initialize min with the top of int range to compare with integers later
			int max = 0;

			if (!STOP.equals(expression)) {
				// Puts all the characters in 'expression' to an array while removing white
				// spaces
				String[] characters = expression.split(" ");
				for (int i = 0; i < characters.length; i++) {
					// Checks if the character evaluated is an integer or not
					if (!(characters[i].equals("/") || characters[i].equals("*") || characters[i].equals("+")
							|| characters[i].equals("-") || characters[i].equals("^"))) {
						if (!(characters[i].matches("[a-zA-Z]+"))) {
							// Finds the min and max and adds up all the integers
							int num = Integer.parseInt(characters[i]);
							if (num > max) {
								max = num;
							}
							if (num < min) {
								min = num;
							}
							sum += num;
						}
					}
				}
			}

			// Obtain and output result of expression evaluation.
			try {
				result = JChenPostFixEvaluator.evaluate(expression);
				// Output result.
				System.out.println("Result = " + result);
			} catch (PostFixException error) {
				// Output error message.
				System.out.println("Error in expression - " + error.getMessage());
			}

			// Outputs the statistics
			System.out.println("**STATISTICS**");
			System.out.println("Largest Number: " + max);
			System.out.println("Smallest Number: " + min);
			System.out.println("Count: " + count);
			System.out.println("Average: " + (sum / count));
		}
	}
}
