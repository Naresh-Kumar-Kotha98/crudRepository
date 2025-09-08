package solidPrinciples.OCP2;

public class UpiPayment implements PaymentMethod {
	public void doPayment(double amount) {
		System.out.println("UPI payment donewith Rs:"+ amount);
	}
}
