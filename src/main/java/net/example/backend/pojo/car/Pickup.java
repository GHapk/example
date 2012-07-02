package net.example.backend.pojo.car;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;
import org.hibernate.annotations.Type;

/**
 *
 * @author andre
 */
@Entity
@Table(name="pickup")
@DiscriminatorValue("PICKUP")
@PrimaryKeyJoinColumn(name="car_id")
public class Pickup extends Car{
    
    @Column(name="cargo_area")
    private Double cargoArea;
    
    @Column(name="cargo_closeable")
    @Type(type="yes_no")
    private Boolean cargoCloseable;

    public Double getCargoArea() {
        return cargoArea;
    }

    public void setCargoArea(Double cargoArea) {
        this.cargoArea = cargoArea;
    }

    public Boolean getCargoCloseable() {
        return cargoCloseable;
    }

    public void setCargoCloseable(Boolean cargoCloseable) {
        this.cargoCloseable = cargoCloseable;
    }
    
    @Override
    public Map<String, String> getDetails(){
        HashMap<String, String> retMap = (HashMap<String, String> ) super.getDetails();
        
        retMap.put("cargo area", this.cargoArea.toString());
        retMap.put("cargo closeable", (this.cargoCloseable ? "yes" : "no"));
        
        return retMap;
    }
    
}
