package ch04.apps;

import java.util.*;
import ch04.queues.*;

/**
 * COSC 1020 - Spring 2020 Assignment #4 
 * This application utilizes methods from
 * the DeQueDLL class and asks the user whether they want to add or delete an
 * element from the double linked list. If they want to insert an element, it
 * will do so in numerical order. If they want to delete an element, it will
 * delete the first occurrence of the element or will notify if the element is not
 * in the deque to begin with
 * @author Jimmy Chen
 */
public class Assign4CLI {
	public static void main(String[] args) {
		//Sets up the initial scanner object, the actual double linked list (DLL), and the temporary DLL for sorting
		DeQueDLL<Integer> doubly = new DeQueDLL<Integer>();
		DeQueDLL<Integer> temp = new DeQueDLL<Integer>();
		Scanner inp = new Scanner(System.in);

		//Initializes the variable that is going to take in the scanner input
		int option = 0;
		//3 is the input the user will enter to stop the application
		final int quit = 3;
		
		//This while loop will keep asking the user to do something unless they decide to quit (enter '3')
		while (option != quit) {
			System.out.println("Choose an operation - 1. insert, 2. delete, 3. quit");
			option = inp.nextInt();

			//This condition will work when the user wants to insert an integer in the deque
			if (option == 1) {
				System.out.println("Enter an integer you want to insert in the deque: ");
				Scanner intInp = new Scanner(System.in);
				int num;
				num = intInp.nextInt();

				//Will check if the deque is empty in order to see how/if the entered integer will be ordered in the deque
				if (doubly.isEmpty()) {
					doubly.enqueueFront(num);
				} else {
					//This will add the integer to the front 
					if (num <= doubly.getFront()) {
						doubly.enqueueFront(num);
					} 
					//This will add the integer to the rear
					else if (num >= doubly.getRear()) {
						doubly.enqueueRear(num);
					} 
					//This will decide where to put the integer with the help of an empty DLL
					else {
						if (num > doubly.getFront()) {
							if (num < doubly.getRear()) {
								for (int i = 0; i < doubly.size() - 1; i++) {
									temp.enqueueRear(doubly.getFront());
									doubly.dequeueFront();
								}
								temp.enqueueRear(num);
								temp.enqueueRear(doubly.getRear());
								doubly = temp;
							}
						}
					}
				}
				System.out.println("deque: " + doubly.toString());
			} 
			//This condition will work if the user wants to delete a certain integer from the deque
			else if (option == 2) {
				System.out.println("Enter an integer you want to delete from the deque: ");
				Scanner intInp = new Scanner(System.in);
				int num;
				num = intInp.nextInt();

				//Will check if the deque is empty before attempting a deletion of an integer
				if (doubly.isEmpty()) {
					doubly.isEmpty();
				} 
				else {
					//This will check if the integer that the user wants to delete is even in the deque using a created 'search' method
					if (!doubly.search(num)) {
						System.out.println(num + " not found in the deque.");
					} else {
						//Will delete the front element if the integer is the front element
						if (num == doubly.getFront()) {
							doubly.dequeueFront();
						} 
						//Will delete the rear element if the integer is the rear element
						else if (num == doubly.getRear()) {
							doubly.dequeueRear();
						} else {
							//This will decide where the first instance of the integer is to delete with the help of the 'temp' DLL
							if (num > doubly.getFront()) {
								if (num < doubly.getRear()) {
									for (int i = 0; i < doubly.size() - 1; i++) {
										temp.enqueueRear(doubly.getFront());
										doubly.dequeueFront();
									}
									doubly.dequeueFront();
									temp.enqueueRear(doubly.getRear());
									doubly = temp;
								}
							}
						}
					}
					System.out.println("deque: " + doubly.toString());
				}
			}
		}
		//If the user enters '3' to quit, this will stop the loop and will be the end of the application
		System.out.println("Goodbye!!");
	}
}