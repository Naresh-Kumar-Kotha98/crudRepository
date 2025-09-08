package solidPrinciples.SRP1;

public class SRPImplementation {

	public static void main(String[] args) {
		PrintReceiptServiceImpl printReceiptService = new PrintReceiptServiceImpl();
		PlaceOrderServiceImpl placeOrderService = new PlaceOrderServiceImpl();
		printReceiptService.printReceipt();
		placeOrderService.placeOrder();
	}
}
