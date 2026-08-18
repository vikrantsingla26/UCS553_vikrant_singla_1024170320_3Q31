package food.service;

import food.model.FoodOrder;
import food.model.Discountable;

public class BillingService {

    public static double getDiscount(FoodOrder order) {
        if (order instanceof Discountable) {
            return ((Discountable) order).applyDiscount();
        }
        return 0.0;
    }

    public static double calculateFinalAmount(double amount, double discount, double deliveryCharge) {
        return amount - discount + deliveryCharge;
    }
}
