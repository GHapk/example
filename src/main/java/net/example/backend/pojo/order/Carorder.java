package net.example.backend.pojo.order;

import java.sql.Date;
import javax.persistence.*;
import net.example.backend.pojo.car.Car;
import net.example.backend.pojo.user.User;

/**
 *
 * @author andre
 */
@Entity
@Table(name="`carorder`")
public class Carorder {
    
    @Column
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    
    @Column(name="orderdate")
    private Date date;
    
    @OneToOne
    @JoinColumn(name="car_id")
    private Car car;
    
    @ManyToOne(targetEntity=net.example.backend.pojo.user.User.class)
    @JoinColumn(name="customer_id")
    private User customer;
    
    @OneToOne(mappedBy="order")
    private Payment payment;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
