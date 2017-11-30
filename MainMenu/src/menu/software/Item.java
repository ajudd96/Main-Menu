package menu.software;

import java.io.Serializable;

public class Item implements Serializable{
	private String name;
	private String type;
	private String description;
	private int prepTime;
	private Double price;
	
	public Item() {
		name = "unknown";
		type = "unknown";
		description = "unknown";
		prepTime = 0;
		price = 0.0;
	}
	
	public Item(Item item) {
		name = item.getName().trim();
		type = item.getType().trim();
		description = item.getDescription();
		prepTime = item.getPrepTime();
		price = item.getPrice();
	}
	
	public void setName(String aName) {name = aName;};
	public String getName() {return name;};
	
	public void setType(String aType) {type = aType;};
	public String getType() {return type;};
	
	public void setDescription(String aDescription) {description = aDescription;};
	public String getDescription() {return description;};
	
	public void setPrepTime(int aPrepTime) {prepTime = aPrepTime;};
	public int getPrepTime() {return prepTime;};
	
	public void setPrice(Double aPrice) {price = aPrice;};
	public Double getPrice() {return price;};
}
