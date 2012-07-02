package net.example.frontend.service.car;

import java.util.List;
import net.example.backend.pojo.car.Car;
import net.example.backend.pojo.user.User;

/**
 *
 * @author andre
 */
public interface CarService {

    public List<Car> fetchAll();
    
    public void save(Car car);

    public List<Car> fetchAllAvailableCars();

    public List<Car> fetchAllAvailableForSeller(User user);
    
    public List<Car> fetchAllSoldForSeller(User user);
    
    public List<Car> fetchAllBoughtForCustomer(User user);
    
}
