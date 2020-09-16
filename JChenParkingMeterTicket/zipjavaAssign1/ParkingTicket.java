package assign1;
/**
 * COSC 1020 - Spring 2020
 * Assignment #1
 * This class sets up methods for which each section of the ticket would generate
 * @author Jimmy Chen
 */
public class ParkingTicket {
	private int parked;
	private int payed;
	/**
	 * @param parked
	 * @param payed
	 */
	public ParkingTicket(int parked, int payed) {
		this.parked=parked;
		this.payed=payed;
	}
	/**
	 * @param car
	 */
	public void carInfo(ParkedCar car) {
		System.out.println("Car Info: "+car.getMake()+" "+car.getModel()+" "+car.getColor()+" "+car.getLicenseNumber());
	}
	
	/**
	 * @param cop
	 */
	public void policeInfo(PoliceOfficer cop) {
		System.out.println("Police Officer Info: "+cop.getName()+", "+cop.getBadgeNumber());
	}
	
	/**
	 * calculates minutes exceeded from payed time
	 */
	public void minExceed() {
		int difference=parked-payed;
		System.out.println("Minutes Exceeded: "+difference);
	}
	
	/**
	 * outputs total fine amount
	 */
	public void addFine() {
		int initialFine=25;
		int difference=parked-payed;
		int moreHours=difference/60;
		int addTen=moreHours*10;
		int totFine=initialFine+addTen;
		System.out.println("Amount of fine to be payed: $"+totFine);
	}

}
