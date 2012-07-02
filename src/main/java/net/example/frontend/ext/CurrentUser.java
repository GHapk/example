package net.example.frontend.ext;

import net.example.backend.pojo.user.User;

/**
 * Representates the current loggedin user
 * 
 * @author andre
 */
public enum CurrentUser {
    
    INSTANCE;
    
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
