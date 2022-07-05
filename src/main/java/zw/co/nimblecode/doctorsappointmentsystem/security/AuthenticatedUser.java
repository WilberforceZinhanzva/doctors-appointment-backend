package zw.co.nimblecode.doctorsappointmentsystem.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class AuthenticatedUser extends User {
    private Map<String, String> userInformation = new HashMap<>();

    public AuthenticatedUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public Map<String, String> getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(Map<String, String> userInformation) {
        this.userInformation = userInformation;
    }
}
