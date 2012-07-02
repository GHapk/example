package net.example.backend.dao.Car;

import java.util.List;
import net.example.backend.dao.AbstractDao;
import net.example.backend.pojo.car.Car;
import net.example.backend.pojo.user.User;

/**
 *
 * @author andre
 */

public interface CarDao extends AbstractDao<Car> {

    public List<Car> fetchAllAvailable();

    public List<Car> fetchAllAvailableForSeller(User user);
    
    public List<Car> fetchAllSoldForSeller(User user);
    
    public List<Car> fetchAllBoughtForCustomer(User user);
}
