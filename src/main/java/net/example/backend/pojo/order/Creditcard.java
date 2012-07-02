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
@DiscriminatorValue("CREDITCARD")
public class Creditcard extends Payment{
    
    @Column(name="creditcard_number")
    private String creditcardNumber;
    
    @Column(name="delivery_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date deliveryDate;

    public String getCreditcardNumber() {
        return creditcardNumber;
    }

    public void setCreditcardNumber(String creditcardNumber) {
        this.creditcardNumber = creditcardNumber;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String getRelevantDateString(){
        return "delivery: " + this.getDeliveryDate();
    }
    
}
