package net.example.frontend.service.order;

import net.example.backend.dao.order.OrderDao;
import net.example.backend.dao.order.PaymentDao;
import net.example.backend.pojo.order.Carorder;
import net.example.backend.pojo.order.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andre
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    
    @Autowired
    private OrderDao orderDao;
    
    @Autowired
    private PaymentDao paymentDao;
    
    @Override
    public Carorder saveOrder(Carorder order){
        return orderDao.save(order);
    }
    
    @Override
    public void savePayment(Payment payment){
        paymentDao.save(payment);
    }
    
}
