package solidPrinciples.OCP2;

public class OtherPayment implements PaymentMethod {
	public void doPayment(double amount) {
		System.out.println("Other payment  donewith Rs:"+ amount);
	}
}
