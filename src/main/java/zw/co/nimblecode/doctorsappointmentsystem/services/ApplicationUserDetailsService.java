package zw.co.nimblecode.doctorsappointmentsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zw.co.nimblecode.doctorsappointmentsystem.exceptions.ResourceNotFoundException;
import zw.co.nimblecode.doctorsappointmentsystem.models.entities.*;
import zw.co.nimblecode.doctorsappointmentsystem.repositories.CredentialsRepository;
import zw.co.nimblecode.doctorsappointmentsystem.security.AuthenticatedUser;

import java.util.*;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Credentials> credentials = credentialsRepository.findByUsername(username);
        if (credentials.isEmpty())
            throw new ResourceNotFoundException("Username not found!");
        Map<String, String> userInformation = new HashMap<>();
        User user = credentials.get().getUser();
        userInformation.put("userId", user.getId());
        if (user instanceof Doctor) {
            Doctor doctor = (Doctor) user;
            userInformation.put("name", doctor.getFullname());
        } else if (user instanceof Patient) {
            Patient patient = (Patient) user;
            userInformation.put("name", patient.getFullname());
            userInformation.put("address", patient.getAddress());
            userInformation.put("email", patient.getEmail());
            userInformation.put("phone", patient.getPhone());

        } else if (user instanceof Assistant) {
            Assistant assistant = (Assistant) user;
            userInformation.put("name", assistant.getFullname());
        }

        AuthenticatedUser authenticatedUser = new AuthenticatedUser(username, credentials.get().getPassword(), authorities(credentials.get()));
        authenticatedUser.setUserInformation(userInformation);
        return authenticatedUser;
    }

    public Collection<GrantedAuthority> authorities(Credentials credentials) {

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        credentials.getRoles().stream().forEach(credential -> {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + credential.getRole()));

            credential.getPermissions().stream().forEach(permission -> {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermission()));
            });
        });
        return grantedAuthorities;
    }
}
