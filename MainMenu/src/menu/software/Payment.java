package menu.software;

import java.io.Serializable;

public class Payment implements Serializable{
	private String type;
	
	public Payment() {
		type = "";
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String aType) {
		type = aType;
	}
}
