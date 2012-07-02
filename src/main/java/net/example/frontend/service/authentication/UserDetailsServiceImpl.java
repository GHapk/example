package net.example.frontend.service.authentication;

import java.util.ArrayList;
import net.example.backend.dao.User.UserDao;
import net.example.backend.pojo.user.GrantedAuthorityImpl;
import net.example.backend.pojo.user.User;
import net.example.frontend.ext.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author andre
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public UserDetails loadUserByUsername(String username){
        User userDb = userDao.fetchByEmail(username);
        CurrentUser.INSTANCE.setUser(userDb);
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthorityImpl("ROLE_" + userDb.getType().toUpperCase()));
        if(userDb.getAdmin()){
            authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
        }
        
        org.springframework.security.core.userdetails.User user = 
                new org.springframework.security.core.userdetails.User(username, 
                userDb.getPassword(), true, true, true, true, authorities);
        
        return user;
    }
    
}
