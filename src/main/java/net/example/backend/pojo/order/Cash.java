package net.example.backend.pojo.order;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author andre
 */
@Entity
@DiscriminatorValue("CASH")
public class Cash extends Payment{
    
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date pickupdate;

    public Date getPickupdate() {
        return pickupdate;
    }

    public void setPickupdate(Date pickupdate) {
        this.pickupdate = pickupdate;
    }
    
    @Override
    public String getRelevantDateString(){
        
        return "pickup: " + this.pickupdate;
    }
    
}
