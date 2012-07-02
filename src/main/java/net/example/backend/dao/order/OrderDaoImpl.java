package net.example.backend.dao.order;

import net.example.backend.annotation.Dao;
import net.example.backend.dao.AbstractDaoImpl;
import net.example.backend.pojo.order.Carorder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
@Dao(entityClass=net.example.backend.pojo.order.Carorder.class)
public class OrderDaoImpl extends AbstractDaoImpl<Carorder> implements OrderDao{
    
}
