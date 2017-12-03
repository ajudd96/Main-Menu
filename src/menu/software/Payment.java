package menu.software;

import java.io.Serializable;

public class Payment implements Serializable{
	private String type;
	private int creditCardLastFourNumber;
	private String payPalEmail;
	
	public Payment() {
		type = "";
		creditCardLastFourNumber = 0;
		payPalEmail = "";
	}
	
	public Payment(Payment payment) {
		type = payment.getType();
		creditCardLastFourNumber = payment.getCreditCardLastFourNumber();
		payPalEmail = payment.getPayPalEmail();
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String aType) {
		type = aType;
	}
	
	public int getCreditCardLastFourNumber() {
		return creditCardLastFourNumber;
	}
	
	public void setCreditCardLastFourNumber(int aInt) {
		creditCardLastFourNumber = aInt;
	}
	
	public String getPayPalEmail() {
		return payPalEmail;
	}
	
	public void setPayPalEmail(String aString) {
		payPalEmail = aString;
	}
	
}
