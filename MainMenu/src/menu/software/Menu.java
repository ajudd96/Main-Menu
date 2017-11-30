package menu.software;

import java.io.*;
import java.util.ArrayList;

public class Menu implements Serializable{
	
	// Fields
	private ArrayList<Item> entrees;
	private ArrayList<Item> sides;
	private ArrayList<Item> drinks;
		
	// Constructors
	public Menu() {
		this.entrees = new ArrayList<Item>();
		this.sides = new ArrayList<Item>();
		this.drinks = new ArrayList<Item>();
	}
	
	public Menu(Menu menu) {
		this.entrees = new ArrayList<Item>(menu.getEntrees());
		this.sides = new ArrayList<Item>(menu.getSides());
		this.drinks = new ArrayList<Item>(menu.getDrinks());
	}

	// Getters/Setter
	public ArrayList<Item> getEntrees() { return entrees;}
	public ArrayList<Item> getSides() { return sides;}
	public ArrayList<Item> getDrinks() { return drinks;}

	// Methods
	public void addEntree(Item item) { this.entrees.add(item);}
	public void removeEntree(Item item) { this.entrees.remove(item);}
	
	public void addSide(Item item) { this.sides.add(item);}
	public void removeSide(Item item) { this.sides.remove(item);}
	
	public void addDrink(Item item) { this.drinks.add(item);}
	public void removeDrink(Item item) { this.drinks.remove(item);}

}
