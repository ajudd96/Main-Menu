package menu.software;

import java.util.ArrayList;

public class Restaurant extends Log_In{

	// Fields
	private ArrayList<String> hours;
	private Menu menu;
	
	// Constructors
	public Restaurant() {
		hours = new ArrayList<String>();
		menu = new Menu();
	}
	
	public Restaurant(String name, String address, String email,
			String password, String phoneNumber, ArrayList<String> hours) {
		
		this.name = name.trim();
		this.address = address.trim();
		this.email = email.trim();
		this.password = password.trim();
		this.phoneNumber = phoneNumber.trim();
		this.hours = new ArrayList<String>(hours);
		
	}

	public Restaurant(Restaurant restaurant) {
		
		this.name = restaurant.getName();
		this.address = restaurant.getAddress();
		this.email = restaurant.getEmail();
		this.password = restaurant.getPassword();
		this.phoneNumber = restaurant.getNumber();
		this.hours = new ArrayList<String>(restaurant.getHours());
	}
	
	// Getters/Setters
	public void setHour(int day, String hour) {this.hours.set(day, hour);}
	public ArrayList<String> getHours(){ return hours;}
	public Menu getMenu() { return menu;}
	
	// Methods
}
