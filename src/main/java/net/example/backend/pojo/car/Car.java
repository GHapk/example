package net.example.backend.pojo.car;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;
import net.example.backend.pojo.order.Carorder;
import net.example.backend.pojo.user.User;

/**
 *
 * @author andre
 */
@Entity
@Table(name="car")
@Inheritance(strategy= InheritanceType.JOINED)
@DiscriminatorColumn(
        name="type",
        discriminatorType= DiscriminatorType.STRING
)
@DiscriminatorValue("Car")
public class Car {
    
    @Column
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    
    @Column
    private Integer hp;
    
    @Column
    private String fuel;
    
    @Column(name="fabrication_year")
    private Integer fabricationYear;
    
    @Column(name="fabrication_month")
    private Integer fabricationMonth;
    
    @Column
    private Integer mileage;
    
    @Column
    private String manufacturer;
    
    @Column
    private String type;
    
    @Column
    private Double price;
    
    @ManyToOne(targetEntity = net.example.backend.pojo.user.User.class)
    @JoinColumn(name = "seller_id")
    private User seller;
    
    @OneToOne(mappedBy="car")
    private Carorder carorder;

    public Integer getFabricationMonth() {
        return fabricationMonth;
    }

    public void setFabricationMonth(Integer fabricationMonth) {
        this.fabricationMonth = fabricationMonth;
    }

    public Integer getFabricationYear() {
        return fabricationYear;
    }

    public void setFabricationYear(Integer fabricationYear) {
        this.fabricationYear = fabricationYear;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Carorder getCarorder() {
        return carorder;
    }

    public void setCarorder(Carorder carorder) {
        this.carorder = carorder;
    }
    
    public Map<String, String> getDetails(){
        HashMap<String, String> retMap = new HashMap<String, String>();
        
        retMap.put("hp", this.hp.toString());
        retMap.put("fuel", this.fuel);
        retMap.put("fabrication month", this.fabricationMonth.toString());
        retMap.put("fabrication year", this.fabricationYear.toString());
        retMap.put("mileage", this.mileage.toString());
        retMap.put("manufacturer", this.manufacturer);
        retMap.put("type", this.type.substring(0,1) + this.type.substring(1).toLowerCase());
        retMap.put("price", this.price.toString());
        
        return retMap;
    }
    
    
}
