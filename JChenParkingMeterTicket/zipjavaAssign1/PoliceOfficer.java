package assign1;

/**
 * COSC 1020 - Spring 2020 Assignment #1 This class assigns and is able to get
 * an officer's name and number and also has a boolean comparison between how
 * long a car has been parked vs. time payed to determine if generating a ticket
 * is needed
 * 
 * @author Jimmy Chen
 */
public class PoliceOfficer {
	private String name;
	private int badgeNumber;

	/**
	 * @param name
	 * @param badgeNumber
	 */
	public PoliceOfficer(String name, int badgeNumber) {
		super();
		this.name = name;
		this.badgeNumber = badgeNumber;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return make
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param badgeNumber the badgeNumber to set
	 */
	public void setBadgeNumber(int badgeNumber) {
		this.badgeNumber = badgeNumber;
	}

	/**
	 * @return badgeNumber
	 */
	public int getBadgeNumber() {
		return badgeNumber;
	}

	/**
	 * @param c1 is object of ParkedCar
	 * @param c2 is object of ParkingMeter
	 * @return true if minutes parked is more than minutes payed, false otherwise
	 */
	public boolean examine(ParkedCar c1, ParkingMeter c2) {
		return (c1.getMinParked() > c2.getMinPayed());
	}
}
