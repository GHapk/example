package net.example.frontend.service.user;

import java.util.List;
import net.example.backend.pojo.user.User;

/**
 *
 * @author andre
 */
public interface UserService {

    public List<User> fetchAll();
    
    public void save(User user);
}
