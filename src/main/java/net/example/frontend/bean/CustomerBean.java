package net.example.frontend.bean;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import net.example.backend.pojo.car.Car;
import net.example.backend.pojo.order.Carorder;
import net.example.backend.pojo.order.Cash;
import net.example.backend.pojo.order.Creditcard;
import net.example.backend.pojo.order.Payment;
import net.example.frontend.ext.CurrentUser;
import net.example.frontend.service.car.CarService;
import net.example.frontend.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Component("customerBean")
@RequestScoped
public class CustomerBean {

    private List<Car> availableCars;
    private List<Car> boughtCars;
    private Car currentCar;
    
    private Carorder currentOrder;
    private Payment currentPayment;
    
    @Autowired
    private CarService carService;
    @Autowired
    private OrderService orderService;
    
    private HtmlDataTable carTable;
    
    /**
     * Creates a new instance of CustomerBean
     */
    public CustomerBean() {
    }
    
    @PostConstruct
    private void init(){
        loadAvailableCars();
    }
    
    private void loadAvailableCars(){
        this.availableCars = carService.fetchAllAvailableCars();
    }

    public List<Car> getAvailableCars() {
        return availableCars;
    }

    public void setAvailableCars(List<Car> availableCars) {
        this.availableCars = availableCars;
    }

    public List<Car> getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(List<Car> boughtCars) {
        this.boughtCars = boughtCars;
    }

    public Car getCurrentCar() {
        return currentCar;
    }

    public void setCurrentCar(Car currentCar) {
        this.currentCar = currentCar;
    }

    public HtmlDataTable getCarTable() {
        return carTable;
    }

    public void setCarTable(HtmlDataTable carTable) {
        this.carTable = carTable;
    }

    public Carorder getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Carorder currentOrder) {
        this.currentOrder = currentOrder;
    }

    public Payment getCurrentPayment() {
        return currentPayment;
    }

    public void setCurrentPayment(Payment currentPayment) {
        this.currentPayment = currentPayment;
    }
    
    public String prepareDetails(){
        this.currentCar = (Car) this.carTable.getRowData();
        return "/customer/carDetails";
    }
    
    public String prepareCarList(){
        this.loadAvailableCars();
        return "/customer/carList";
    }
    
    public String prepareCarListBought(){
        this.loadBoughtCars();
        return "/customer/carListBought";
    }
    
    private void loadBoughtCars(){
        this.boughtCars = this.carService.fetchAllBoughtForCustomer(CurrentUser.INSTANCE.getUser());
    }
    
    private void initOrder(Boolean cash){
        this.currentOrder = new Carorder();
        this.currentOrder.setCar(this.currentCar);
        this.currentOrder.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        this.currentOrder.setCustomer(CurrentUser.INSTANCE.getUser());
        
        if(cash){
            this.currentPayment = new Cash();
        }else{
            this.currentPayment = new Creditcard();
        }
        this.currentPayment.setInvoiceAmount(this.currentCar.getPrice());
        this.currentPayment.setInvoiceDate(new Date(Calendar.getInstance().getTimeInMillis()));
        this.currentPayment.setInvoiceNumber(((Double) Math.random()).toString());
        this.currentPayment.setOrder(this.currentOrder);
    }
    
    public String orderCarCash(){
        this.initOrder(true);
        
        return "/customer/orderCarCash";
    }
    
    public String orderCarCreditcard(){
        this.initOrder(false);
        return "/customer/orderCarCreditcard";
    }
    
    public String saveOrder(){
        this.currentPayment.setOrder(orderService.saveOrder(this.currentOrder));
        orderService.savePayment(this.currentPayment);
        return prepareCarList();
    }
}
