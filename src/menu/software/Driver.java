package menu.software;

public class Driver extends Log_In{
	
	//Fields
	private String vehicle;

	// Constructor
	public Driver() {
		this.vehicle = "unknown";
	}
	
	public Driver(String name, String address, String email, 
			String password, String phoneNumber, String vehicle) {
		
		this.name = name.trim();
		this.address = address.trim();
		this.email = email.trim();
		this.password = password.trim();
		this.phoneNumber = phoneNumber.trim();
		this.vehicle = vehicle.trim();
	}

	public Driver(Driver driver) {
		
		this.name = driver.getName();
		this.address = driver.getAddress();
		this.email = driver.getEmail();
		this.password = driver.getPassword();
		this.phoneNumber = driver.getNumber();
		this.vehicle = driver.getVehicle();
	}
	
	// Getters/Setters
	public void setVehicle(String vehicle) {this.vehicle = vehicle;}
	public String getVehicle() {return vehicle;}
	
	// Methods

}
