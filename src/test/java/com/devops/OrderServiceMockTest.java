package com.devops;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OrderServiceMockTest {
    @Test
    void testWithMockDiscount() {
        // Arrange
        DiscountService mockDiscount = mock(DiscountService.class);
        when(mockDiscount.getRate("SALES10")).thenReturn(0.10);

        OrderService service = new OrderService(mockDiscount);

        // Act
        double total = service.calculateTotal(100, true, false, "SALES10");

        // Assert
        assertEquals(100 * 0.9 + 10.0, total, 0.001);  // Descuento del 10%, envío regular
    }
}