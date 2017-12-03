package menu.software;

import java.io.Serializable;
import java.util.ArrayList;

public class Log_In implements Serializable{

	// Fields
	protected String name;
	protected String address;
	protected String email;
	protected String password;
	protected String phoneNumber;
	protected ArrayList<Order> orders;
	
	// Constructor
	public Log_In() {
		this.name = "unkonwn";
		this.address = "unknown";
		this.email = "unknown";
		this.password = "unknown";
		this.phoneNumber = "unknown";
		
		this.orders = new ArrayList<Order>();
	}
	
	// Setters/Getters
	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	public void setAddress(String address) {this.address = address;}
	public String getAddress() {return address;}
	public void setEmail(String email) {this.email = email.trim();}
	public String getEmail() {return email;}
	public void setPassword(String password) {this.password = password;}
	public String getPassword() {return password;}
	public void setNumber(String number) {this.phoneNumber = number;}
	public String getNumber() {return phoneNumber;}
	
	public void addOrder(Order order) {this.orders.add(order);}
	public void removeOrder(Order order) {this.orders.remove(order);}
	public ArrayList<Order> getOrders() {return orders;}
	
	// Methods
	public boolean Verify(String email, String password) {
		boolean good = false;
		
		if(this.email.equals(email) && this.password.equals(password)) {
			good = true;
		}
		
		return good;
	}
	
	public boolean Verify(String email) {
		boolean good = false;
		
		if(this.email.equals(email)) {
			good = true;
		}
		
		return good;
	}

}
