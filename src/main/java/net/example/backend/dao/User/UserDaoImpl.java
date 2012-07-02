package net.example.backend.dao.User;

import net.example.backend.annotation.Dao;
import net.example.backend.dao.AbstractDaoImpl;
import net.example.backend.pojo.user.User;
import org.springframework.stereotype.Repository;

/**
 *
 * @author andre
 */
@Repository
@Dao(entityClass=net.example.backend.pojo.user.User.class)
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

    @Override
    public User fetchByEmail(String username) {
        return (User) this.sessionFactory.getCurrentSession()
                .createQuery("from " + this.getHibernateTableName() + " WHERE "
                + "email=:email")
                .setParameter("email", username)
                .uniqueResult();
    }
    
    
    
}
