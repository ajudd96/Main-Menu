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
		
		this.menu = new Menu();
	}

	public Restaurant(Restaurant restaurant) {
		
		this.name = restaurant.getName();
		this.address = restaurant.getAddress();
		this.email = restaurant.getEmail();
		this.password = restaurant.getPassword();
		this.phoneNumber = restaurant.getNumber();
		this.hours = new ArrayList<String>(restaurant.getHours());
		
		this.menu = new Menu(restaurant.getMenu());
	}
	
	// Getters/Setters
	public void setHour(int day, String hour) {this.hours.set(day, hour);}
	public ArrayList<String> getHours(){ return hours;}
	public Menu getMenu() { return menu;}
	
	// Methods
	public void addItem(Item item) {
		if(item.getType().equals("Entree")) { menu.addEntree(item);}
		else if(item.getType().equals("Side")) { menu.addSide(item);}
		else if(item.getType().equals("Drink")) { menu.addDrink(item);}
	}
	
	public Item getItem(Item item) {
		if(item.getType().equals("Entree")) { 
			for(Item i: menu.getEntrees()) {
				if(i.getName().equals(item.getName())){return i;}
			}
		}
		else if(item.getType().equals("Side")) { 			
			for(Item i: menu.getSides()) {
				if(i.getName().equals(item.getName())){return i;}
			}
		}
		else if(item.getType().equals("Drink")) { 			
			for(Item i: menu.getDrinks()) {
				if(i.getName().equals(item.getName())){return i;}
			}
		}
		
		return null;
	}
	
	public ArrayList<String> getOrderList() {
		ArrayList<String> list = new ArrayList<String>();
		
		for(Order order : this.getOrders()) {
			list.add(order.RestaurantOrderList());
		}
		
		return list;
	}
}
