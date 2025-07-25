package com.devops;

// Class that provides services related to orders
public class OrderService {

    /**
     * Calculates the final total of an order based on:
     * - subtotal: base amount of the products
     * - applyDiscount: whether a discount should be applied
     * - expressShipping: whether express shipping is selected
     *
     * @param subtotal base amount before discount and shipping
     * @param applyDiscount flag that activates a 10% discount
     * @param expressShipping flag that chooses express (20.0) or regular (10.0) shipping
     * @return total cost after applying discount and shipping
     */

   
    private DiscountService discountService;
    public OrderService (DiscountService discountService){
    this.discountService = discountService ;
    }

    public double calculateTotal(double subtotal, boolean applyDiscount, boolean expressShipping, String discountCode) {
    
        double discount = discountService.getRate(discountCode);
    
        // Apply 10% discount if requested, otherwise no discount
      //  double discountRate = applyDiscount ? 0.1 : 0;
double discountRate = applyDiscount ? discount : 0;
        // Shipping cost: 20.0 for express, 10.0 for regular
        double shippingCost = expressShipping ? 20.0 : 10.0;

        // Final total: subtract discount from subtotal, then add shipping
        return (subtotal * (1 - discountRate)) + shippingCost;
    }
}
