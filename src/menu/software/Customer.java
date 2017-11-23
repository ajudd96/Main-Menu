package menu.software;

import java.io.Serializable;

public class Customer extends Log_In implements Serializable{

	// Fields
	
	// Constructors
	public Customer() {

	}
	
	public Customer(String name, String address, 
			String email, String password, String phoneNumber) {
		
		this.name = name.trim();
		this.address = address.trim();
		this.email = email.trim();
		this.password = password.trim();
		this.phoneNumber = phoneNumber.trim();
	}
	
	public Customer(Customer customer) {
		
		this.name = customer.getName();
		this.address = customer.getAddress();
		this.email = customer.getEmail();
		this.password = customer.getPassword();
		this.phoneNumber = customer.getNumber();
	}
	
	// Getters/Setters
	
	// Methods

}
