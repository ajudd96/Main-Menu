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

	public void printEntrees() {
		for (int i=0, i < entrees.size, i++) {
			System.out.printf("%s $%f.2 %s\n", this.entrees.get(i).name, this.entrees.get(i).price, this.entrees.get(i).description);
		}
	}
	
	public void printSides() {
		for (int i=0, i < sides.size, i++) {
			System.out.printf("%s $%f.2 %s\n", this.sides.get(i).name, this.sides.get(i).price, this.sides.get(i).description);
		}
	}
	
	public void printDrinks() {
		for (int i=0, i < drinks.size, i++) {
			System.out.printf("%s $%f.2 %s\n", this.drinks.get(i).name, this.drinks.get(i).price, this.drinks.get(i).description);
		}
	}
}
