package menu.software;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Customer extends Log_In implements Serializable{

	// Fields
	private ArrayList<Payment> payments;
	private ArrayList<CreditCard> cards;
	private ArrayList<Paypal> paypals;
	
	private static final DecimalFormat dformat = new DecimalFormat("0000");
	
	// Constructors
	public Customer() {
		this.payments = new ArrayList<Payment>();
		this.cards = new ArrayList<CreditCard>();
		this.paypals = new ArrayList<Paypal>();
	}
	
	public Customer(String name, String address, 
			String email, String password, String phoneNumber) {
		
		this.name = name.trim();
		this.address = address.trim();
		this.email = email.trim();
		this.password = password.trim();
		this.phoneNumber = phoneNumber.trim();
		
		this.cards = new ArrayList<CreditCard>();
		this.paypals = new ArrayList<Paypal>();
	}
	
	public Customer(Customer customer) {
		
		this.name = customer.getName();
		this.address = customer.getAddress();
		this.email = customer.getEmail();
		this.password = customer.getPassword();
		this.phoneNumber = customer.getNumber();
		
		this.cards = new ArrayList<CreditCard>(customer.getCreditCards());
		this.paypals = new ArrayList<Paypal>(customer.getPaypals());
	}
	
	// Getters/Setters
	public ArrayList<Payment> getPayments(){ return payments;}
	public ArrayList<CreditCard> getCreditCards(){ return cards;}
	public ArrayList<Paypal> getPaypals(){ return paypals;}
	
	// Methods
	public void addPayment(Payment payment){ this.payments.add(payment);}
	public void removePayment(Payment payment){ this.payments.remove(payment);}
	public void addCard(CreditCard payment){ this.cards.add(payment);}
	public void removeCard(CreditCard payment){ this.cards.remove(payment);}
	public void addPaypal(Paypal payment){ this.paypals.add(payment);}
	public void removePaypal(Paypal payment){ this.paypals.remove(payment);}

	public void viewPayments() {
		System.out.println("The following are your saved Credit Card(s):\n");
		for(CreditCard aCreditCard:cards) {
			if(aCreditCard.getType() == "Credit Card") {
				System.out.println("XXXX-XXXX-XXXX-" + aCreditCard.getCreditCardLastFourNumber() + "\n");
			}
		}
		System.out.println("The following are your saved PayPal accounts:\n");
		for(Paypal aPayPal:paypals) {
			if(aPayPal.getType() == "Paypal") {
				System.out.println(aPayPal.getPayPalEmail() + "\n");
			}
		}
	}
	
	public ArrayList<String> CreditCardList() {
		ArrayList<String> pay = new ArrayList<String>();
		
		for(CreditCard aCreditCard:cards) {
			pay.add(" XXXX-XXXX-XXXX-" + aCreditCard.getCreditCardLastFourNumber());
		}
		
		return pay;
	}
	
	public ArrayList<String> PaypalList() {
		ArrayList<String> pay = new ArrayList<String>();
		
		for(Paypal aPayPal:paypals) {
			pay.add(' ' + aPayPal.getPayPalEmail());
		}
		
		return pay;
	}

	public ArrayList<String> getOrderList() {
		ArrayList<String> list = new ArrayList<String>();
		
		for(Order order : this.getOrders()) {
			list.add(order.CustomerOrderList());
		}
		
		return list;
	}
	
	public boolean getCardEqual(CreditCard p1, CreditCard p2) {
		if(p1.getNumber().equals(p2.getNumber()) &&
				p1.getCVV().equals(p2.getCVV()) &&
				p1.getExpireDate().equals(p2.getExpireDate()) &&
				p1.getName().equals(p2.getName())){
				return true;
		}
		
		return false;
	}
	
	public boolean getPaypalEqual(Paypal p1, Paypal p2) {
		if(p1.getEmail().equals(p2.getEmail())) {
			return true;
		}
		return false;
	}
}
