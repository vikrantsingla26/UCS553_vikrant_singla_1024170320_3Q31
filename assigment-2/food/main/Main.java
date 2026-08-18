package food.main;

import food.model.FoodOrder;
import food.model.RegularOrder;
import food.model.PremiumOrder;
import food.service.BillingService;
import food.utility.OrderUtility;

public class Main {

    public static void main(String[] args) {
        FoodOrder[] orders = new FoodOrder[6];

        orders[0] = new RegularOrder(101, "sam", 450.0);
        orders[1] = new PremiumOrder(102, "sachin", 900.0);
        orders[2] = new RegularOrder(103, "suyash", 320.0);
        orders[3] = new PremiumOrder(104, "subh", 1200.0);
        orders[4] = new RegularOrder(105, "zaza", 275.0);
        orders[5] = new PremiumOrder(106, "seedhemaut", 650.0);

        for (FoodOrder order : orders) {
            
            double deliveryCharge = order.calculateDeliveryCharge();
            double discount = BillingService.getDiscount(order);
            double finalAmount = BillingService.calculateFinalAmount(
                    order.getAmount(), discount, deliveryCharge);

            String summary = OrderUtility.generateOrderSummary(
                    order.getOrderId(), order.getCustomerName(), order.getAmount(),
                    discount, deliveryCharge, finalAmount);

            System.out.println(summary);
        }

        FoodOrder.displayTotalOrders();
    }
}
