package net.example.backend.dao.User;

import net.example.backend.dao.AbstractDao;
import net.example.backend.pojo.user.User;

/**
 *
 * @author andre
 */
public interface UserDao extends AbstractDao<User> {

    public User fetchByEmail(String username);
}
