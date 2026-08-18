package food.utility;

public class OrderUtility {

    public static boolean validateAmount(double amount) {
        return amount > 0;
    }

    public static boolean validateCustomerName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static String generateOrderSummary(int orderId, String customerName, double amount,
                                               double discount, double deliveryCharge, double finalAmount) {
        return String.format(
            "Order #%d | Customer: %s | Bill: Rs. %.2f | Discount: Rs. %.2f | Delivery: Rs. %.2f | Final Payable: Rs. %.2f",
            orderId, customerName, amount, discount, deliveryCharge, finalAmount
        );
    }
}
