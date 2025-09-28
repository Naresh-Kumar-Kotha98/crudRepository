package solidPrinciples.OCP2;

public class OCPImplementation {
	public static void main(String[] args) {
		PaymentServiceImpl paymentService = new PaymentServiceImpl();
		PaymentMethod cardPaymentMethod = new CardPayment();
		paymentService.processPayment(cardPaymentMethod, 10);
		
		PaymentMethod upiPaymentMethod = new UpiPayment();
		paymentService.processPayment(upiPaymentMethod, 210.50);
		
		PaymentMethod otherPaymentMethod = new OtherPayment();
		paymentService.processPayment(otherPaymentMethod, -20.50);
	}
}
