package menu.software;

import java.io.*;
import java.util.ArrayList;

public class Customer extends Log_In implements Serializable{

	// Fields
	private ArrayList<Payment> payments;
	
	// Constructors
	public Customer() {
		this.payments = new ArrayList<Payment>();
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
	public ArrayList<Payment> getPayments(){ return payments;}
	
	// Methods
	public void addPayment(Payment payment){ this.payments.add(payment);}
	public void removePayment(Payment payment){ this.payments.remove(payment);}

}
