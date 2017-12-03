package menu.software;

public class CreditCard extends Payment{
	private String number;
	private String CVV;
	private String expireDate;
	private String name;
	
	public CreditCard() {
		setType("Credit Card");
		number = "";
		CVV = "";
		expireDate ="";
		name= "";
	}
	
	public CreditCard(String number, String CVV, String date, String name) {
		setType("Credit Card");
		this.number = number.trim();
		this.CVV = CVV.trim();
		this.expireDate = date.trim();
		this.name= name.trim();
		//setCreditCardLastFourNumber(Integer.parseInt(number) % 10000);	// convert string to int first, and then calculates the last 4 digits
	}
	
	public CreditCard(CreditCard card) {
		setType("Credit Card");
		this.number = card.getNumber();
		this.CVV = card.getCVV();
		this.expireDate = card.getExpireDate();
		this.name= card.getName();
		//setCreditCardLastFourNumber(Integer.parseInt(number) % 10000);	// convert string to int first, and then calculates the last 4 digits
	}

	public String getNumber() {
		return number;
	}
	
	public void setNumber(String aNumber) {
		number = aNumber;
	}
	
	public String getCVV() {
		return CVV;
	}
	
	public void setCVV(String aCVV) {
		CVV = aCVV;
	}
	
	public String getExpireDate() {
		return expireDate;
	}
	
	public void setExpireDate(String aExpireDate) {
		expireDate = aExpireDate;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String aName) {
		name = aName;
	}
	
}
