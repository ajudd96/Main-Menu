package menu.software;

import java.io.*;
import java.util.ArrayList; 

public class MainMenu implements Serializable {
	
	// Fields
	public ArrayList<Customer> customers;
	public ArrayList<Driver> drivers;
	public ArrayList<Restaurant> restaurants;
	
	// Constructor
	public MainMenu() {
		
		customers = new  ArrayList<Customer>();
		drivers = new ArrayList<Driver>();
		restaurants = new ArrayList<Restaurant>();
		
	}
	
	public MainMenu(MainMenu menu) {
		
		customers = new  ArrayList<Customer>(menu.getCustomers());
		drivers = new ArrayList<Driver>(menu.getDrivers());
		restaurants = new ArrayList<Restaurant>(menu.getRestaurants());
		
	}
	
	// Getters/Setters
	public ArrayList<Customer> getCustomers(){ return customers;}	
	public ArrayList<Driver> getDrivers(){ return drivers;}	
	public ArrayList<Restaurant> getRestaurants(){ return restaurants;}

	// Methods
	public void addCustomer(Customer customer){ this.customers.add(customer);}
	public void removeCustomer(Customer customer){ this.customers.remove(customer);}
	
	public void addDriver(Driver driver){ this.drivers.add(driver);}
	public void removeDriver(Driver driver){ this.drivers.remove(driver);}
	
	public void addRestaurant(Restaurant restaurant){ this.restaurants.add(restaurant);}
	public void removeRestaurant(Restaurant restaurant){ this.restaurants.remove(restaurant);}
	
	public boolean CheckExists(int personType, String email, String password) {

		if(personType == 1) {
			for(Customer customer: customers) {
				if(customer.Verify(email, password)) {
					return true;
				}
			}
		}
		else if(personType == 2) {
			for(Driver driver: drivers) {
				if(driver.Verify(email, password)) {
					return true;
				}
			}			
		}
		else if(personType == 3) {
			for(Restaurant restaurant: restaurants) {
				if(restaurant.Verify(email, password)) {
					return true;
				}
			}			
		}
		
		return false;
	}

	public boolean CheckExists(int personType, String email) {

		if(personType == 1) {
			for(Customer customer: customers) {
				if(customer.Verify(email)) {
					return true;
				}
			}
		}
		else if(personType == 2) {
			for(Driver driver: drivers) {
				if(driver.Verify(email)) {
					return true;
				}
			}			
		}
		else if(personType == 3) {
			for(Restaurant restaurant: restaurants) {
				if(restaurant.Verify(email)) {
					return true;
				}
			}			
		}
		
		return false;
	}

	public int getIndex(int personType, String email, String password) {
		int index = 0;
		
		if(personType == 1) {
			for(Customer customer: customers) {
				if(customer.Verify(email, password)) {
					return index;
				}
			}
			index++;
		}
		else if(personType == 2) {
			for(Driver driver: drivers) {
				if(driver.Verify(email, password)) {
					return index;
				}
			}	
			index++;
		}
		else if(personType == 3) {
			for(Restaurant restaurant: restaurants) {
				if(restaurant.Verify(email, password)) {
					return index;
				}
			}	
			index++;
		}
		return -1;
	}
	
	public Customer getCustomer(String email, String password) {
		for(Customer customer: customers) {
			if(customer.Verify(email, password)) {
				return customer;
			}
		}
		return null;
	}

	public Driver getDriver(String email, String password) {
		for(Driver driver: drivers) {
			if(driver.Verify(email)) {
				return driver;
			}
		}
		return null;
	}
	
	public Restaurant getRestaurant(String email, String password) {
		for(Restaurant restaurant: restaurants) {
			if(restaurant.Verify(email)) {
				return restaurant;
			}
		}	
		return null;
	}

	
	public static void saveData(MainMenu menu) {
		
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut= null;

		try 
		{
			fileOut = new FileOutputStream( "MainMenu.ser" );		
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(menu);
			objOut.close();
			fileOut.close();
	     }	
		
		catch(IOException i)
	    {
			i.printStackTrace();
	    }	
		
	}

	public static MainMenu loadData() {
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		MainMenu menu = null;
			
		try
		{
			fileIn = new FileInputStream("MainMenu.ser");
			objIn = new ObjectInputStream(fileIn);
			menu = (MainMenu) objIn.readObject();
			objIn.close();
			fileIn.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}  
		return menu;
	}

	public void viewRestaurantList() {
		System.out.println("A list of Restaurants are as follows:\n");
		for(Restaurant aRestaurant: restaurants) {
			System.out.println(aRestaurant.getName() + "\n");
		}
	}
	
	public ArrayList<String> RestaurantList() {
		ArrayList<String> locations = new ArrayList<String>();
		
		for(Restaurant aRestaurant: restaurants) {
			locations.add(' ' + aRestaurant.getName());
		}
		return locations;
	}

	public boolean CheckItemExists(Item food, Restaurant restaurant) {
		boolean good = false;
		
		for(Item item : restaurant.getMenu().getEntrees()) {
			if((item.getName().equals(food.getName())) 
				&& (item.getType().equals(food.getType())) 
				&& (item.getDescription().equals(food.getDescription()))
				&& (item.getPrepTime() == food.getPrepTime())
				&& (item.getPrice() == food.getPrice())) 
				{return true;}
		}
		for(Item item : restaurant.getMenu().getSides()) {
			if((item.getName().equals(food.getName())) 
					&& (item.getType().equals(food.getType())) 
					&& (item.getDescription().equals(food.getDescription()))
					&& (item.getPrepTime() == food.getPrepTime())
					&& (item.getPrice() == food.getPrice())) 
					{return true;}
		}
		for(Item item : restaurant.getMenu().getDrinks()) {
			if((item.getName().equals(food.getName())) 
					&& (item.getType().equals(food.getType())) 
					&& (item.getDescription().equals(food.getDescription()))
					&& (item.getPrepTime() == food.getPrepTime())
					&& (item.getPrice() == food.getPrice())) 
					{return true;}
		}
		
		return good;
	}
	
	public int getOrderExists(Order order, Customer customer) {
		int index = 0;
			
		for(Order things : customer.getOrders()) {
			if((things.getCost() == order.getCost()) &&
					(things.getTotalTime() == order.getTotalTime()) &&
					(things.getPrepTime() == order.getPrepTime()) &&
					(things.getRestaurant().getEmail().equals(order.getRestaurant().getEmail())) &&
					(things.getItems().containsAll(order.getItems())) &&
					(things.getComment().equals(order.getComment())) &&
					(things.getComment().equals(order.getComment()))) {
				
				if(things.getPayment().getType().equals(order.getPayment().getType())) {
					// Does not account for different Payment
					if(things.getPayment().getCreditCardLastFourNumber() == order.getPayment().getCreditCardLastFourNumber() ||
							things.getPayment().getPayPalEmail().equals(order.getPayment().getPayPalEmail())) {
						return index;
					}
				}
				index++;
			}
			
		}
		
		return -1;
	}

}
