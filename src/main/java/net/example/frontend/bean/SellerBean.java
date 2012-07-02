package net.example.frontend.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import net.example.backend.pojo.car.Cabrio;
import net.example.backend.pojo.car.Car;
import net.example.backend.pojo.car.Pickup;
import net.example.frontend.ext.CurrentUser;
import net.example.frontend.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Component("sellerBean")
@RequestScoped
public class SellerBean {

    @Autowired
    private CarService carService;
    
    private List<Car> cars;
    private List<Car> soldCars;
    private Car newCar;
    private Car currentCar;
    
    private HtmlDataTable carTable;
    
    /**
     * Creates a new instance of SellerBean
     */
    public SellerBean() {
    }
    
    @PostConstruct
    private void init(){
        loadList();
    }

    public HtmlDataTable getCarTable() {
        return carTable;
    }

    public void setCarTable(HtmlDataTable carTable) {
        this.carTable = carTable;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public Car getCurrentCar() {
        return currentCar;
    }

    public void setCurrentCar(Car currentCar) {
        this.currentCar = currentCar;
    }

    public Car getNewCar() {
        return newCar;
    }

    public void setNewCar(Car newCar) {
        this.newCar = newCar;
    }

    public List<Car> getSoldCars() {
        return soldCars;
    }

    public void setSoldCars(List<Car> soldCars) {
        this.soldCars = soldCars;
    }
    
    public String prepareNewCar(){
        this.currentCar = new Car();
        this.currentCar.setSeller(CurrentUser.INSTANCE.getUser());
        this.currentCar.setType("NORMAL");
        return "/seller/carForm";
    }
    
    public String prepareNewCabrio(){
        this.currentCar = new Cabrio();
        this.currentCar.setSeller(CurrentUser.INSTANCE.getUser());
        this.currentCar.setType("CABRIO");
        return "/seller/cabrioForm";
    }
    
    public String prepareNewPickup(){
        this.currentCar = new Pickup();
        this.currentCar.setSeller(CurrentUser.INSTANCE.getUser());
        this.currentCar.setType("PICKUP");
        return "/seller/pickupForm";
    }
    
    public String prepareNew(String type){
        if(type.equals("pickup")){
            this.currentCar = new Pickup();
            return "/seller/pcikupForm";
        }else if(type.equals("cabrio")){
            this.currentCar = new Cabrio();
            return "/seller/cabrioForm";
        }else{
            this.currentCar = new Car();
            return "/seller/carForm";
        }
    }
    
    public String prepareList(){
        loadList();
        return "/seller/carList";
    }
    
    public String prepareListSold(){
        loadSoldCars();
        return "/seller/carListSold";
    }
    
    private void loadList(){
        this.cars = this.carService.fetchAllAvailableForSeller(CurrentUser.INSTANCE.getUser());
    }
    
    private void loadSoldCars(){
        this.soldCars = this.carService.fetchAllSoldForSeller(CurrentUser.INSTANCE.getUser());
    }
    
    public String prepareEdit(){
        this.currentCar = (Car) this.carTable.getRowData();
        if(this.currentCar.getClass().equals(Cabrio.class)){
            return "/seller/cabrioForm";
        }else if(this.currentCar.getClass().equals(Pickup.class)){
            return "/seller/pickupForm";
        }else{
            return "/seller/carForm";
        }
    }
    
    public String save(){
        this.carService.save(this.currentCar);
        return prepareList();
    }
}
