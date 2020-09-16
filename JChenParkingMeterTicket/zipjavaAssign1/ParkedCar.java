package assign1;
/**
 * COSC 1020 - Spring 2020
 * Assignment #1
 * This class is responsible with setting and getting a car's make, model,
 * color, license number, and how many minutes the car was parked
 * @author Jimmy Chen
 */
public class ParkedCar {
	private String make;
	private int model;
	private String color;
	private String licenseNumber;
	private int minParked;
	/**
	 * @param make
	 * @param model
	 * @param licenseNumber
	 * @param minParked
	 */
	public ParkedCar(String make, int model, String color, String licenseNumber, int minParked) {
		super();
		this.make=make;
		this.model=model;
		this.color=color;
		this.licenseNumber=licenseNumber;
		this.minParked=minParked;
	}
	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make=make;
	}
	/**
	 * @return make
	 */
	public String getMake() {
		return make;
	}
	
	/**
	 * @param model the model to set
	 */
	public void setModel(int model) {
		this.model=model;
	}
	/**
	 * @return model
	 */
	public int getModel() {
		return model;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color=color;
	}
	/**
	 * @return color
	 */
	public String getColor() {
		return color;
	}
	
	/**
	 * @param licenseNumber the licenseNumber to set
	 */
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber=licenseNumber;
	}
	/**
	 * @return licenseNumber
	 */
	public String getLicenseNumber() {
		return licenseNumber;
	}
	
	/**
	 * @param minParked the minParked to set
	 */
	public void setMinParked(int minParked) {
		this.minParked=minParked;
	}
	/**
	 * @return minParked
	 */
	public int getMinParked() {
		return minParked;
	}
}
