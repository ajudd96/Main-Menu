package menu.testing;

import menu.GUI.MainMenuGUI;
import menu.software.*;

public class GUIDriver {
	
	public static void main(String[] args) {
		/**/
		MainMenu menu = new MainMenu();

		//-------- Create Customer -----------//
		Customer cust1 = new Customer();
		
		cust1.setName("Frank Doe");
		cust1.setAddress("1535 Springs Rd.");
		cust1.setNumber("254 524 8248");
		cust1.setEmail("doeFrank@email.com");
		cust1.setPassword("Customer1");
		
		// Credit Card
		CreditCard c1 = new CreditCard();
		c1.setCVV("123");
		c1.setExpireDate("April 1 2020");	
		c1.setNumber("1234 5678 9101 1232");
		c1.setName("Frank Doe");
		cust1.addPayment(c1);
		
		// PayPal
		Paypal pp1 = new Paypal();
		pp1.setEmail("doeFrank@email.com");
		cust1.addPayment(pp1);
		
		menu.addCustomer(cust1);
		
		//-------- Create Driver -----------//
		Driver driv1 = new Driver();
		
		driv1.setName("Susan Shaw");
		driv1.setAddress("4568 Dale St.");
		driv1.setNumber("984 684 6581");
		driv1.setEmail("shaws@email.com");
		driv1.setPassword("Driver1");
		driv1.setVehicle("Chevrolet Equinox");
		
		menu.addDriver(driv1);
		
		//-------- Create Restaurant -----------//
		Restaurant rest1 = new Restaurant();
		
		rest1.setName("Billy Bob's Java Cafe");
		rest1.setAddress("857 Windsdrow St.");
		rest1.setNumber("166 489 4869");
		rest1.setEmail("javaCafe@email.com");
		rest1.setPassword("Restaurant1");
		
		rest1.getHours().add("8 AM to 9 PM");
		rest1.getHours().add("8 AM to 9 PM");
		rest1.getHours().add("8 AM to 9 PM");
		rest1.getHours().add("8 AM to 9 PM");
		rest1.getHours().add("8 AM to 9 PM");
		rest1.getHours().add("8 AM to 9 PM");
		rest1.getHours().add("8 AM to 9 PM");
		
		// Create Menu
		Item item1 = new Item();
		Item item2 = new Item();
		Item item3 = new Item();
		
		item1.setName("chicken wrap");
		item2.setName("ceaser salad");
		item3.setName("french fries");
		
		item1.setType("Entree");
		item2.setType("Side");
		item3.setType("Side");
		
		item1.setDescription("It is a wrap with chicken");
		item2.setDescription("It is a salad with ceaser dressing and chicken");
		item3.setDescription("They are fried potatoes");
		
		item1.setPrepTime(15);
		item2.setPrepTime(10);
		item3.setPrepTime(8);
		
		item1.setPrice(7.95);
		item2.setPrice(3.95);
		item3.setPrice(2.50);
		
		rest1.addItem(item1);
		rest1.addItem(item2);
		rest1.addItem(item3);

		menu.addRestaurant(rest1);
		
		// -------- Main Menu -----------//
		
		MainMenu.saveData(menu);
		
		// Create GUI
		MainMenuGUI newGUI = new MainMenuGUI();     
	}
}
