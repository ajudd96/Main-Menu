package menu.software;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Menu implements Serializable{
	
	// Fields
	private ArrayList<Item> entrees;
	private ArrayList<Item> sides;
	private ArrayList<Item> drinks;
	private static final DecimalFormat dformat = new DecimalFormat("#.00");
	
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
		for (Item item: entrees) {
			System.out.printf("%s $%f.2 %s\n", item.getName(), item.getPrice(), item.getDescription());
		}
	}
	
	public void printSides() {
		for (Item item: sides) {
			System.out.printf("%s $%f.2 %s\n", item.getName(), item.getPrice(), item.getDescription());
		}
	}
	
	public void printDrinks() {
		for (Item item: drinks) {
			System.out.printf("%s $%f.2 %s\n", item.getName(), item.getPrice(), item.getDescription());
		}
	}
	
	public ArrayList<String> getEntreesList() {
		ArrayList<String> entreeList = new ArrayList<String>();
		
		for (Item item: entrees) {
			entreeList.add(item.getName() + "				" + dformat.format(item.getPrice()) + '\n' + item.getDescription() + '\n') ;
		}
		
		return entreeList;
	}
	
	public ArrayList<String> getSidesList() {
		ArrayList<String> sideList = new ArrayList<String>();
		
		for (Item item: sides) {
			sideList.add(item.getName() + "				" + dformat.format(item.getPrice()) + '\n' + item.getDescription() + '\n');
		}
		
		return sideList;
	}
	
	public ArrayList<String> getDrinksList() {
		ArrayList<String> drinkList = new ArrayList<String>();
		
		for (Item item: drinks) {
			drinkList.add(item.getName() + "				" + dformat.format(item.getPrice()) + '\n' + item.getDescription() + '\n');
		}
		
		return drinkList;
	}

}
