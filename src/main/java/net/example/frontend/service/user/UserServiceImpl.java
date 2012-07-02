package net.example.frontend.service.user;

import java.util.List;
import net.example.backend.dao.User.UserDao;
import net.example.backend.pojo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andre
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    
    @Override
    public List<User> fetchAll() {
        return userDao.fetchAll();
    }
    
    @Override
    public void save(User user){
        userDao.save(user);
    }
}
