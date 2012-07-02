package net.example.frontend.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import net.example.frontend.ext.CurrentUser;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Component("menuBean")
@RequestScoped
public class MenuBean {

    private Boolean renderCustomer = false;
    private Boolean renderSeller = false;
    
    /**
     * Creates a new instance of MenuBean
     */
    public MenuBean() {
    }
    

    @PostConstruct
    private void init(){
        if(CurrentUser.INSTANCE.getUser() != null){
            if(CurrentUser.INSTANCE.getUser().getType().equals("CUSTOMER")){
                this.renderCustomer = true;
            }else{
                this.renderSeller = true;
            }
        }
    }

    public Boolean getRenderCustomer() {
        return CurrentUser.INSTANCE.getUser().getType().toLowerCase().equals("customer");
    }

    public void setRenderCustomer(Boolean renderCustomer) {
        this.renderCustomer = renderCustomer;
    }

    public Boolean getRenderSeller() {
        return CurrentUser.INSTANCE.getUser().getType().toLowerCase().equals("seller");
    }

    public void setRenderSeller(Boolean renderSeller) {
        this.renderSeller = renderSeller;
    }
    
    
}
