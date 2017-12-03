package menu.software;

public class Paypal extends Payment{
	private String email;
	
	public Paypal() {
		setType("Paypal");
		email ="";
	}
	
	public Paypal(String email) {
		setType("Paypal");
		this.email = email.trim();
		setPayPalEmail(email.trim());
	}
	
	public Paypal(Paypal paypal) {
		setType("Paypal");
		this.email = paypal.getEmail();
		setPayPalEmail(paypal.getEmail());
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String aEmail) {
		email = aEmail;
	}
}
