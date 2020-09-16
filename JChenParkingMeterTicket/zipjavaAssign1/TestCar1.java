package assign1;
/**
 * COSC 1020 - Spring 2020
 * Assignment #1
 * This program tests and demonstrates how the 4 classes work together to print out
 * a need ticket to the output console.
 * @author Jimmy Chen
 */
public class TestCar1 {
	public static void main(String args[]) {
		/**
		 * Setting objects to each class
		 */
		ParkedCar jonsCar=new ParkedCar("Toyota", 2003, "silver", "B029MM2",13);
		ParkingMeter jonPayed=new ParkingMeter(30);
		PoliceOfficer dan=new PoliceOfficer("Brain Bunn", 1402);
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
