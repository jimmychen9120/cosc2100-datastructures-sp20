package assign1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
/**
 * COSC 1020 - Spring 2020
 * Assignment #1
 * This program tests and demonstrates how the 4 classes work together to print out
 * a need ticket to a .txt file (identical to the 'TestCar1' program)
 * @author Jimmy Chen
 */
public class TicketText {
	public static void main(String arr[]) throws FileNotFoundException {
		/*
		 * Creating a File object
		 */
		PrintStream genTicket = new PrintStream(new File("test_case1.txt"));
		/*
		 * Store System.out before assigning a new value
		 */
		PrintStream console = System.out;
		/*
		 * Assign genTicket to output stream
		 */
		System.setOut(genTicket);
		/**
		 * Setting objects to each class
		 */
		ParkedCar jonsCar=new ParkedCar("Fiat", 2007, "Green", "7H2349M",56);
		ParkingMeter jonPayed=new ParkingMeter(55);
		PoliceOfficer dan=new PoliceOfficer("Dan Bean", 9083);
		ParkingTicket ticket=new ParkingTicket(jonsCar.getMinParked(),jonPayed.getMinPayed());
		
		/**
		 * uses PoliceOfficer examine method with ParkingTicket methods to generate ticket
		 */
		if (dan.examine(jonsCar, jonPayed)==true) {
			ticket.carInfo(jonsCar);
			ticket.policeInfo(dan);
			ticket.minExceed();
			ticket.addFine();
		}
		else {
			System.out.println("Did not exceed minutes purchased. No parking ticket issued");
		}
	}
}

