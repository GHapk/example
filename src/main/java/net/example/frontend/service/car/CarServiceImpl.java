package net.example.frontend.service.car;

import java.util.List;
import net.example.backend.dao.Car.CarDao;
import net.example.backend.pojo.car.Car;
import net.example.backend.pojo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andre
 */
@Service
@Transactional
public class CarServiceImpl implements CarService{

    @Autowired
    private CarDao carDao;
    
    @Override
    public List<Car> fetchAll() {
        return carDao.fetchAll();
    }
    
    @Override
    public void save(Car car){
        carDao.save(car);
    }

    @Override
    public List<Car> fetchAllAvailableCars() {
        return carDao.fetchAllAvailable();
    }

    @Override
    public List<Car> fetchAllAvailableForSeller(User user) {
        return carDao.fetchAllAvailableForSeller(user);
    }

    @Override
    public List<Car> fetchAllSoldForSeller(User user) {
        return carDao.fetchAllSoldForSeller(user);
    }

    @Override
    public List<Car> fetchAllBoughtForCustomer(User user) {
        return carDao.fetchAllBoughtForCustomer(user);
    }
    
}
