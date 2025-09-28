package solidPrinciples.OCP2;

public class PaymentServiceImpl {
	public  void  processPayment(PaymentMethod method, double amount) {
		method.doPayment(amount);
	}
}
