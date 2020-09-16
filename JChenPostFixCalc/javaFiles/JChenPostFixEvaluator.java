//----------------------------------------------------------------------
// PostFixEvaluator.java       by Dale/Joyce/Weems             Chapter 2
//
// Provides a postfix expression evaluation.
//----------------------------------------------------------------------

package ch02.postfix;

import ch02.stacks.*;
import java.util.Scanner;
import java.util.stream.IntStream;
/**
 * COSC 2100 - Spring 2020
 * Assignment #3
 * This class evaluates the inputted expression entered by the user
 * and perform arithmetic.
 * @author Jimmy Chen
 */
public class JChenPostFixEvaluator {
	public static int evaluate(String expression) {
		Scanner tokenizer = new Scanner(expression);
		StackInterface<Integer> stack = new ArrayListStack<Integer>();

		int value;
		String operator;
		int operand1, operand2;
		int result = 0;
		int count = expression.length() / 3;
		int size = count;
		int[] arrayVal = new int[size];
		int index = 0;

		while (tokenizer.hasNext()) {
			if (tokenizer.hasNextInt()) {
				// Process operand.
				value = tokenizer.nextInt();
				stack.push(value);

				// Sets the integer value in an index in the array "arrayVal"
				arrayVal[index] = value;
				index++;
			} else {
				// Process operator.
				operator = tokenizer.next();

				// Check for illegal symbol
				if (!(operator.equals("/") || operator.equals("*") || operator.equals("+") || operator.equals("-")
						|| operator.contentEquals("^")))
					throw new PostFixException("Illegal symbol: " + operator);

				// Obtain second operand from stack.
				if (stack.isEmpty())
					throw new PostFixException("Not enough operands-stack underflow");
				operand2 = stack.top();
				stack.pop();

				// Obtain first operand from stack.
				if (stack.isEmpty())
					throw new PostFixException("Not enough operands-stack underflow");
				operand1 = stack.top();
				stack.pop();

				// Perform operation.
				if (operator.equals("/")) {
					// Check for illegal divide-by-zero situation and throws PostFixException
					if (operand1 == 0 || operand2 == 0) {
						throw new PostFixException("Illegal divide by zero");
					} else {
						result = operand1 / operand2;
					}
				} else if (operator.equals("*"))
					result = operand1 * operand2;
				else if (operator.equals("+"))
					result = operand1 + operand2;
				else if (operator.equals("-"))
					result = operand1 - operand2;
				// New operation for the ^ operator to return the greater number value
				else {
					if (operator.equals("^")) {
						if (operand1 > operand2) {
							result = operand1;
						} else {
							result = operand2;
						}
					}
				}
				// Push result of operation onto stack.
				stack.push(result);
			}
		}

		// Obtain final result from stack.
		if (stack.isEmpty())
			throw new PostFixException("Not enough operands-stack underflow");
		result = stack.top();
		stack.pop();

		// Stack should now be empty.
		if (!stack.isEmpty())
			throw new PostFixException("Too many operands-operands left over");

		// Return the final.
		return result;

	}
}