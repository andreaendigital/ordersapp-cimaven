package com.devops;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private DiscountService stubDiscount;
    private OrderService service;

    @BeforeEach
    void setUp() {
        // Crea un mock para DiscountService y define comportamiento por defecto
        stubDiscount = mock(DiscountService.class);
        when(stubDiscount.getRate("")).thenReturn(0.10); // Usamos 10% como default
        service = new OrderService(stubDiscount);
    }

    @Test
    void testTotalWithoutDiscountRegularShipping() {
        when(stubDiscount.getRate("")).thenReturn(0.0);  // sin descuento
        double result = service.calculateTotal(100.0, false, false, "");
        assertEquals(110.0, result, 0.001); // 100 + envío regular
    }

    @Test
    void testTotalWithDiscountRegularShipping() {
        when(stubDiscount.getRate("")).thenReturn(0.10); // 10% descuento
        double result = service.calculateTotal(100.0, true, false, "");
        assertEquals(100.0, result, 0.001); // (100 * 0.9) + 10
    }

    @Test
    void testTotalWithoutDiscountExpressShipping() {
        when(stubDiscount.getRate("")).thenReturn(0.0);  // sin descuento
        double result = service.calculateTotal(100.0, false, true, "");
        assertEquals(120.0, result, 0.001); // 100 + envío express
    }

    @Test
    void testTotalWithDiscountExpressShipping() {
        when(stubDiscount.getRate("")).thenReturn(0.10); // 10% descuento
        double result = service.calculateTotal(100.0, true, true, "");
        assertEquals(110.0, result, 0.001); // (100 * 0.9) + 20
    }

    @Test
    void testZeroSubtotal() {
        when(stubDiscount.getRate("")).thenReturn(0.10);
        double result = service.calculateTotal(0.0, true, true, "");
        assertEquals(20.0, result, 0.001); // 0 + envío express
    }
}