package net.example.frontend.bean;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import net.example.backend.pojo.user.User;
import net.example.frontend.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author andre
 */
@Component("userBean")
@RequestScoped
public class UserBean {

    private List<User> users;
    private User newUser;
    private User currentUser;
    private HtmlDataTable userTable;
    @Autowired
    private UserService userService;

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    @PostConstruct
    private void init() {
        prepareList();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String prepareNew() {
        this.currentUser = new User();
        return "userForm";
    }

    public void prepareList() {
        this.users = this.userService.fetchAll();
    }

    public String saveUser() {
        userService.save(this.currentUser);
        prepareList();
        return "userList";
    }

    public HtmlDataTable getUserTable() {
        return userTable;
    }

    public void setUserTable(HtmlDataTable userTable) {
        this.userTable = userTable;
    }

    public String prepareEdit() {
        this.currentUser = (User) this.userTable.getRowData();
        return "userForm";
    }
}
