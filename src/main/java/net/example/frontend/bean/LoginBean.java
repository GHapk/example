package net.example.frontend.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import net.example.frontend.ext.CurrentUser;
import net.example.frontend.service.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Component("loginBean")
@RequestScoped
public class LoginBean {

    private String username;
    private String password;
    
    @Autowired
    private AuthenticationService authenticationService;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String login(){
        Boolean success = authenticationService.login(this.username, this.password);
        if(success){
            if(CurrentUser.INSTANCE.getUser().getType().toLowerCase().equals("customer")){
                return "/customer/carList";
            }else{
                return "/seller/carList";
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Login or password incorrect.")); 
            return "login";
        }
    }
}
