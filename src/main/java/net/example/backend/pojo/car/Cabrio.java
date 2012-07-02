package net.example.backend.pojo.car;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;

/**
 *
 * @author andre
 */
@Entity
@Table(name="cabrio")
@DiscriminatorValue("CABRIO")
@PrimaryKeyJoinColumn(name="car_id")
public class Cabrio extends Car{
    
    @Column(name="hood_type")
    private String hoodType;
    
    @Column(name="hood_open_time")
    private Double hoodOpenTime;
    
    @Column(name="open_speed")
    private Integer openSpeed;

    public Double getHoodOpenTime() {
        return hoodOpenTime;
    }

    public void setHoodOpenTime(Double hoodOpenTime) {
        this.hoodOpenTime = hoodOpenTime;
    }

    public String getHoodType() {
        return hoodType;
    }

    public void setHoodType(String hoodType) {
        this.hoodType = hoodType;
    }

    public Integer getOpenSpeed() {
        return openSpeed;
    }

    public void setOpenSpeed(Integer openSpeed) {
        this.openSpeed = openSpeed;
    }

    @Override
    public Map<String, String> getDetails(){
        HashMap<String, String> retMap = (HashMap<String, String> ) super.getDetails();
        
        retMap.put("hood type", this.hoodType);
        retMap.put("hood open time", this.hoodOpenTime.toString());
        retMap.put("open speed", this.openSpeed.toString());
        
        return retMap;
    }
}
