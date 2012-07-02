package net.example.frontend.service.order;

import net.example.backend.pojo.order.Carorder;
import net.example.backend.pojo.order.Payment;

/**
 *
 * @author andre
 */
public interface OrderService {
 
    public Carorder saveOrder(Carorder order);
    
    public void savePayment(Payment payment);
}
