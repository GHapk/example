package net.example.backend.dao.Car;

import java.util.List;
import net.example.backend.annotation.Dao;
import net.example.backend.dao.AbstractDaoImpl;
import net.example.backend.pojo.car.Car;
import net.example.backend.pojo.user.User;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
@Dao(entityClass=net.example.backend.pojo.car.Car.class)
public class CarDaoImpl extends AbstractDaoImpl<Car> implements CarDao{

    @Override
    public List<Car> fetchAllAvailable() {
        try{
            return (List<Car>)this.sessionFactory.getCurrentSession()
                .createQuery(
                "FROM " + this.getHibernateTableName()
                + " WHERE id NOT IN (SELECT car.id FROM Carorder)")
                .list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Car> fetchAllAvailableForSeller(User user) {
        try{
            return (List<Car>)this.sessionFactory.getCurrentSession()
                .createQuery(
                "FROM " + this.getHibernateTableName()
                + " WHERE id NOT IN (SELECT car.id FROM Carorder) AND seller=:user")
                .setParameter("user", user)
                .list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        
    }

    @Override
    public List<Car> fetchAllSoldForSeller(User user) {
        try{
            return (List<Car>)this.sessionFactory.getCurrentSession()
                .createQuery(
                "FROM " + this.getHibernateTableName()
                + " WHERE id IN (SELECT car.id FROM Carorder) AND seller=:user")
                .setParameter("user", user)
                .list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        
    }

    @Override
    public List<Car> fetchAllBoughtForCustomer(User user) {
        try{
            return (List<Car>)this.sessionFactory.getCurrentSession()
                .createQuery(
                "FROM " + this.getHibernateTableName()
                + " WHERE id IN (SELECT car.id FROM Carorder WHERE customer=:user)")
                .setParameter("user", user)
                .list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    
}
