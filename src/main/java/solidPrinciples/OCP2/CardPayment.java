package solidPrinciples.OCP2;

public class CardPayment implements PaymentMethod {
	public void doPayment(double amount) {
		System.out.println("card payment  donewith Rs:"+ amount);
	}
}
