package net.example.backend.pojo.user;

/**
 *
 * @author andre
 */
public class GrantedAuthorityImpl implements org.springframework.security.core.GrantedAuthority{

    private String authority;
    
    public GrantedAuthorityImpl(String authority){
        this.authority = authority;
    }
    
    @Override
    public String getAuthority() {
        return this.authority;
    }
    
}
