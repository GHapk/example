package net.example.backend.pojo.order;

import java.sql.Date;
import javax.persistence.*;
import org.hibernate.annotations.ForceDiscriminator;

/**
 *
 * @author andre
 */
@Entity
@Table(name="payment")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name="type",
        discriminatorType= DiscriminatorType.STRING
)
@ForceDiscriminator
@DiscriminatorValue("PAYMENT")
public class Payment {
    
    @Column
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    
    @Column(name="invoice_amount")
    private Double invoiceAmount;
    
    @Column(name="invoice_date")
    private Date invoiceDate;
    
    @Column(name="invoice_number")
    private String invoiceNumber;
    
    @OneToOne
    @JoinColumn(name="order_id")
    private Carorder order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Carorder getOrder() {
        return order;
    }

    public void setOrder(Carorder order) {
        this.order = order;
    }
    
    
    
}
