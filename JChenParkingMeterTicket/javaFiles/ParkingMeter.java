package assign1;
/**
 * COSC 1020 - Spring 2020
 * Assignment #1
 * This class is responsible with setting and getting the amount of minutes
 * of parking has been purchased
 * @author Jimmy Chen
 */
public class ParkingMeter {
	private int minPayed;
	/**
	 * @param minPayed
	 */
	public ParkingMeter(int minPayed) {
		super();
		this.minPayed=minPayed;
	}
	
	/**
	 * @param minPayed the minPayed to set
	 */
	public void setMinPayed(int minPayed) {
		this.minPayed=minPayed;
	}
	/**
	 * @return minPayed
	 */
	public int getMinPayed() {
		return minPayed;
	}
}
